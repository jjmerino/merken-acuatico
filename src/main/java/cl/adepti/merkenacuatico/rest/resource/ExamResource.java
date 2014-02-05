package cl.adepti.merkenacuatico.rest.resource;

import org.springframework.hateoas.ResourceSupport;

public class ExamResource extends ResourceSupport {
	
    private Integer id;

    public ExamResource() {
    }

	public Integer getExamId() {
		return this.id;
	}
	public void setExamId(int id){
		this.id = id; 
	}
    
}
