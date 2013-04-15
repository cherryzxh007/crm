package com.sg.crm.contact.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectListInput;

public class ContactInput extends BusinessObjectListInput {

		@Override
	protected AuthCollectionService getService() {
		return new Contact();
	}

	/* (non-Javadoc)
	 * @see com.sg.ui.model.input.BusinessObjectListInput#getSortBy()
	 */
	@Override
	protected DBObject getSortBy() {
		return new BasicDBObject().append(Contact.FIELD_NAME, 1);
	}

}
