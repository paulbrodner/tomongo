package org.brodner.alfresco.tomongo;

import java.util.Calendar;

import org.alfresco.dataprep.SiteService;
import org.brodner.alfresco.tomongo.scripts.SiteOperationOnUser;

import com.mongodb.MongoClient;

public class Runner extends RunnerContext {

	static String adminTicket;
	static MongoDB mongoDB;
	static SiteService siteService;

	public static void main(String[] args) throws Exception {
		siteService = (SiteService) getContext().getBean("siteService");
		mongoDB = (MongoDB) getContext().getBean(MongoDB.class);
		adminTicket = siteService.getTicket(mongoDB.alfrescoAdmin,mongoDB.alfrescoPassword);
		
		System.out.println("Start:" + Calendar.getInstance().getTime().toString());
		MongoClient mongoClient = mongoDB.getClient();
		SiteOperationOnUser.generateSitesAndSiteMembersForUserMirrorCollection(adminTicket, siteService, mongoDB, mongoDB.mongoDBUsersMirrorValue);
		System.out.println("END" + Calendar.getInstance().getTime().toString());
		mongoClient.close();
	}
}
