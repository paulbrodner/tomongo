package org.brodner.alfresco.tomongo.site;

import org.bson.Document;
import org.json.simple.JSONObject;

public class SiteMemberData extends org.alfresco.bm.site.SiteMemberData {

	private static final long serialVersionUID = 3065864667652223180L;

	public SiteMemberData(JSONObject jsonObject) {
		setUsername(jsonObject.get("").toString());
	}

	public Document toDocument() {
		return new Document()
			.append(FIELD_USERNAME, getUsername())
			.append(FIELD_SITE_ID, getSiteId())
			.append(FIELD_ROLE, getRole())
			.append(FIELD_CREATION_STATE, getCreationState())
			.append(FIELD_RANDOMIZER, getRandomizer());
	}
}
