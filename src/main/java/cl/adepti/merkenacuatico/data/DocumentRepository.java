package cl.adepti.merkenacuatico.data;


import org.springframework.data.mongodb.repository.MongoRepository;

import cl.adepti.merkenacuatico.domain.entity.MerkenDocument;

public interface DocumentRepository extends MongoRepository<MerkenDocument, String>{
	public MerkenDocument findById(String id);
}
