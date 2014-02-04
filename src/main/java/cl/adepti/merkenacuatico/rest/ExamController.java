package cl.adepti.merkenacuatico.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.entity.Exam;

@Controller
public class ExamController {
	
	@Autowired
    private ExamAssembler examAssembler;
	@Autowired
	private ExamRepository repository;
	
	@RequestMapping("/exams/{examid}")
	@ResponseBody
	public ExamResource exam(@PathVariable Integer examid) {
		Exam exam = repository.findById(examid);
		return examAssembler.toResource(exam);
	}
	@RequestMapping("/exams")
	@ResponseBody
	public List<ExamResource> exams(){
		List<Exam> exams = repository.findAll();
		return examAssembler.toResources(exams);
	}

}
