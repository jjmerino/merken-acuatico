package cl.adepti.merkenacuatico.domain.entity;

import java.io.File;

import org.springframework.data.annotation.Id;

public class Exam {
	
	@Id
	private Integer id;
	private Student student;
	private BluePrint bluePrint;
	private MerkenDocument sourceDocument;//Backup of original pdf File with the exam.
	private MerkenDocument annotatedDocument;
	public Integer getId() {
		return id;
	}
	
}
