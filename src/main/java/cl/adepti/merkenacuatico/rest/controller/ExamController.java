package cl.adepti.merkenacuatico.rest.controller;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.rest.resource.ExamAssembler;
import cl.adepti.merkenacuatico.rest.resource.ExamResource;

@Controller
public class ExamController {
	
	@Autowired
    private ExamAssembler examAssembler;
	@Autowired
	private ExamRepository repository;
	
	@RequestMapping("/exams/{examid}")
	@ResponseBody
	public ExamResource exam(@PathVariable String string) {
		Exam exam = repository.findById(new ObjectId(string));
		return examAssembler.toResource(exam);
	}
	@RequestMapping("/exams")
	@ResponseBody
	public List<ExamResource> exams(){
		List<Exam> exams = repository.findAll();
		return examAssembler.toResources(exams);
	}

}
