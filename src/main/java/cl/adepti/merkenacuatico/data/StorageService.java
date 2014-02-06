package cl.adepti.merkenacuatico.data;

import java.io.InputStream;

import org.bson.types.ObjectId;

import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public interface StorageService {

	public String create(InputStream inputStream, String clientFileName,
			String contentType, String area);

	public MerkenFile find(String id);

}