package com.sg.crm.action.data;

import java.util.List;

import org.bson.types.ObjectId;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectRelationListInput;

public class ActionOfOpportunityInput extends BusinessObjectRelationListInput {

	private ObjectId opportunityId;
	private ObjectId companyId;

	public ActionOfOpportunityInput(ObjectId opportunityId, ObjectId companyId) {
		this.opportunityId = opportunityId;
		this.companyId = companyId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sg.ui.model.input.BusinessObjectRelationListInput#doQuery(com.mongodb
	 * .DBObject)
	 */
	@Override
	protected List<DBObject> doQuery(DBObject returnFields) {
		if (service == null) {
			service = getService();
		}
		DBObject projection = returnFields == null ? service
				.getDefaultSearchColumns() : returnFields;

		try {
			BasicDBList list = new BasicDBList();
			list.add(new BasicDBObject().append(Action.FIELD_OPPORTUNIY_ID, opportunityId));
			list.add(new BasicDBObject().append(Action.FIELD_OPPORTUNIY_ID, null));
			
			BasicDBObject query = new BasicDBObject().append(
					Action.FIELD_COMPANY_ID, companyId).append(Action.OR, list);

			DBCursor cursor = service.find(query, projection);
			DBObject sort = getSort();
			cursor.sort(sort);
			return cursor.toArray();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String getBindField() {
		return Action.FIELD_OPPORTUNIY_ID;
	}

	@Override
	protected DBObject getSort() {
		return new BasicDBObject().append(Action.FIELD_PLANSTART, -1);
	}

	@Override
	protected AuthCollectionService getService() {
		return new Action();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sg.ui.model.input.BusinessObjectRelationListInput#getInputId()
	 */
	@Override
	protected ObjectId getInputId() {
		if (opportunityId != null) {
			return opportunityId;
		}
		return super.getInputId();
	}

}
