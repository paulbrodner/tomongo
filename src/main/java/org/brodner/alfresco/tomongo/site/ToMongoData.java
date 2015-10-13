package org.brodner.alfresco.tomongo.site;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface ToMongoData {

	public Document toDocument();

	public void addToMongoCollection(MongoCollection<Document> collection);
}
