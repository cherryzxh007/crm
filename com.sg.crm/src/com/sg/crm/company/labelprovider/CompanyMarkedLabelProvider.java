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

		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:΢���ź�;font-size:11pt'><b>");

		// Object text = company.get(Company.FIELD_DESC_S);
		// // ����ܹ�ȡ����ƣ���ʹ�ü�ƣ�����ʹ��ȫ��
		// if (Utils.isNullOrEmptyString(text)) {
		Object text = company.get(Company.FIELD_DESC);
		// }

		if (Company.VALUE_COMPANY_STATUS_CLOSED.equals(company
				.get(Company.FIELD_COMPANY_STATUS))) {// ��ʾɾ����
			builder.append("<del>");
			builder.append(text);
			builder.append("</del>");
		} else {
			builder.append(text);
		}
		builder.append("   </b>");

		// �ͻ�����
		text = company.get(Company.FIELD_MANAGER_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append(" ");
		}

		// ��ʾ�ͻ��ļ���
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

		// ��ʾ�Ƿ����Ͽͻ�
		text = company.get(Company.FIELD_CUSTOMER_LEVEL);
		String image = null;
		if ("ս�Կͻ�".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_A,
					Activator.PLUGIN_ID);
		} else if ("��ͻ�".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_B,
					Activator.PLUGIN_ID);
		} else if ("��ͨ�ͻ�".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_C,
					Activator.PLUGIN_ID);
		} else if ("׼�ͻ�".equals(text)) {
			image = FileUtil.getImageURL(ImageResource.CLASS_D,
					Activator.PLUGIN_ID);
		} else if ("������".equals(text)) {
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

		// ����ܹ�ȡ����ҵ����ʾ��ҵ
		text = company.get(Company.FIELD_INDUSTRY);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("��ҵ: ");
			builder.append("</b>");
			builder.append(text);
			builder.append("  ");
		}

		// ����ܹ�ȡ����Ʒ����ʾ��Ʒ
		text = company.get(Company.FIELD_PRODUCT);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<b>");
			builder.append("��Ʒ: ");
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
			// builder.append("����");
		} else {
			if (!Utils.isNullOrEmptyString(prov)) {
				builder.append(prov);
			}
			if (!Utils.isNullOrEmptyString(city)) {
				builder.append("��");
				builder.append(city);
			}
			if (!Utils.isNullOrEmptyString(address)) {
				builder.append("��");
				builder.append(address);
			}
			if (!Utils.isNullOrEmptyString(postcode)) {
				builder.append("��");
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

		// web��ַ
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
