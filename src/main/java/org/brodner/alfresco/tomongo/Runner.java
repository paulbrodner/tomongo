package org.brodner.alfresco.tomongo;

import org.alfresco.dataprep.SiteService;
import org.brodner.alfresco.tomongo.scripts.SiteOperationOnUser;

public class Runner extends RunnerContext {

	public static void main(String[] args) throws Exception {
		SiteService siteService = (SiteService) getContext().getBean("siteService");
		MongoDB mongoDB = (MongoDB) getContext().getBean(MongoDB.class);

		SiteOperationOnUser.createSitesInMongoDBFromUsers(new String[] {"jmuser00001", "jmuser00001" }, siteService, mongoDB);
	}
}
