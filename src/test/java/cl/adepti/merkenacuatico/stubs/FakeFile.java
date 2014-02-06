package cl.adepti.merkenacuatico.stubs;


import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

/**
 * Provides a fake file to test.
 * Files should be stored in test/resources/
 * @author Jose
 *
 */
public class FakeFile extends MerkenFile {
	
	public FakeFile(String name){
		super(name,ClassLoader.getSystemClassLoader().getResourceAsStream(name),name);
	}

}
