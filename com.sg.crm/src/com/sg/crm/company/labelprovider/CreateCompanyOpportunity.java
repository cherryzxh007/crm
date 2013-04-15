package com.sg.crm.company.labelprovider;

import org.eclipse.jface.viewers.IStructuredSelection;

import com.mobnut.db.collection.CollectionService;
import com.mongodb.DBObject;
import com.sg.ui.command.handler.IOperationLabelProvider;
import com.sg.util.Utils;

public class CreateCompanyOpportunity implements IOperationLabelProvider {

	public CreateCompanyOpportunity() {
	}

	@Override
	public String getText(IStructuredSelection selection) {
		if (selection == null || selection.isEmpty()){
			return null;
		}else{
			DBObject dbo = (DBObject) selection.getFirstElement();
			Object name = dbo.get(CollectionService.FIELD_DESC);
			if(name instanceof String){
				String text = Utils.getLimitLengthString((String) name, 6);
				return "×¢²á ¡°"+text+"¡± ÉÌ»ú";
			}
			return null;
		}
	}

}
