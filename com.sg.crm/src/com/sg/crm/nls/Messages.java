package com.sg.crm.nls;

import org.eclipse.rap.rwt.RWT;

public class Messages {
	private static final String BUNDLE_NAME = "com.sg.crm.nls.messages"; //$NON-NLS-1$
	public String perspective_home;
	public String perspective_customer;
	public String perspective_campaign;
	public String perspective_opportunity;
	public String perspective_address_book;
	public String perspective_doc;
	public String perspective_contract;
	public String perspective_team;
	public String perspective_financial;
	public String perspective_setting;
	public String view_home;
	public String view_customer_navigator;
	public String view_industry_navigator;
	public String view_section_navigator;
	public String view_opportunities;
	public String view_activities;
	public String view_cost;
	public String view_contract;
	public String view_contact;
	public String view_mydoc;
	public String view_teamdoc;
	public String view_myfav;
	public String view_latestopened;
	public String view_myopportunities;
	public String view_mycontract;

	public static Messages get() {
		return RWT.NLS.getISO8859_1Encoded(BUNDLE_NAME, Messages.class);
	}

}
