package cl.adepti.merkenacuatico.rest.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.rest.controller.ExamController;
@Component
public class UserAssembler extends ResourceAssemblerSupport<Exam, ExamResource> {

	public UserAssembler() {
		super(Exam.class, ExamResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExamResource toResource(Exam exam) {
		ExamResource resource = new ExamResource();
		resource.add(linkTo(methodOn(ExamController.class).exam(exam.getId())).withSelfRel());

		resource.add(linkTo(methodOn(ExamController.class).exams())
				.withRel("exams"));
		return resource;
	}

}
