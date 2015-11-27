package org.brodner.alfresco.tomongo;

import java.util.ArrayList;
import java.util.Calendar;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CopyDatabase extends RunnerContext {

	static MongoDB mongoDB;
	static MongoClient targetClient;
	static MongoClient sourceClient;
	
	static String sourceDatabase = "bm20-ace198";
	static String sourceCollection = "mirrors.alf01.filefolders";
	
	static String targetMongoServer = "localhost";
	static String targetDatabase = "bm20-data";
	

	public static void main(String[] args) throws Exception {
		mongoDB = (MongoDB) getContext().getBean(MongoDB.class);

		System.out.println("Start:"+ Calendar.getInstance().getTime().toString());
		sourceClient = mongoDB.getClient();

		MongoDatabase db = sourceClient.getDatabase(sourceDatabase);
		MongoCollection<Document> collection = db.getCollection(sourceCollection);

		FindIterable<Document> iterable = collection.find();

		final ArrayList<Document> docObjects = new ArrayList<Document>();
		iterable.forEach(new Block<Document>() {

			public void apply(Document t) {
				docObjects.add(t);
			}
		});

		targetClient = new MongoClient(targetMongoServer, 27017);
		MongoDatabase targetDB = targetClient.getDatabase(targetDatabase);
		
		targetDB.createCollection(sourceCollection);
		
		MongoCollection<Document> targetCollection = targetDB.getCollection(sourceCollection);
		targetCollection.insertMany(docObjects);
		
		
		System.out.println("END" + Calendar.getInstance().getTime().toString());
		targetClient.close();
		sourceClient.close();
	}
}
