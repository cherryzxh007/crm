package com.sg.crm.action.data;

import org.bson.types.ObjectId;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectRelationListInput;

public class ActionOfCompanyInput extends BusinessObjectRelationListInput {

	private ObjectId id;

	public ActionOfCompanyInput(ObjectId id) {
		this.id = id;
	}
	
	public ActionOfCompanyInput() {
	}
	

	@Override
	protected String getBindField() {
		return Action.FIELD_COMPANY_ID;
	}

	@Override
	protected DBObject getSort() {
		return new BasicDBObject().append(Action.FIELD_PLANSTART,-1);
	}

	@Override
	protected AuthCollectionService getService() {
		return new Action();
	}
	
	@Override
	protected ObjectId getInputId() {
		if(id!=null){
			return id;
		}
		return super.getInputId();
	}
}
