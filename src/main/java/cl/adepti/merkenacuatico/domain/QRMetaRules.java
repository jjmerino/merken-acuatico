package cl.adepti.merkenacuatico.domain;

import cl.adepti.merkenacuatico.domain.entity.Exam;

public interface QRMetaRules {
	public String makeQRDataForExam(Exam exam) throws Exception;
	public Exam getExamDataFromMeta(String text) throws Exception;
}
