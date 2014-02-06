package cl.adepti.merkenacuatico.data;

import java.util.List;




import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.Student;

@Repository
public interface ExamRepository extends MongoRepository<Exam,ObjectId> {
    public Exam findById(ObjectId objectId);
    public List<Exam> findByBluePrintAndStudent(BluePrint bluePrint,Student lastName);
}
