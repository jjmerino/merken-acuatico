package cl.adepti.merkenacuatico.data;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import cl.adepti.merkenacuatico.config.SpringMongoConfig;
import cl.adepti.merkenacuatico.domain.entity.MerkenDocument;

public class DocumentStorageService {
	@Autowired
	private DocumentRepository repository;
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	GridFsOperations gridOperations = (GridFsOperations) ctx.getBean("gridFsTemplate");
	
	public MerkenDocument create(InputStream inputStream,String clientFileName, String contentType){
		MerkenDocument document = new MerkenDocument(clientFileName);
		String filename = UUID.randomUUID().toString();
		
		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);

		GridFSFile file = gridOperations.store(inputStream, filename, metaData);
		
		document.setMongoFile(file);
		repository.save(document);
		return document;
	}
	
	public List<GridFSDBFile> find(String id){
		
		List<GridFSDBFile> result = gridOperations.find(
	               new Query().addCriteria(Criteria.where("id").is(id)));
		return result;
	}
	
}
