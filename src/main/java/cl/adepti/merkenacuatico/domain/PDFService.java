package cl.adepti.merkenacuatico.domain;

import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public interface PDFService {

	/**
	 * Creastes a document for the exam.
	 * @param exam
	 * @return
	 */
	MerkenFile create(Exam exam);

}
