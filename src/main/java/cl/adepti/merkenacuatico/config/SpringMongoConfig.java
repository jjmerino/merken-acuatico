package cl.adepti.merkenacuatico.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import cl.adepti.merkenacuatico.data.FileStorageService;
import cl.adepti.merkenacuatico.data.StorageService;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
@EnableMongoRepositories(basePackages = "cl.adepti.merkenacuatico.data")
public class SpringMongoConfig extends AbstractMongoConfiguration{
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {

		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
 
	@Override
	protected String getDatabaseName() {
		return "merken-acuatico";
	}

	@Bean
	StorageService documentStorageService() {
		return new FileStorageService();
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