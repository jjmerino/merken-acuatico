package cl.adepti.merkenacuatico.domain;

import java.util.LinkedList;
import java.util.List;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.Student;

public class ExamGenerator {
	PDFService pdfService;
	ExamRepository examRepo;
	public ExamGenerator(PDFService pdfService,ExamRepository examRepo){
		this.pdfService=pdfService;
		this.examRepo = examRepo;
		
	}
	
	/**
	 * Generate an exam list for a blueprint. 
	 * The blueprint must have students set.
	 * @param blueprint
	 * @return
	 */
	public List<Exam> generateExams(BluePrint blueprint){
		List<Exam> list = new LinkedList<Exam>();
		
		for(Student s : blueprint.getStudents()){
			Exam exam = new Exam();
			exam.setBluePrint(blueprint);
			exam.setStudent(s);
			examRepo.save(exam);
			MerkenFile doc = pdfService.create(exam);
			exam.setSourceDocument(doc);
			examRepo.save(exam);
			list.add(exam);
		}
		return list;
		
	}
	
}
