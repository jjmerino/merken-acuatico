package cl.adepti.merkenacuatico.domain.entity;

import java.io.File;

import org.springframework.data.annotation.Id;

public class Page {
	@Id
	private Integer id;
	private Exam exam;
	private File image;
	private Integer index;
}
