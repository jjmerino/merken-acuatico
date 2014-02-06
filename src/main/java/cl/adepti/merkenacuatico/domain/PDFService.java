package cl.adepti.merkenacuatico.domain;

import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public interface PDFService {

	/**
	 * Creastes a document for the exam.
	 * @param exam
	 * @return
	 * @throws Exception when the exam is not persisted
	 */
	MerkenFile create(Exam exam) throws Exception;

}
