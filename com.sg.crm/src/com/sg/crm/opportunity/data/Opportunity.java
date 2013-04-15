package com.sg.crm.opportunity.data;

import com.mobnut.portal.Portal;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.sg.ui.model.DataObjectCollectionService;

public class Opportunity extends DataObjectCollectionService {

	public Opportunity() {
		super();
	}

	public static final String FIELD_COMPANY_ID = "company_id";
	
	public static final String FIELD_COMPANY_NAME = "company_name";
	/**
	 * manager fieldConfigurator Account DBObject
	 */
	public static final String FIELD_MANAGER_NAME = "manager_name";

	public static final String FIELD_MANAGER_ID = "manager_id";
	
	public static final String FIELD_BUDGET = "budget";
	
	public static final String FIELD_PROGRESS = "progress";
	
	public static final String FIELD_REQ_DESC = "req_desc";
	
	public static final String FIELD_REQ_PROD = "req_prod";

	public static final String VALUE_PROGRESS_M10 = "识别需求";
	
	public static final String VALUE_PROGRESS_M20 = "预算规划";

	public static final String VALUE_PROGRESS_M30 = "交流选型";

	public static final String VALUE_PROGRESS_M40 = "备用1";

	public static final String VALUE_PROGRESS_M50 = "备用2";

	public static final String VALUE_PROGRESS_M60 = "备用3";
	
	public static final String VALUE_PROGRESS_M70 = "招标谈判";
	
	public static final String VALUE_PROGRESS_M80 = "签订合同";
	
	public static final String VALUE_PROGRESS_M90 = "启动实施";
	
	public static final String VALUE_PROGRESS_M00 = "项目结案";

	public static final String FIELD_PM_NAME = "pm_name";
	
	public static final String FIELD_PM_ID = "pm_id";
	
	public static final String FIELD_M10 = "m10";
	
	public static final String FIELD_M20 = "m20";
	
	public static final String FIELD_M30 = "m30";
	
	public static final String FIELD_M40 = "m40";
	
	public static final String FIELD_M50 = "m50";

	public static final String FIELD_M60 = "m60";
	
	public static final String FIELD_M70 = "m70";
	
	public static final String FIELD_M10_E = "m10_1";
	
	public static final String FIELD_M20_E = "m20_1";
	
	public static final String FIELD_M30_E = "m30_1";
	
	public static final String FIELD_M40_E = "m40_1";
	
	public static final String FIELD_M50_E = "m50_1";

	public static final String FIELD_M60_E = "m60_1";
	
	public static final String FIELD_M70_E = "m70_1";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobnut.db.collection.CollectionService#getCollectionName()
	 */
	@Override
	protected String getCollectionName() {
		return "opportunity";
	}

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	public DBObject getDefaultSearchColumns() {
		BasicDBObject fields = new BasicDBObject();
	
		fields.put(FIELD_DESC, 1);
		fields.put(FIELD_MANAGER_NAME, 1);
		fields.put(FIELD_MANAGER_ID, 1);
		fields.put(FIELD_PM_NAME, 1);
		fields.put(FIELD_PM_ID, 1);
		fields.put(FIELD_COMPANY_NAME, 1);
		fields.put(FIELD_PROGRESS, 1);
		fields.put(FIELD_BUDGET, 1);
		fields.put(FIELD_REQ_DESC, 1);
		fields.put(FIELD_REQ_PROD, 1);
		fields.put(FIELD_CREATEDATE, 1);
		fields.put(FIELD_CREATE_ACCOUNT, 1);
		
		fields.put(FIELD_M10, 1);
		fields.put(FIELD_M20, 1);
		fields.put(FIELD_M30, 1);
		fields.put(FIELD_M40, 1);
		fields.put(FIELD_M50, 1);
		fields.put(FIELD_M60, 1);
		fields.put(FIELD_M70, 1);
		fields.put(FIELD_M10_E, 1);
		fields.put(FIELD_M20_E, 1);
		fields.put(FIELD_M30_E, 1);
		fields.put(FIELD_M40_E, 1);
		fields.put(FIELD_M50_E, 1);
		fields.put(FIELD_M60_E, 1);
		fields.put(FIELD_M70_E, 1);
		
		return fields;
	}
}
