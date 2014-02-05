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
	private File sourceFile;
	private Manager owner;
	private Integer extraSheets;
	private Integer extraExams;
	private Collection<Student> students = new LinkedList<Student>();
	public BigInteger getId() {
		return this.id;
	}

}
