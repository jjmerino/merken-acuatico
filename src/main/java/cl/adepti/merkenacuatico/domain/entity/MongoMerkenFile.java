package cl.adepti.merkenacuatico.domain.entity;


import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;
public class MongoMerkenFile implements MerkenFile {
	
	private GridFSDBFile gridFsFile;

	public MongoMerkenFile(GridFSDBFile file){
		this.gridFsFile = file;
		
	}
	/* (non-Javadoc)
	 * @see cl.adepti.merkenacuatico.domain.entity.IMerkenFile#getInputStream()
	 */
	@Override
	public InputStream getInputStream(){
		return this.gridFsFile.getInputStream();
	}
	/* (non-Javadoc)
	 * @see cl.adepti.merkenacuatico.domain.entity.IMerkenFile#getFileName()
	 */
	@Override
	public String getFileName() {
		return this.gridFsFile.getFilename();
	}
	
	
}
