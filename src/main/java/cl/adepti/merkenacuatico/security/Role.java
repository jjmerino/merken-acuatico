package cl.adepti.merkenacuatico.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	public Role(String name){
		this.name = name;
	}
	@Override
	public String getAuthority() {
		return name;
	}

}
