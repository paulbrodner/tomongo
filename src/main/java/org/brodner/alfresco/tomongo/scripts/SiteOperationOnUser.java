package org.brodner.alfresco.tomongo.scripts;

import java.util.Iterator;
import java.util.List;

import org.alfresco.dataprep.SiteService;
import org.brodner.alfresco.tomongo.MongoDB;
import org.brodner.alfresco.tomongo.RunnerContext;
import org.brodner.alfresco.tomongo.site.SiteData;
import org.bson.Document;
import org.json.simple.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SiteOperationOnUser extends RunnerContext {

	public static void createSitesInMongoDBFromUsers(
			String[] userNameAndPassword, 
			SiteService siteService,
			MongoDB mongoDB) throws Exception {

		// get all sites for this user
		List<JSONObject> sites = siteService.getSitesForUser(userNameAndPassword[0], userNameAndPassword[1]);

		MongoClient mongoClient = mongoDB.getClient();
		MongoDatabase db = mongoClient.getDatabase(mongoDB.mongoDBDatabaseValue);

		MongoCollection<Document> c = db.getCollection(mongoDB.mongoDBCollectionValue);

		for (Iterator<JSONObject> iterator = sites.iterator(); iterator.hasNext();) {
			SiteData siteData = new SiteData((JSONObject) iterator.next());
			siteData.addToMongoCollection(c);
		}
		mongoClient.close();
	}
}
