package cl.adepti.merkenacuatico.domain.entity;

import java.io.File;

import org.springframework.data.annotation.Id;

public class Document {
	@Id
	private String uniqueName;//a unique filename for mongo storage and retrieval
	private String filename;//client file name
	
}
