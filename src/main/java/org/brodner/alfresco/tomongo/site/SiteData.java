package org.brodner.alfresco.tomongo.site;

import org.alfresco.bm.data.DataCreationState;
import org.bson.Document;
import org.json.simple.JSONObject;

import com.mongodb.client.MongoCollection;

public class SiteData extends org.alfresco.bm.site.SiteData {

	private static final long serialVersionUID = 9068012092479061566L;

	public SiteData(JSONObject jsonObject) {
		setGuid(jsonObject.get("node").toString());
		setDomain("default");
		setPath(null);
		setTitle(jsonObject.get("title").toString());
		setSiteId(getTitle());
		setDescription(jsonObject.get("description").toString());
		setVisibility(jsonObject.get("visibility").toString());
	}

	public Document toDocument() {
		return new Document()
				.append(FIELD_SITE_ID, getTitle())
				.append(FIELD_GUID, getGuid())
				.append(FIELD_DOMAIN, getDomain())
				.append(FIELD_PATH, getPath())
				.append(FIELD_PRESET, "preset")
				.append(FIELD_TITLE, getTitle())
				.append(FIELD_DESC, getDescription())
				.append(FIELD_VISIBILITY, getVisibility())
				.append(FIELD_RANDOMIZER, getRandomizer())
				.append(FIELD_CREATION_STATE, DataCreationState.Created.toString());
	}

	public void addToMongoCollection(MongoCollection<Document> collection) {
		System.out.println("Writting:" + getTitle());
		collection.insertOne(toDocument());
	}
}
