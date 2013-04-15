package com.sg.crm.action.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.crm.action.data.Action;
import com.sg.util.Utils;

public class ActionDescMarkedLabelProvider extends ColumnLabelProvider {

	public ActionDescMarkedLabelProvider() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		DBObject data = (DBObject) element;

		StringBuilder builder = new StringBuilder();

		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:微软雅黑;font-size:11pt'><b>");

		Object text = data.get(Action.FIELD_ACTOR_NAME);
		builder.append(text);
		builder.append(" ");
		text = data.get(Action.FIELD_TYPE);
		builder.append(text);
		builder.append(" ");
		text = data.get(Action.FIELD_ACTION_STATUS);
		if(text!=null){
			builder.append(text);
		}
		builder.append("</b><br/><small>");

		//
		text = data.get(Action.FIELD_DESC);
		if(text!=null){
			text = Utils.getPlainText(text.toString());
			builder.append(text);
		}
		builder.append("<br/>");

		//

		builder.append("<b>");
		builder.append("计划: ");
		builder.append("</b>");
		text = data.get(Action.FIELD_PLANSTART);
		try {
			text = Utils.getText(Utils.TYPE_DATE, text);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		builder.append(" ~ ");
		text = data.get(Action.FIELD_PLANFINISH);
		try {
			text = Utils.getText(Utils.TYPE_DATE, text);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		builder.append("<b>");
		builder.append("实际: ");
		builder.append("</b>");
		text = data.get(Action.FIELD_ACTUALSTART);
		try {
			text = Utils.getText(Utils.TYPE_DATE, text);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		builder.append(" ~ ");
		text = data.get(Action.FIELD_ACTUALFINISH);
		try {
			text = Utils.getText(Utils.TYPE_DATE, text);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
		}
		builder.append("</small></span>");

		return builder.toString();
	}
}
