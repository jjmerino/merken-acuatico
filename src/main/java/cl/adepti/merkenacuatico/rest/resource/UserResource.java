package cl.adepti.merkenacuatico.rest.resource;

import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
	
    private Integer id;

    public UserResource() {
    }

	public Integer getExamId() {
		return this.id;
	}
	public void setExamId(int id){
		this.id = id; 
	}
    
}
