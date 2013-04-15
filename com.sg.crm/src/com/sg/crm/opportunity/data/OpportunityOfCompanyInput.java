package com.sg.crm.opportunity.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.crm.contact.data.Contact;
import com.sg.ui.model.input.BusinessObjectRelationListInput;

public class OpportunityOfCompanyInput extends BusinessObjectRelationListInput{

	@Override
	protected String getBindField() {
		return Contact.FIELD_COMPANY_ID;
	}

	@Override
	protected AuthCollectionService getService() {
		return new Opportunity();
	}

}
