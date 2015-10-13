package org.brodner.alfresco.tomongo.site;

import org.alfresco.bm.data.DataCreationState;
import org.bson.Document;
import org.json.simple.JSONObject;

import com.mongodb.client.MongoCollection;

public class SiteMemberData extends org.alfresco.bm.site.SiteMemberData implements ToMongoData {

	private static final long serialVersionUID = 3065864667652223180L;

	public SiteMemberData(JSONObject jsonObject) throws Exception {
		JSONObject authority = (JSONObject) jsonObject.get("authority");
		if(authority.get("userName")==null){
			throw new Exception("Username is null so this object will be skipped");
		}
		setUsername(authority.get("userName").toString());
		setSiteId(jsonObject.get("siteID").toString());
		setRole(jsonObject.get("role").toString());
		setCreationState(DataCreationState.Created);
	}

	
	@Override
	public Document toDocument() {
		return new Document().append(FIELD_USERNAME, getUsername())
				.append(FIELD_SITE_ID, getSiteId())
				.append(FIELD_ROLE, getRole())
				.append(FIELD_CREATION_STATE, getCreationState().toString())
				.append(FIELD_RANDOMIZER, getRandomizer());
	}

	@Override
	public void addToMongoCollection(MongoCollection<Document> collection) {
		if( getUsername()!=null || getUsername()!="null" ){
			//System.out.println("Writting:" + getUsername() + " to Collection: " + collection.getNamespace());
			collection.insertOne(toDocument());			
		}
	}

}
