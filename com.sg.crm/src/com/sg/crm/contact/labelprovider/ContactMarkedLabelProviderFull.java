package com.sg.crm.contact.labelprovider;

import org.bson.types.BasicBSONList;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.crm.Activator;
import com.sg.crm.ImageResource;
import com.sg.crm.contact.data.Contact;
import com.sg.util.Utils;
import com.sg.util.file.FileUtil;

public class ContactMarkedLabelProviderFull extends ColumnLabelProvider {

	public ContactMarkedLabelProviderFull() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		DBObject contact = (DBObject) element;

		StringBuilder builder = new StringBuilder();

		// 头像
		Object text = contact.get(Contact.FIELD_PHOTO);
		if ((text instanceof BasicBSONList)
				&& ((BasicBSONList) text).size() > 0) {
			DBObject photo = (DBObject) ((BasicBSONList) text).get(0);
			String image = FileUtil.getImageURL(photo);
			if (image != null) {
				builder.append("<img src='");
				builder.append(image);
				builder.append("' style='float:left' width='72' height='72' />");
			}

		}

		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:微软雅黑;font-size:11pt'><b>");

		text = contact.get(Contact.FIELD_NAME);
		builder.append(text);
		builder.append("  ");
		// 部门
		text = contact.get(Contact.FIELD_DEPT);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		// 职位
		text = contact.get(Contact.FIELD_POSITION);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		builder.append("</b><br/>");

		builder.append("<small>");
		// 公司,在编辑器中显示
		text = contact.get(Contact.FIELD_COMPANY_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("公司: ");
			builder.append("</b>");
			builder.append(text);
			builder.append("  ");
		}
		builder.append("<br/>");

		text = contact.get(Contact.FIELD_TEL_1);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src=\"");
			String imageURL = FileUtil.getImageURL(ImageResource.TEL,
					Activator.PLUGIN_ID);
			builder.append(imageURL);
			builder.append("\"  width='12' height='12' style='padding:4px;'/>");
			builder.append("  ");
			builder.append(text);
			builder.append("  ");
		}

		text = contact.get(Contact.FIELD_TEL_2);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src=\"");
			String imageURL = FileUtil.getImageURL(ImageResource.MP,
					Activator.PLUGIN_ID);
			builder.append(imageURL);
			builder.append("\"  width='12' height='12' style='padding:4px;'/>");
			builder.append("  ");
			builder.append(text);
			builder.append("  ");
		}

		text = contact.get(Contact.FIELD_FAX);
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

		text = contact.get(Contact.FIELD_EMAIL);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src=\"");
			String imageURL = FileUtil.getImageURL(ImageResource.EMAIL,
					Activator.PLUGIN_ID);
			builder.append(imageURL);
			builder.append("\"  width='12' height='12' style='padding:4px;'/>");
			builder.append("  ");
			builder.append(" <a href='mailto:" + text + "' target='_black'>"
					+ text + "</a>");
			builder.append("  ");
		}

		text = contact.get(Contact.FIELD_QQ);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("QQ: ");
			builder.append("</b>");
			builder.append(text);
		}

		builder.append("   ");

		Object prov = contact.get(Contact.FIELD_PROV);
		Object city = contact.get(Contact.FIELD_CITY);
		Object address = contact.get(Contact.FIELD_ADDRESS);
		Object postcode = contact.get(Contact.FIELD_POSTCODE);
		if (Utils.isNullOrEmptyString(prov) && Utils.isNullOrEmptyString(city)
				&& Utils.isNullOrEmptyString(address)
				&& Utils.isNullOrEmptyString(postcode)) {
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

		builder.append("</small>");
		builder.append("</span>");

		return builder.toString();
	}

}
