package cl.adepti.merkenacuatico.domain.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public class MerkenDocument {
	@Id
	private String id;//an id for the document instance
	
	private String clientFilename;//original client file name

	private String fileId; // Reference to the stored file.
	
	public MerkenDocument(String clientFilename){
		this.clientFilename = clientFilename;
	}
	public String getId() {
		return this.id;
	}
	
	public String getClientFilename() {
		return this.clientFilename;
	}

	public void setFileId(String id) {
		this.fileId = id;
	}
	public String getFileId(){
		return this.fileId;
	}
	
}
