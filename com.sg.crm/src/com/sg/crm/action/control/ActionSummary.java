package com.sg.crm.action.control;

import com.sg.ui.part.ISummaryProvider;
import com.sg.ui.part.Navigator;

public class ActionSummary implements ISummaryProvider {

	public ActionSummary() {
	}

	@Override
	public String getTitle(Navigator navigatable) {
		return "������¼";
	}

	@Override
	public String getSummary(Navigator navigatable) {
		return null;
	}

}
