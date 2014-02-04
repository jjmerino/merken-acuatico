package cl.adepti.merkenacuatico.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.adepti.merkenacuatico.data.DocumentRepository;
import cl.adepti.merkenacuatico.domain.entity.MerkenDocument;

@Controller
public class MerkenDocumentController {
	

	@Autowired
	private DocumentRepository repository;
	
	@RequestMapping("/documents/{documentid}")
	@ResponseBody
	public List<MerkenDocument> exam(@PathVariable String documentid) {
		List<MerkenDocument> merkens = new ArrayList<MerkenDocument>();
		MerkenDocument document = repository.findById(documentid);
		merkens.add(document);
		return merkens;
	}
	@RequestMapping("/documents")
	@ResponseBody
	public List<MerkenDocument> exams(){
		List<MerkenDocument> docs = repository.findAll();
		return docs;
	}

}
