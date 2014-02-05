package cl.adepti.merkenacuatico.rest.controller;


import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.adepti.merkenacuatico.data.BluePrintRepository;
import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.rest.resource.BluePrintAssembler;
import cl.adepti.merkenacuatico.rest.resource.BluePrintResource;
import cl.adepti.merkenacuatico.rest.resource.ExamAssembler;

@Controller
public class BluePrintController {
	

	@Autowired
	private BluePrintRepository repository;
	@Autowired
    private BluePrintAssembler bluePrintAssembler;
	@RequestMapping("/blueprints/{blueprintId}")
	@ResponseBody
	public BluePrint blueprint(@PathVariable BigInteger blueprintId) {
		return repository.findById(blueprintId);
	}
	@RequestMapping("/blueprints")
	@ResponseBody
	public List<BluePrintResource> blueprints(){
		List<BluePrint> blueprints = repository.findAll();
		
		return bluePrintAssembler.toResources(blueprints);
	}

}
