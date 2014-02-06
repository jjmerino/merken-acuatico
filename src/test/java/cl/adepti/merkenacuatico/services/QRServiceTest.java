package cl.adepti.merkenacuatico.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.MerkenQrRules;
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

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

public class QRServiceTest {

	@Test
	public void testCreateQRExam() {
		StorageService fileService =  new FakeStorageService();
		ExamRepository examRepo = Mockito.mock(ExamRepository.class);
		
		QRPdfService service = new QRPdfService(new MerkenQrRules(),fileService,examRepo);
		StubGenerator stubGenerator = new StubGenerator();
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
		assertEquals(doc,exam.getSourceDocument());
		
		MerkenFile storedFile = fileService.find(doc.getFileId());
		assertTrue(storedFile.getFileId().equals(doc.getFileId()));
		
		
		
	}
}
