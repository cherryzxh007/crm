package com.sg.crm.action.editor;

import com.sg.crm.action.data.Action;
import com.sg.ui.model.DataObject;
import com.sg.ui.part.editor.field.value.ConditionNotNullValidator;

public class ValidateContract extends ConditionNotNullValidator {

	public ValidateContract() {
	}

	@Override
	protected String getValidMessage(DataObject data) {
		Object type = data.getValue(Action.FIELD_TYPE);
		Object opportunity = data.getValue(Action.FIELD_CONTRACT_ID);
		if (opportunity == null) {
			if ("�绰��ϵ".equals(type)) {
			} else if ("��˾�ݷ�".equals(type)) {
			} else if ("��ǰ����".equals(type)) {
			} else if ("����".equals(type)) {
			} else if ("��������".equals(type)) {
				return "�������������͵Ļ����ѡ�񡰺�ͬ��";
			} else if ("�������".equals(type)) {
				return "��������١����͵Ļ����ѡ�񡰺�ͬ��";
			} else if ("�г��".equals(type)) {

			} else if ("�ڲ���ѵ".equals(type)) {

			}
		}

		return null;
	}

}
