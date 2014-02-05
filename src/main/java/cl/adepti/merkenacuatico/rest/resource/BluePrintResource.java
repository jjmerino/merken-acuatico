package cl.adepti.merkenacuatico.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;

public class BluePrintResource extends ResourceSupport {

	private String name;
	private String fileData;
	public BluePrintResource(BluePrint blueprint) {
		this.name="Lindonombre";
		this.fileData="Lindofile!";
	}
	
}
