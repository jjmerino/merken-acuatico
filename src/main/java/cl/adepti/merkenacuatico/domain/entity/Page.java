package cl.adepti.merkenacuatico.domain.entity;


import java.util.List;

import org.bson.types.ObjectId;

public class Page {
	private String fileId;
	private Integer index;
	private List<Annotation> annotations;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}


	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * @return the annotations
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}
	/**
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
}
