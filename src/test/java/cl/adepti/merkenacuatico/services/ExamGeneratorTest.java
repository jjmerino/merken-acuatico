package cl.adepti.merkenacuatico.services;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.ExamGenerator;
import cl.adepti.merkenacuatico.domain.PDFService;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.Student;
import cl.adepti.merkenacuatico.stubs.FakeFile;
import cl.adepti.merkenacuatico.stubs.StubGenerator;

import org.mockito.Mockito;

public class ExamGeneratorTest {

	@Test
	public void testGenerateExams() {
		
		//To use you need the repository and the pdfService.
		ExamRepository repo = Mockito.mock(ExamRepository.class);
		PDFService pdfService = Mockito.mock(PDFService.class);
		StubGenerator stubGenerator = new StubGenerator();
		//The generator must be instanciated with pdfService and Repository
		ExamGenerator generator = new ExamGenerator(pdfService,repo);
		
		//You must have a blueprint with Students set.
		BluePrint bluePrint = new BluePrint();
		int n = 10;
		List<Student> students= stubGenerator.createStudents(n);
		bluePrint.setStudents(students);
		
		//The pdfService will create one MerkenDocument per exam
		try {
			Mockito.when(pdfService.create(Mockito.any(Exam.class))).thenReturn(new FakeFile("3pageqrexam.pdf"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		}
		
		//Generator Usage:
		Collection<Exam> exams= null;
		try {
			exams = generator.generateExams(bluePrint);
		} catch (Exception e2) {
			fail(e2.getMessage());
		}
		
		assertNotNull(exams);
		//It should generate one exam per student
		assertEquals(n, exams.size());
		
		for(Exam e : exams){
			//The exam will have a document.
			assertNotNull(e.getSourceDocument());
			//The exam will have a student
			assertNotNull(e.getStudent());
			//the exam will have a reference to the original Blueprint.
			assertEquals(bluePrint,e.getBluePrint());
		}

		//The repo should save twice per exam.
		Mockito.verify(repo,Mockito.times(n*2)).save(Mockito.any(Exam.class));

		//The pdf creator should be called once per exam,.
		try {
			Mockito.verify(pdfService,Mockito.times(n)).create(Mockito.any(Exam.class));
		} catch (Exception e1) {
			fail(e1.getMessage());
		}
		
	}
}
