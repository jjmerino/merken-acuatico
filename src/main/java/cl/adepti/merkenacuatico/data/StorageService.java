package cl.adepti.merkenacuatico.data;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

public class StorageService {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	GridFsOperations gridOperations = (GridFsOperations) ctx.getBean("gridFsTemplate");
	
	public String save(InputStream inputStream,String contentType,String filename){
		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);

		GridFSFile file = gridOperations.store(inputStream, filename, metaData);

		return file.getId().toString();
		
	}
	
	public List<GridFSDBFile> findByFileName(String fileName){
		
		List<GridFSDBFile> result = gridOperations.find(
	               new Query().addCriteria(Criteria.where("filename").is(fileName)));
		return result;
	}
	
}
