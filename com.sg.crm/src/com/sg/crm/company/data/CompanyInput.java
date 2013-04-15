package com.sg.crm.company.data;

import com.mobnut.db.collection.AuthCollectionService;
import com.sg.ui.model.input.BusinessObjectListInput;

public class CompanyInput extends BusinessObjectListInput {

	@Override
	protected AuthCollectionService getService() {
		return new Company();
	}

}
