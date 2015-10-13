package org.brodner.alfresco.tomongo.scripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.alfresco.dataprep.SiteService;
import org.brodner.alfresco.tomongo.MongoDB;
import org.brodner.alfresco.tomongo.RunnerContext;
import org.brodner.alfresco.tomongo.site.SiteData;
import org.brodner.alfresco.tomongo.site.SiteMemberData;
import org.bson.Document;
import org.json.simple.JSONObject;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SiteOperationOnUser extends RunnerContext {
	
	static ArrayList<String> tempSites = new ArrayList<String>();
	
	public static void generateSitesAndSiteMembersForUserMirrorCollection(
			final String adminTicket,
			final SiteService siteService,
			final MongoDB mongoDB, String userMirrorCollection){
		
		MongoDatabase db = mongoDB.getClient().getDatabase(mongoDB.mongoDBDatabaseValue);
		MongoCollection<Document> userCollection = db.getCollection(mongoDB.mongoDBUsersMirrorValue);

		//get the iterable documents from collection
		FindIterable<Document> iterable = userCollection.find();

		iterable.forEach(new Block<Document>() {
			/*
			 * for each user generate the sites and for each site generate the siteMembers
			 */
			@Override
			public void apply(final Document document) {
				String[] credentials = new String[] { document.get("username").toString(), document.get("password").toString() };
				try {
					SiteOperationOnUser.generateSitesAndSiteMembersFromUser(credentials, adminTicket, siteService, mongoDB);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void generateSitesAndSiteMembersFromUser(String[] userNameAndPassword,
			String adminTicket,
			SiteService siteService,
			MongoDB mongoDB) throws Exception{
		
		// get all sites for this user
		List<JSONObject> sites = siteService.getSitesForUser(userNameAndPassword[0], userNameAndPassword[1]);

		MongoClient mongoClient = mongoDB.getClient();
		MongoDatabase db = mongoClient.getDatabase(mongoDB.mongoDBDatabaseValue);

		MongoCollection<Document> siteCollection = db.getCollection(mongoDB.mongoDBSitesCollectionValue);

		//generate all sites for this user
		for (Iterator<JSONObject> iterator = sites.iterator(); iterator.hasNext();) {
			SiteData siteData = new SiteData((JSONObject) iterator.next());
			if(tempSites.contains(siteData.getSiteId())){
				continue;
			}
			else {
				tempSites.add(siteData.getSiteId());
			}
			
			siteData.addToMongoCollection(siteCollection);
			
			List<JSONObject> siteMembershipts = siteService.getSiteMemberships(adminTicket, siteData.getSiteId());
			MongoCollection<Document> siteMembersCollection = db.getCollection(mongoDB.mongoDBSiteMembersCollectionValue);
			
			//generate all siteMembershipt for this site
			for (Iterator<JSONObject> siteMemberIterator = siteMembershipts.iterator(); siteMemberIterator.hasNext();) {
				try {
					SiteMemberData siteMemberData = new SiteMemberData((JSONObject) siteMemberIterator.next());
					siteMemberData.addToMongoCollection(siteMembersCollection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
	}
}
