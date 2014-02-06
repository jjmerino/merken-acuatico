package cl.adepti.merkenacuatico.stubs;

import java.io.InputStream;
import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public class FakeStorageService implements StorageService {

	@Override
	public String create(InputStream inputStream, String clientFileName,
			String contentType, String area) {
		return "pagep1.png";
	}

	@Override
	public MerkenFile find(String id) {
		
		return new FakeFile(id); 
	}

}
