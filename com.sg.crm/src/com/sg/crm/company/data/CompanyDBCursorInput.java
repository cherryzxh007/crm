package com.sg.crm.company.data;

import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sg.ui.model.input.InputProvider;

public class CompanyDBCursorInput extends InputProvider {

	private Company companyService;

	public CompanyDBCursorInput() {
		companyService = new Company();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sg.ui.viewer.InputProvider#getInitialInput()
	 */
	@Override
	protected Object getInitialInput() {
		DBObject projection = companyService.getDefaultSearchColumns();
		try {
			String accountName = UserSessionContext.getSession().getUserId();
			DBCursor cursor = companyService.findWithAuthorize(accountName,
					projection);
			return cursor.sort(new BasicDBObject().append(Company.FIELD_SYSID, -1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
