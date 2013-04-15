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
		return "您管理的公司记录";
	}

	@Override
	public String getSummary(Navigator navigatable) {
		// 统计A类公司
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
		sb.append("在您的名单中, A级公司:");
		sb.append(cntA);
		sb.append(", ");
		sb.append("B级公司");
		sb.append(cntB);
		sb.append(", ");
		sb.append("客户:");
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
			sb.append("选择记录中：");
			sb.append("A级公司:");
			sb.append(sntA);
			sb.append(", ");
			sb.append("B级公司");
			sb.append(sntB);
			sb.append(", ");
			sb.append("客户:");
			sb.append(sntCust);

		}

		return sb.toString();
	}

}
