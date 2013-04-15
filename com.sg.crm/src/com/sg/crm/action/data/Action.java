package com.sg.crm.action.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.mobnut.portal.Portal;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class Action extends AuthCollectionService {

	public static final String FIELD_TEAM = "team";
	public static final String FIELD_ACTOR = "actor";
	public static final String FIELD_ACTOR_ID = "actor_id";
	public static final String FIELD_ACTOR_NAME = "actor_name";
	public static final String FIELD_COMPANY = "company";
	public static final String FIELD_COMPANY_ID = "company_id";
	public static final String FIELD_COMPANY_NAME = "company_name";
	public static final String FIELD_PLANFINISH = "pfinish";
	public static final String FIELD_PLANSTART = "pstart";
	public static final String FIELD_ACTUALFINISH = "afinish";
	public static final String FIELD_ACTUALSTART = "astart";
	public static final String FIELD_REMARK = "remark";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_MOME = "memo";
	public static final String FIELD_ACTION_IP = "ip";
	public static final String FIELD_ACTION_TP = "tp";
	public static final String FIELD_ACTION_STATUS = "status";
	public static final String FIELD_EVALUATION = "evaluation";
	public static final String FIELD_OPPORTUNIY_ID = "opportunity_id";
	public static final String FIELD_OPPORTUNIY_NAME = "opportunity_name";
	public static final String FIELD_CONTRACT_ID = "contract_id";
	public static final String FIELD_CONTRACT_NAME = "contract_name";
	public static final String FIELD_OPPORTUNIY_PROGRESS = "opportunity_progress";
	public static final String FIELD_ACTION_PROGRESS = "progress";

	@Override
	protected String getCollectionName() {
		return "action";
	}

	@Override
	public DB getDB() {
		return Portal.getBasicDB();
	}

	public DBObject getDefaultSearchColumns() {
		BasicDBObject fields = new BasicDBObject();

		fields.put(FIELD_TEAM, 1);
		fields.put(FIELD_ACTOR, 1);
		fields.put(FIELD_ACTOR_ID, 1);
		fields.put(FIELD_ACTOR_NAME, 1);
		fields.put(FIELD_COMPANY, 1);
		fields.put(FIELD_COMPANY_ID, 1);
		fields.put(FIELD_COMPANY_NAME, 1);
		fields.put(FIELD_PLANFINISH, 1);
		fields.put(FIELD_PLANSTART, 1);
		fields.put(FIELD_ACTUALFINISH, 1);
		fields.put(FIELD_ACTUALSTART, 1);
		fields.put(FIELD_REMARK, 1);
		fields.put(FIELD_DESC, 1);
		fields.put(FIELD_TYPE, 1);
		fields.put(FIELD_MOME, 1);
		fields.put(FIELD_ACTION_IP, 1);
		fields.put(FIELD_ACTION_TP, 1);
		fields.put(FIELD_ACTION_STATUS, 1);
		fields.put(FIELD_EVALUATION, 1);
		fields.put(FIELD_OPPORTUNIY_PROGRESS, 1);
		fields.put(FIELD_OPPORTUNIY_ID, 1);
		fields.put(FIELD_EVALUATION, 1);
		fields.put(FIELD_ACTION_PROGRESS, 1);

		return fields;
	}
}
