package cl.adepti.merkenacuatico.domain.entity;



import com.mongodb.gridfs.GridFSDBFile;
public class MongoMerkenFile extends MerkenFile {
	
	private GridFSDBFile gridFsFile;

	public MongoMerkenFile(GridFSDBFile file){
		super(file.getId().toString(),file.getInputStream(),file.getFilename());
		this.gridFsFile = file;
	}
	
	public GridFSDBFile getGridFsDBFile(){
		return this.gridFsFile;
	}

	
	
}
