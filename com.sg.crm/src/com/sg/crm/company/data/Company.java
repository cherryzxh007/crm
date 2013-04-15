package com.sg.crm.company.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class Company extends AuthCollectionService {

	public static final String FIELD_DESC_S = "desc_s";//简称
	
	public static final String FIELD_INDUSTRY = "industry";
	
	public static final String FIELD_PRODUCT = "product";
	
	public static final String FIELD_ANNUAL = "annual";
	
	public static final String FIELD_CAPITAL = "capital";
	
	public static final String FIELD_LEVL = "level";
	
	public static final String FIELD_PROV = "prov";
	
	public static final String FIELD_CITY = "city";
	
	public static final String FIELD_DIST = "dist";
	
	public static final String FIELD_ADDRESS = "address";
	
	public static final String FIELD_POSTCODE = "postcode";
	
	public static final String FIELD_TEL_1 = "tel_1";
	
	public static final String FIELD_TEL_2 = "tel_2";
	
	public static final String FIELD_FAX = "fax";
	
	public static final String FIELD_EMAIL = "email";
	
	public static final String FIELD_REGISTEDATE = "registedate";
	
	/**
	 * manager fieldConfigurator Account DBObject
	 */
	public static final String FIELD_MANAGER_NAME = "manager_name";

	public static final String FIELD_MANAGER_ID = "manager_id";

	public static final String FIELD_SN = "sn";

	/**
	 * 公司状态
	 */
	public static final String FIELD_COMPANY_STATUS = "status";
	public static final String VALUE_COMPANY_STATUS_CLOSED = "closed";
	

	/**
	 * String 客户级别
	 */
	public static final String FIELD_CUSTOMER_LEVEL = "levelservice";

	
	public static final String FIELD_WEBSITE = "website";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "company";
	}

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	public DBObject getDefaultSearchColumns() {
		BasicDBObject fields = new BasicDBObject();
	
		fields.put(FIELD_DESC, 1);
		fields.put(FIELD_DESC_S, 1);
		fields.put(FIELD_INDUSTRY, 1);
		fields.put(FIELD_PRODUCT, 1);
		fields.put(FIELD_ANNUAL, 1);
		fields.put(FIELD_CAPITAL, 1);
		fields.put(FIELD_LEVL, 1);
		fields.put(FIELD_PROV, 1);
		fields.put(FIELD_CITY, 1);
		fields.put(FIELD_DIST, 1);
		fields.put(FIELD_ADDRESS, 1);
		fields.put(FIELD_POSTCODE, 1);
		fields.put(FIELD_TEL_1, 1);
		fields.put(FIELD_TEL_2, 1);
		fields.put(FIELD_FAX, 1);
		fields.put(FIELD_EMAIL, 1);
		fields.put(FIELD_MANAGER_NAME, 1);
		fields.put(FIELD_CUSTOMER_LEVEL, 1);
		fields.put(FIELD_COMPANY_STATUS, 1);
		fields.put(FIELD_WEBSITE, 1);

		return fields;
	}
}
