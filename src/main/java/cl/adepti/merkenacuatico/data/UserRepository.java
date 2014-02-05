package cl.adepti.merkenacuatico.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.adepti.merkenacuatico.domain.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

	public User findByUsername( String username);
}
