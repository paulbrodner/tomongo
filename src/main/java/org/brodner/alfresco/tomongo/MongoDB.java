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
	MongoClient mongoClient;

	@Autowired
	private Environment env;

	@Bean
	public MongoClient getClient() {
		if (mongoClient == null) {
			mongoClient = new MongoClient(env.getProperty("mongodb.url"),
					Integer.valueOf(env.getProperty("mongodb.port")));
		}

		return mongoClient;
	}

	@Value("${mongodb.sites.collection}")
	public String mongoDBSitesCollectionValue;

	@Value("${mongodb.siteMembers.collection}")
	public String mongoDBSiteMembersCollectionValue;

	@Value("${mongodb.mirrors.users}")
	public String mongoDBUsersMirrorValue;
	
	@Value("${mongodb.database}")
	public String mongoDBDatabaseValue;

	@Value("${alfresco.admin}")
	public String alfrescoAdmin;

	@Value("${alfresco.password}")
	public String alfrescoPassword;

}
