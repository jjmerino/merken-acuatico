package cl.adepti.merkenacuatico.domain.entity;

import java.io.InputStream;

public abstract class MerkenFile {
	
	private String fileId;
	private String fileName;
	private InputStream inputStream;
	
	protected MerkenFile(String fileId,InputStream inputStream,String fileName){
		this.fileId = fileId;
		this.fileName = fileName;
		this.inputStream = inputStream;
	}
	

	public final String getFileName(){
		return this.fileName;
	}
	public final String getFileId(){
		return this.fileId;
	}
	public final InputStream getInputStream(){
		return this.inputStream;
	}

}