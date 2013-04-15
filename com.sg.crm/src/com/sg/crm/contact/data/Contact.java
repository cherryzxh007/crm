package com.sg.crm.contact.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class Contact extends AuthCollectionService {


	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_CITY =   "city";
	public static final String FIELD_COMPANY =   "company";
	public static final String FIELD_COMPANY_ID =   "company_id";
	public static final String FIELD_COMPANY_NAME =   "company_name";
	public static final String FIELD_DEPT =   "dept";
	public static final String FIELD_NAME =   "desc";
	public static final String FIELD_NAME_E =   "desc_e";
	public static final String FIELD_DIST =   "dist";
	public static final String FIELD_EMAIL =   "email";
	public static final String FIELD_FAX =   "fax";
	public static final String FIELD_FIRSTNAME =   "firstname";
	public static final String FIELD_LASTNAME =   "lastname";
	public static final String FIELD_MSN =   "msn";
	public static final String FIELD_OWNER_NAME =   "owner_name";
	public static final String FIELD_OWNER_ID =   "owner_id";
	public static final String FIELD_OWNER =   "owner_id";
	public static final String FIELD_POSITION =   "position";
	public static final String FIELD_POSTCODE =   "postcode";
	public static final String FIELD_PROV =   "prov";
	public static final String FIELD_QQ =   "qq";
	public static final String FIELD_TEL_1 =   "tel_1" ;
	public static final String FIELD_TEL_2 =   "tel_2" ;
	public static final String FIELD_WEBSITE =   "website";
	public static final String FIELD_NAME_CARD =   "namecard";
	public static final String FIELD_PHOTO =   "photo";
	public static final String FIELD_SKYPE =   "skype";
	public static final String FIELD_BLOG =   "blog";
	public static final String FIELD_WEIBO =   "weibo";
	
	@Override
	protected String getCollectionName() {
		return "contact";
	}

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	public DBObject getDefaultSearchColumns() {
		BasicDBObject fields = new BasicDBObject();
	
		fields.put(FIELD_ADDRESS, 1);
		fields.put(FIELD_CITY, 1);
		fields.put(FIELD_COMPANY, 1);
		fields.put(FIELD_COMPANY_ID, 1);
		fields.put(FIELD_COMPANY_NAME, 1);
		fields.put(FIELD_DEPT, 1);
		fields.put(FIELD_NAME, 1);
		fields.put(FIELD_NAME_E, 1);
		fields.put(FIELD_DIST, 1);
		fields.put(FIELD_EMAIL, 1);
		fields.put(FIELD_FAX, 1);
		fields.put(FIELD_FIRSTNAME, 1);
		fields.put(FIELD_LASTNAME, 1);
		fields.put(FIELD_MSN, 1);
		fields.put(FIELD_OWNER_NAME, 1);
		fields.put(FIELD_OWNER_ID, 1);
		fields.put(FIELD_OWNER, 1);
		fields.put(FIELD_POSITION, 1);
		fields.put(FIELD_POSTCODE, 1);
		fields.put(FIELD_PROV, 1);
		fields.put(FIELD_QQ, 1);
		fields.put(FIELD_TEL_1, 1);
		fields.put(FIELD_TEL_2, 1);
		fields.put(FIELD_WEBSITE, 1);
		fields.put(FIELD_NAME_CARD, 1);
		fields.put(FIELD_PHOTO, 1);

		return fields;
	}
}
