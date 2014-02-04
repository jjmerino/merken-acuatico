package cl.adepti.merkenacuatico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration{
 
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
 
	@Override
	protected String getDatabaseName() {
		return "merken-acuatico";
	}
 
	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("ds063307.mongolab.com:63307");
	}
	
	protected UserCredentials getUserCredentials() {
		return new UserCredentials("merken-acuatico","merkenhackeradepti");
	}
}