package com.sg.crm.company.editor;

import com.mongodb.DBObject;
import com.sg.crm.company.data.Company;
import com.sg.ui.model.IURLProvider;

public class CompanyURLProvider implements IURLProvider {

	public CompanyURLProvider() {
	}

	@Override
	public String getURL(DBObject data) {
		return (String) data.get(Company.FIELD_WEBSITE);
	}

}
