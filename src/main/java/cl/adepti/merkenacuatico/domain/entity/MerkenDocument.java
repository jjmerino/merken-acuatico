package cl.adepti.merkenacuatico.domain.entity;


import org.springframework.data.annotation.Id;

import com.mongodb.gridfs.GridFSFile;

public class MerkenDocument {
	@Id
	private String id;//an id for the document instance
	
	private String clientFilename;//original client file name

	private GridFSFile file;
	
	public MerkenDocument(String clientFilename){
		this.clientFilename = clientFilename;
	}
	public String getId() {
		return this.id;
	}
	
	public String getClientFilename() {
		return this.clientFilename;
	}

	public GridFSFile getFile(){
		return this.file;
	}
	public void setMongoFile(GridFSFile file) {
		this.file = file;
	}
	
}
