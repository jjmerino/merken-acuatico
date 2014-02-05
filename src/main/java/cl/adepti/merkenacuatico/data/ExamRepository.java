package cl.adepti.merkenacuatico.data;

import java.util.List;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.Student;

@Repository
public interface ExamRepository extends MongoRepository<Exam,Integer> {
    public Exam findById(Integer firstName);
    public List<Exam> findByBluePrintAndStudent(BluePrint bluePrint,Student lastName);
}
