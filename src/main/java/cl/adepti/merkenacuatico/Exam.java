package cl.adepti.merkenacuatico;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.LinkBuilderSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.data.annotation.Id;
public class Exam extends ResourceSupport {
	
    @Id
    private Integer id;
    
    private final String content;

    public Exam(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

	public Integer getExamId() {
		return this.id;
	}
	public void setExamId(int id){
		this.id = id; 
	}
    
}
