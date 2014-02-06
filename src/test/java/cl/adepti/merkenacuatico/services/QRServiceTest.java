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
import cl.adepti.merkenacuatico.domain.MerkenQrRules;
import cl.adepti.merkenacuatico.domain.PDFService;
import cl.adepti.merkenacuatico.domain.QRPdfService;
import cl.adepti.merkenacuatico.domain.ScanService;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.Page;
import cl.adepti.merkenacuatico.domain.entity.Student;
import cl.adepti.merkenacuatico.stubs.FakeFile;
import cl.adepti.merkenacuatico.stubs.FakeStorageService;
import cl.adepti.merkenacuatico.stubs.StubGenerator;

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

public class QRServiceTest {

	@Test
	public void testCreateQRExam() {
		QRPdfService service = new QRPdfService(new MerkenQrRules());
		StorageService fileService =  new FakeStorageService();
		StubGenerator stubGenerator = new StubGenerator();
		QRCodeReader reader = new QRCodeReader();//Will assert that the qr is created
		ScanService scanService = new ScanService(fileService);
		
		Exam exam = new Exam();
		
		
		BluePrint bluePrint = new BluePrint();
		bluePrint.setName("Exam for testing");
		bluePrint.setSourceFile(new FakeFile("3pageblueprint.pdf"));

		List<Student> students = stubGenerator.createStudents(30);
		bluePrint.setStudents(students);
		
		//This is how you build an exam.
		exam.setBluePrint(bluePrint);
		exam.setStudent(students.get(0));
		exam.setId(new ObjectId());
		
		MerkenFile doc = null;
		try {
			doc = service.create(exam);
		} catch (Exception e1) {
			e1.printStackTrace();
			fail(e1.getMessage());
		}
		
		
		assertNotNull(doc);
		assertNotNull(doc.getFileName());
		
		List<Page> pages = exam.getPages();
		assertNotNull("Must create pages",pages);
		assertTrue("Page list cannot be empty", pages.size()>0);
		
		Page examplePage = pages.get(0);
		try {
			scanService.getMetaDataForPage(examplePage);
		} catch (NotFoundException e) {
			fail(e.getMessage());
		} catch (ChecksumException e) {
			fail(e.getMessage());
		} catch (FormatException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
	}
}
