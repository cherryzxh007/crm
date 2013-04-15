package com.sg.crm.action.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.DBObject;
import com.sg.crm.action.data.Action;
import com.sg.util.Utils;

public class ActionRemarksMarkedLabelProvider extends ColumnLabelProvider {

	public ActionRemarksMarkedLabelProvider() {
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
		Object text = data.get(Action.FIELD_REMARK);
		if (Utils.isNullOrEmptyString(text)) {
			return "";
		}
		text = Utils.getPlainText(text.toString());
		text = text.toString().replaceAll("\n", "<br/>");
		return "<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:Î¢ÈíÑÅºÚ;font-size:11pt'><small>"
				+ text + "</small></span>";
	}
}
