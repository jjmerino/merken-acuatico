package cl.adepti.merkenacuatico.services;

import java.io.InputStream;

import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

/**
 * Provides a fake file to test.
 * Files should be stored in test/resources/
 * @author Jose
 *
 */
public class FakeFile implements MerkenFile {
	
	String name = "testPage.png";
	public FakeFile(String name){
		this.name = name;
	}
	@Override
	public InputStream getInputStream() {
		InputStream str = ClassLoader.getSystemClassLoader().getResourceAsStream(this.name);
		return str;
	}

	@Override
	public String getFileName() {
		return this.name;
	}

}
