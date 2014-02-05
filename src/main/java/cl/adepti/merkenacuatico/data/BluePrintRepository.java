package cl.adepti.merkenacuatico.data;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.adepti.merkenacuatico.domain.entity.BluePrint;
import cl.adepti.merkenacuatico.domain.entity.Manager;

@Repository
public interface BluePrintRepository extends MongoRepository<BluePrint,Integer> {
    public BluePrint findById(BigInteger firstName);
    public List<BluePrint> findByOwner(Manager lastName);
}
