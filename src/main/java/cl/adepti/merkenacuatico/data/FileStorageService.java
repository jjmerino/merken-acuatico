package cl.adepti.merkenacuatico.data;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
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
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.MongoMerkenFile;

public class FileStorageService implements StorageService {

	
	@Autowired
	GridFsOperations gridOperations;
	
	/* (non-Javadoc)
	 * @see cl.adepti.merkenacuatico.data.StorageService#create(java.io.InputStream, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String create(InputStream inputStream,String clientFileName, String contentType,String area){

		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", clientFileName);
		metaData.put("meta2", contentType);
		metaData.put("meta3", area);

		GridFSInputFile file = (GridFSInputFile) gridOperations.store(inputStream, clientFileName, metaData);
		return file.getId().toString();
	}
	
	/* (non-Javadoc)
	 * @see cl.adepti.merkenacuatico.data.StorageService#find(java.lang.String)
	 */
	@Override
	public MerkenFile find(String id){
		GridFSDBFile result = gridOperations.findOne(
	               new Query().addCriteria(Criteria.where("_id").is(new ObjectId(id))));
		MerkenFile mkfile = new MongoMerkenFile(result);
		return mkfile;
	}
	
}
