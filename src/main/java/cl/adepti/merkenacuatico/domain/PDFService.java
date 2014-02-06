package cl.adepti.merkenacuatico.domain;

import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public interface PDFService {

	/**
	 * Creates a document for the exam. Adds it to the exam. Persists it and return it.
	 * @param exam
	 * @return
	 * @throws Exception when the exam is not persisted
	 */
	MerkenFile create(Exam exam) throws Exception;

}
