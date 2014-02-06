package cl.adepti.merkenacuatico.domain.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Exam {
	
	@Id
	private ObjectId id;
	@DBRef
	private Student student;
	@DBRef
	private BluePrint bluePrint;
	
	@Transient
	private MerkenFile sourceDocument;
	
	@Transient
	private MerkenFile annotatedDocument;
	
	private List<Page> pages;
	
	private Integer originalNumPages;
	
	public ObjectId getId() {
		return id;
	}


	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}


	/**
	 * @return the bluePrint
	 */
	public BluePrint getBluePrint() {
		return bluePrint;
	}


	/**
	 * @return the sourceDocument
	 */
	public MerkenFile getSourceDocument() {
		return sourceDocument;
	}


	/**
	 * @return the annotatedDocument
	 */
	public MerkenFile getAnnotatedDocument() {
		return annotatedDocument;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}


	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}


	/**
	 * @param bluePrint the bluePrint to set
	 */
	public void setBluePrint(BluePrint bluePrint) {
		this.bluePrint = bluePrint;
	}


	/**
	 * @param sourceDocument the sourceDocument to set
	 */
	public void setSourceDocument(MerkenFile sourceDocument) {
		this.sourceDocument = sourceDocument;
	}


	/**
	 * @param annotatedDocument the annotatedDocument to set
	 */
	public void setAnnotatedDocument(MerkenFile annotatedDocument) {
		this.annotatedDocument = annotatedDocument;
	}


	/**
	 * @return the pages
	 */
	public List<Page> getPages() {
		return pages;
	}


	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}


	public void setOriginalNumPages(Integer numPages) {
		this.originalNumPages = numPages;
		
	}
	
	
}
