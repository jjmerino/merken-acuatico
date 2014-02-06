package cl.adepti.merkenacuatico.domain;

import org.springframework.beans.factory.annotation.Autowired;

import cl.adepti.merkenacuatico.data.UserRepository;
import cl.adepti.merkenacuatico.domain.entity.Exam;

public class MerkenQrRules implements QRMetaRules {
	
	@Override
	public String makeQRDataForExam(Exam exam) throws Exception {
		String str= exam.getId().toString();
		if(str.length()<1){
			throw new Exception("Exam must be initialized");
		}
		return str;
	}
	@Autowired
	UserRepository userRepo;
	@Override
	public Exam getExamDataFromMeta(String text) throws Exception {
		//TODO: Implement method.
		throw new Exception("The method is not implemented");
	}

}
