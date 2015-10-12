package org.brodner.alfresco.tomongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:tomongo.properties")
public class MongoDB {

	@Autowired
	private Environment env;

	@Bean
	public MongoClient getClient() {
		MongoClient mongoClient = new MongoClient(
				env.getProperty("mongodb.url"), Integer.valueOf(env
						.getProperty("mongodb.port")));

		return mongoClient;
	}

	@Value("${mongodb.collection}")
	public String mongoDBCollectionValue;
	

	@Value("${mongodb.database}")
	public String mongoDBDatabaseValue;

}
