package com.sg.crm.contact.data;

import java.util.Comparator;

import com.mongodb.DBObject;
import com.sg.util.Utils;

public class PeopleNameComparator implements Comparator<DBObject> {

	@Override
	public int compare(DBObject data0, DBObject data1) {
		String name0 = (String) data0.get(Contact.FIELD_NAME);
		String name1 = (String) data1.get(Contact.FIELD_NAME);
		if (Utils.isNullOrEmpty(name0) && Utils.isNullOrEmpty(name1)) {
			return 0;
		} else if (!Utils.isNullOrEmpty(name0) && Utils.isNullOrEmpty(name1)) {
			return -1;
		} else if (Utils.isNullOrEmpty(name0) && !Utils.isNullOrEmpty(name1)) {
			return 1;
		} else {
			return Utils.doCompare(name0, name1);
		}

	}

}
