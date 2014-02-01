package cl.adepti.merkenacuatico.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Manager;
public interface BluePrintRepository extends MongoRepository<BluePrint,Integer> {
    public BluePrint findById(Integer firstName);
    public List<BluePrint> findByOwner(Manager lastName);
}
