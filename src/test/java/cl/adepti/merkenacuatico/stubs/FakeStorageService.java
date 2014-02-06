package cl.adepti.merkenacuatico.stubs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public class FakeStorageService implements StorageService {

	@Override
	public String create(InputStream inputStream, String clientFileName,
			String contentType, String area) {
		
		String basePath = "src/test/resources";
		File testfile = new File(basePath+"/"+clientFileName);
		FileOutputStream ostr = null;
		try {
			ostr = new FileOutputStream(testfile);
		
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				ostr.write(bytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ostr != null) {
				try {
					// outputStream.flush();
					ostr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
		return clientFileName;
	}

	@Override
	public MerkenFile find(String id) {
		
		return new FakeFile(id); 
	}

}
