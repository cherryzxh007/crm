package com.sg.crm.company.control;

import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredSelection;

import com.mobnut.portal.user.UserSessionContext;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sg.crm.company.data.Company;
import com.sg.ui.part.ISummaryProvider;
import com.sg.ui.part.Navigator;
import com.sg.ui.viewer.table.CTableViewer;

public class CustomerNavigatorSummary implements ISummaryProvider {

	private Company companyService;
	private String accountName;

	public CustomerNavigatorSummary() {
		companyService = new Company();
		try {
			accountName = UserSessionContext.getSession().getUserId();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getTitle(Navigator navigatable) {
		return "������Ĺ�˾��¼";
	}

	@Override
	public String getSummary(Navigator navigatable) {
		// ͳ��A�๫˾
		DBObject query = new BasicDBObject();
		query.put(Company.FIELD_LEVL, "A");
		int cntA = 0;
		try {
			cntA = companyService.findWithAuthorize(query, accountName).count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = new BasicDBObject();
		query.put(Company.FIELD_LEVL, "B");
		int cntB = 0;
		try {
			cntB = companyService.findWithAuthorize(query, accountName).count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		query = new BasicDBObject();
		query.put(Company.FIELD_CUSTOMER_LEVEL, Boolean.TRUE);
		int cntCust = 0;
		try {
			cntCust = companyService.findWithAuthorize(query, accountName)
					.count();
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		sb.append("������������, A����˾:");
		sb.append(cntA);
		sb.append(", ");
		sb.append("B����˾");
		sb.append(cntB);
		sb.append(", ");
		sb.append("�ͻ�:");
		sb.append(cntCust);

		CTableViewer viewer = navigatable.getViewer();
		if (!viewer.getSelection().isEmpty()) {
			int sntA = 0;
			int sntB = 0;
			int sntCust = 0;

			IStructuredSelection is = (IStructuredSelection) viewer
					.getSelection();
			Iterator<?> iter = is.iterator();
			while (iter.hasNext()) {
				DBObject row = (DBObject) iter.next();
				Object level = row.get(Company.FIELD_LEVL);
				if ("A".equals(level)) {
					sntA++;
				} else if ("B".equals(level)) {
					sntB++;
				}
				if (Boolean.TRUE.equals(row.get(Company.FIELD_CUSTOMER_LEVEL))) {
					sntCust++;
				}
			}

			sb.append("  ");
			sb.append("ѡ���¼�У�");
			sb.append("A����˾:");
			sb.append(sntA);
			sb.append(", ");
			sb.append("B����˾");
			sb.append(sntB);
			sb.append(", ");
			sb.append("�ͻ�:");
			sb.append(sntCust);

		}

		return sb.toString();
	}

}
