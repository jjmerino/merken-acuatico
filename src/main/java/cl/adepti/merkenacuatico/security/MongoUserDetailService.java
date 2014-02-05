package cl.adepti.merkenacuatico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cl.adepti.merkenacuatico.config.SpringMongoConfig;
import cl.adepti.merkenacuatico.data.UserRepository;
import cl.adepti.merkenacuatico.domain.entity.User;

public class MongoUserDetailService implements UserDetailsService{
	

	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
	    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		Query query = new Query();
		User user=null;
		try{
		query.addCriteria(Criteria.where("username").is(username));
		user = mongoOperation.findOne(query, User.class);
		}catch(Exception e){
			System.out.println("IS THISIISSTT");
			e.printStackTrace();
		}
		if(user==null){
			throw new UsernameNotFoundException("CAROLINA");
		}
		return user;
	}

	
}
