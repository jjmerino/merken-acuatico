package cl.adepti.merkenacuatico;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
@Component
public class ExamAssembler extends ResourceAssemblerSupport<Exam, Exam> {

	public ExamAssembler() {
		super(ExamController.class, Exam.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Exam toResource(Exam exam) {
		
		exam.add(linkTo(methodOn(ExamController.class).exam(exam.getExamId())).withSelfRel());

		exam.add(linkTo(methodOn(ExamController.class).exams())
				.withRel("exams"));
		return exam;
	}

}
