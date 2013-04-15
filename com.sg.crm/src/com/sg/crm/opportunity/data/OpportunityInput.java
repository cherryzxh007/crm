package com.sg.crm.opportunity.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;

public class OpportunityInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new Opportunity();
	}

}
