package com.sg.crm.contact.data;

import java.util.Comparator;

import com.mobnut.db.collection.AuthCollectionService;
import com.mongodb.DBObject;
import com.sg.ui.model.input.BusinessObjectRelationListInput;

public class ContactOfCompanyInput extends BusinessObjectRelationListInput{

	@Override
	protected String getBindField() {
		return Contact.FIELD_COMPANY_ID;
	}

	@Override
	protected AuthCollectionService getService() {
		return new Contact();
	}

	/* (non-Javadoc)
	 * @see com.sg.ui.model.input.BusinessObjectRelationListInput#getComparator()
	 */
	@Override
	protected Comparator<? super DBObject> getComparator() {
		return new PeopleNameComparator();
	}
	
	
}
