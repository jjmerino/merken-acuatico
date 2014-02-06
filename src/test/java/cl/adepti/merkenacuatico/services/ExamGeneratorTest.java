package cl.adepti.merkenacuatico.services;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;

import org.bson.types.ObjectId;
import org.junit.Test;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.ExamGenerator;
import cl.adepti.merkenacuatico.domain.PDFService;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MongoMerkenFile;
import cl.adepti.merkenacuatico.domain.entity.Student;
import org.mockito.Mockito;

public class ExamGeneratorTest {

	@Test
	public void testGenerateExams() {
		
		//To use you need the repository and the pdfService.
		ExamRepository repo = Mockito.mock(ExamRepository.class);
		PDFService pdfService = Mockito.mock(PDFService.class);
		
		//The generator must be instanciated with pdfService and Repository
		ExamGenerator generator = new ExamGenerator(pdfService,repo);
		
		//You must have a blueprint with Students set.
		BluePrint bluePrint = new BluePrint();
		int n = 10;
		Collection<Student> students= this.createStudents(n);
		bluePrint.setStudents(students);
		
		//The pdfService will create one MerkenDocument per exam
		Mockito.when(pdfService.create(Mockito.any(Exam.class))).thenReturn(null);
		
		//Generator Usage:
		Collection<Exam> exams = generator.generateExams(bluePrint);
		
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
		
		
	}
	
	private Collection<Student> createStudents(int n){
		Collection<Student> students = new LinkedList<Student>();
		for(int i = 1; i<=n;i++){
			Student student = new Student();
			student.setId(new ObjectId());
			student.setUsername("student"+i);
			students.add(student);
		}
		
		return students;
	}
}
