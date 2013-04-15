package com.sg.crm.company.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.crm.Activator;
import com.sg.crm.ImageResource;
import com.sg.crm.company.data.Company;
import com.sg.util.Utils;
import com.sg.util.file.FileUtil;

public class CompanyMarkedLabelProvider extends ColumnLabelProvider {

	public CompanyMarkedLabelProvider() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		DBObject company = (DBObject) element;

		StringBuilder builder = new StringBuilder();

		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:微软雅黑;font-size:11pt'><b>");

		// Object text = company.get(Company.FIELD_DESC_S);
		// // 如果能够取到简称，则使用简称，否则使用全称
		// if (Utils.isNullOrEmptyString(text)) {
		Object text = company.get(Company.FIELD_DESC);
		// }

		if (Company.VALUE_COMPANY_STATUS_CLOSED.equals(company
				.get(Company.FIELD_COMPANY_STATUS))) {// 显示删除线
			builder.append("<del>");
			builder.append(text);
			builder.append("</del>");
		} else {
			builder.append(text);
		}
		builder.append("   </b>");

		// 客户经理
		text = company.get(Company.FIELD_MANAGER_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append(" ");
		}

		// 显示客户的级别
		text = company.get(Company.FIELD_LEVL);
		if (!Utils.isNullOrEmptyString(text)) {
			String starImageUrl = null;
			if ("A".equalsIgnoreCase(text.toString())) {
				starImageUrl = FileUtil.getImageURL(ImageResource.A,
						Activator.PLUGIN_ID);

			} else if ("B".equalsIgnoreCase(text.toString())) {
				starImageUrl = FileUtil.getImageURL(ImageResource.B,
						Activator.PLUGIN_ID);

			} else if ("C".equalsIgnoreCase(text.toString())) {
				starImageUrl = FileUtil.getImageURL(ImageResource.C,
						Activator.PLUGIN_ID);

			} else if ("D".equalsIgnoreCase(text.toString())) {
				starImageUrl = FileUtil.getImageURL(ImageResource.D,
						Activator.PLUGIN_ID);

			} else if ("X".equalsIgnoreCase(text.toString())) {
				starImageUrl = FileUtil.getImageURL(ImageResource.X,
						Activator.PLUGIN_ID);

			}
			if (starImageUrl != null) {
				builder.append("<img src=\"");
				builder.append(starImageUrl);
				builder.append("\"  width='16' height='16' style='padding-right:4px;padding-top:4px;'/>");
				builder.append("  ");
			}
		}

		// 显示是否是老客户
		text = company.get(Company.FIELD_CUSTOMER_LEVEL);
		String image = null;
		if ("战略客户".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_A,
					Activator.PLUGIN_ID);
		} else if ("大客户".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_B,
					Activator.PLUGIN_ID);
		} else if ("普通客户".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_C,
					Activator.PLUGIN_ID);
		} else if ("准客户".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_D,
					Activator.PLUGIN_ID);
		} else if ("黑名单".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_X,
					Activator.PLUGIN_ID);
		}
		if (image != null) {
			builder.append("<img src=\"");
			builder.append(image);
			builder.append("\"  width='32' height='16' style='padding-right:4px;padding-top:2px;'/>");
			builder.append("  ");
		}

		builder.append("<br/>");

		builder.append("<small>");

		// 如果能够取到行业，显示行业
		text = company.get(Company.FIELD_INDUSTRY);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("行业: ");
			builder.append("</b>");
			builder.append(text);
			builder.append("  ");
		}

		// 如果能够取到产品，显示产品
		text = company.get(Company.FIELD_PRODUCT);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("产品: ");
			builder.append("</b>");
			builder.append(text);
		}
		builder.append("<br/>");

		// builder.append("<b>");
		// builder.append("ADD: ");
		// builder.append("</b>");
		Object prov = company.get(Company.FIELD_PROV);
		Object city = company.get(Company.FIELD_CITY);
		Object address = company.get(Company.FIELD_ADDRESS);
		Object postcode = company.get(Company.FIELD_POSTCODE);
		if (Utils.isNullOrEmptyString(prov) && Utils.isNullOrEmptyString(city)
				&& Utils.isNullOrEmptyString(address)
				&& Utils.isNullOrEmptyString(postcode)) {
			// builder.append("不详");
		} else {
			if (!Utils.isNullOrEmptyString(prov)) {
				builder.append(prov);
			}
			if (!Utils.isNullOrEmptyString(city)) {
				builder.append("．");
				builder.append(city);
			}
			if (!Utils.isNullOrEmptyString(address)) {
				builder.append("．");
				builder.append(address);
			}
			if (!Utils.isNullOrEmptyString(postcode)) {
				builder.append("．");
				builder.append(postcode);
			}
		}
		builder.append(" ");

		Object tel1 = company.get(Company.FIELD_TEL_1);
		Object tel2 = company.get(Company.FIELD_TEL_2);
		if (!Utils.isNullOrEmptyString(tel1)
				|| !Utils.isNullOrEmptyString(tel2)) {
			builder.append("<img src=\"");
			String imageURL = FileUtil.getImageURL(ImageResource.TEL,
					Activator.PLUGIN_ID);
			builder.append(imageURL);
			builder.append("\"  width='12' height='12' style='padding:4px;'/>");
		}
		if (!Utils.isNullOrEmptyString(tel1)) {
			builder.append("  ");
			builder.append(tel1);
			builder.append("  ");
		}
		if (!Utils.isNullOrEmptyString(tel2)) {
			builder.append("  ");
			builder.append(tel2);
			builder.append("  ");
		}

		text = company.get(Company.FIELD_FAX);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src=\"");
			String imageURL = FileUtil.getImageURL(ImageResource.FAX,
					Activator.PLUGIN_ID);
			builder.append(imageURL);
			builder.append("\"  width='12' height='12' style='padding:4px;'/>");
			builder.append("  ");
			builder.append(text);
			builder.append("  ");
		}

		// web地址
		text = company.get(Company.FIELD_WEBSITE);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("  ");
			builder.append(" <a href='" + text + "' target='_black'>" + text
					+ "</a>");
			builder.append("  ");
		}

		builder.append("</small>");
		builder.append("</span>");

		return builder.toString();
	}

}
