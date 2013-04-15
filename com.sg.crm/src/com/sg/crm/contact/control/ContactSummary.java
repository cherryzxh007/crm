package com.sg.crm.contact.control;

import com.sg.ui.part.ISummaryProvider;
import com.sg.ui.part.Navigator;

public class ContactSummary implements ISummaryProvider {


	public ContactSummary() {

	}

	@Override
	public String getTitle(Navigator navigatable) {
		return "联系人名单";
	}

	@Override
	public String getSummary(Navigator navigatable) {

		return "";
	}

}
