package org.brodner.alfresco.tomongo.scripts;

import org.alfresco.dataprep.SiteService;
import org.brodner.alfresco.tomongo.MongoDB;
import org.brodner.alfresco.tomongo.RunnerContext;

public class Runner extends RunnerContext {

	public static void main(String[] args) throws Exception {
		SiteService siteService = (SiteService) getContext().getBean(
				"siteService");
		MongoDB mongoDB = (MongoDB) getContext().getBean(MongoDB.class);

		SiteOperationOnUser.createSitesInMongoDBFromUsers(new String[] {"0000000.Test@00000.example.com", "0000000.Test@00000.example.com" }, siteService, mongoDB);

	}
}
