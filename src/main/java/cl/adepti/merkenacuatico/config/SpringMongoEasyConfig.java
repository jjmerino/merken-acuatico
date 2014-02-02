package cl.adepti.merkenacuatico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
 
@Configuration
public class SpringMongoEasyConfig {
	
	public @Bean
	UserCredentials userCredentials() throws Exception {
		return new UserCredentials("merken-acuatico","merkenhackeradepti");
	}
 
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("ds063307.mongolab.com:63307"), "merken-acuatico",userCredentials());
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
		return mongoTemplate;
 
	}
 
}