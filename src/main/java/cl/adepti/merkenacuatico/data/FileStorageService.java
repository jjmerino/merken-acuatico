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
import com.mongodb.gridfs.GridFSInputFile;

import cl.adepti.merkenacuatico.config.SpringMongoConfig;
import cl.adepti.merkenacuatico.domain.entity.MerkenDocument;

public class FileStorageService {

	
	@Autowired
	GridFsOperations gridOperations;
	
	public GridFSFile create(InputStream inputStream,String clientFileName, String contentType,String area){

		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", clientFileName);
		metaData.put("meta2", contentType);
		metaData.put("meta3", area);

		GridFSInputFile file = (GridFSInputFile) gridOperations.store(inputStream, clientFileName, metaData);	
		return file;
	}
	
	public List<GridFSDBFile> find(String id){
		
		List<GridFSDBFile> result = gridOperations.find(
	               new Query().addCriteria(Criteria.where("id").is(id)));
		return result;
	}
	
}
