package cl.adepti.merkenacuatico.stubs;

import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;

import cl.adepti.merkenacuatico.domain.entity.Student;

public class StubGenerator {
	public List<Student> createStudents(int n){
		List<Student> students = new LinkedList<Student>();
		for(int i = 1; i<=n;i++){
			Student student = new Student();
			student.setId(new ObjectId());
			student.setUsername("student"+i);
			students.add(student);
		}
		
		return students;
	}
}
