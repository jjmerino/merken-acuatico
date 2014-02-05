package cl.adepti.merkenacuatico.rest.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.rest.controller.BluePrintController;
import cl.adepti.merkenacuatico.rest.controller.ExamController;
@Component
public class BluePrintAssembler extends ResourceAssemblerSupport<BluePrint, BluePrintResource> {

	public BluePrintAssembler() {
		super(BluePrint.class, BluePrintResource.class);
		
	}

	@Override
	public BluePrintResource toResource(BluePrint blueprint) {
		BluePrintResource resource = new BluePrintResource(blueprint);
		resource.add(linkTo(methodOn(BluePrintController.class).blueprint(blueprint.getId())).withSelfRel());

		resource.add(linkTo(methodOn(BluePrintController.class).blueprints())
				.withRel("exams"));
		return resource;
	}

}
