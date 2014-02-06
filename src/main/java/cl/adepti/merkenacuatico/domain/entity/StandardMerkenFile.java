package cl.adepti.merkenacuatico.domain.entity;

import java.io.InputStream;

public class StandardMerkenFile extends MerkenFile {

	public StandardMerkenFile(String id,InputStream stream,String filename){
		super(id,stream,filename);
	}

}
