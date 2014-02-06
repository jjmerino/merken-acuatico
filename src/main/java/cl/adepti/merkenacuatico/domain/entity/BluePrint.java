package cl.adepti.merkenacuatico.domain.entity;

import java.io.File;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.data.annotation.Id;

/**
 * Also known as a Print order
 * @author Jose
 *
 */
public class BluePrint {
	@Id
	private BigInteger id;
	private String name;
	private Date creationDate;
	private Date printDate;
	private Date examDate;
	private MerkenFile sourceFile;
	private Manager owner;
	private Integer extraSheets;
	private Integer extraExams;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @return the printDate
	 */
	public Date getPrintDate() {
		return printDate;
	}
	/**
	 * @return the examDate
	 */
	public Date getExamDate() {
		return examDate;
	}
	/**
	 * @return the sourceFile
	 */
	public MerkenFile getSourceFile() {
		return sourceFile;
	}
	/**
	 * @return the owner
	 */
	public Manager getOwner() {
		return owner;
	}
	/**
	 * @return the extraSheets
	 */
	public Integer getExtraSheets() {
		return extraSheets;
	}
	/**
	 * @return the extraExams
	 */
	public Integer getExtraExams() {
		return extraExams;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @param printDate the printDate to set
	 */
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	/**
	 * @param examDate the examDate to set
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	/**
	 * @param sourceFile the sourceFile to set
	 */
	public void setSourceFile(MerkenFile sourceFile) {
		this.sourceFile = sourceFile;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Manager owner) {
		this.owner = owner;
	}
	/**
	 * @param extraSheets the extraSheets to set
	 */
	public void setExtraSheets(Integer extraSheets) {
		this.extraSheets = extraSheets;
	}
	/**
	 * @param extraExams the extraExams to set
	 */
	public void setExtraExams(Integer extraExams) {
		this.extraExams = extraExams;
	}
	/**
	 * @param students the students to set
	 */
	public void setStudents(Collection<Student> students) {
		this.students = students;
	}
	private Collection<Student> students = new LinkedList<Student>();
	public BigInteger getId() {
		return this.id;
	}
	public Collection<Student> getStudents() {
		return this.students;
	}

}
