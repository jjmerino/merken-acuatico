package cl.adepti.merkenacuatico.services;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.data.FileStorageService;
import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.ExamGenerator;
import cl.adepti.merkenacuatico.domain.PDFService;
import cl.adepti.merkenacuatico.domain.QRPdfService;
import cl.adepti.merkenacuatico.domain.ScanService;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.Page;
import cl.adepti.merkenacuatico.domain.entity.Student;

import org.mockito.Mockito;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

public class PDFServiceTest {

	@Test
	public void testCreateQRExam() {
		QRPdfService service = new QRPdfService();
		StorageService fileService =  new FakeStorageService();
		
		Exam exam = new Exam();
		
		Student student = new Student();
		student.setId(new ObjectId());
		student.setUsername("pedrito");
		BluePrint bluePrint = new BluePrint();
		bluePrint.setName("Exam for testing");
		bluePrint.setSourceFile(new FakeFile("3pageblueprint.pdf"));
		Collection<Student> students = new LinkedList<Student>();
		students.add(student);
		bluePrint.setStudents(students);
		
		//This is how you build an exam.
		exam.setBluePrint(bluePrint);
		exam.setStudent(student);
		
		MerkenFile doc = service.create(exam);
		MerkenFile file = bluePrint.getSourceFile();
		
		//assertNotNull(doc);
		//assertNotNull(doc.getFileName());
		
		QRCodeReader reader = new QRCodeReader();
		ScanService scanService = new ScanService(fileService);
		List<Page> pages = exam.getPages();
		
		BufferedImage image=null;
		try {
			image = scanService.getImageForPage(pages.get(0));
		} catch (IOException e1) {
			fail(e1.getMessage());
		}
		assertNotNull(image);
		BufferedImage subimage = image.getSubimage(
				image.getWidth() - image.getWidth() / 4, 0,
				image.getWidth() / 4, image.getHeight() / 4);
		LuminanceSource source = new BufferedImageLuminanceSource(subimage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result =null;
		try {
		result=	reader.decode(bitmap);
		} catch (NotFoundException e) {
			fail(e.getMessage());
		} catch (ChecksumException e) {
			fail(e.getMessage());
		} catch (FormatException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(result);
		
		String filename = result.getText().replace(" ", "").trim();
		String[] parts = filename.split("-");
		assertTrue("There must be at least one part", parts.length>0);
		
		//The first part of the qr must hold the exam id
		assertTrue(parts[0].equals(exam.getId().toString()));
		
		
	}
}
