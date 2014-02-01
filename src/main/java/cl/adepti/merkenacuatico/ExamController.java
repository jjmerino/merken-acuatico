package cl.adepti.merkenacuatico;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExamController {
	
	@Autowired
    private ExamAssembler examAssembler;
	
	@RequestMapping("/exams/{examid}")
	@ResponseBody
	public Exam exam(@PathVariable Integer examid) {
		Exam exam = new Exam("Contenido falso "+examid);
		exam.setExamId(examid);
		return examAssembler.toResource(exam);
	}
	@RequestMapping("/exams")
	@ResponseBody
	public List<Exam> exams(){
		Exam exam = new Exam("Contenido falso "+1);
		exam.setExamId(1);
		Exam exam2 = new Exam("Contenido falso "+2);
		exam2.setExamId(2);
		ArrayList<Exam> exs = new ArrayList<Exam>();
		exs.add(exam);
		exs.add(exam2);
		return examAssembler.toResources(exs);
	}

}
