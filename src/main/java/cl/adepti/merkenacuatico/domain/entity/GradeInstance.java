package cl.adepti.merkenacuatico.domain.entity;

import org.springframework.data.annotation.Id;

import cl.adepti.merkenacuatico.domain.ScoreValue;

public class GradeInstance {
	@Id
	private Integer id;
	private Marker marker;
	private Exam exam;
	private ScoreValue grade;
	
}
