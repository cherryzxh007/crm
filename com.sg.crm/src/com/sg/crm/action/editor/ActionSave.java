package com.sg.crm.action.editor;

import org.bson.types.ObjectId;
import org.eclipse.core.runtime.IProgressMonitor;

import com.mongodb.DBObject;
import com.sg.crm.action.data.Action;
import com.sg.crm.opportunity.data.Opportunity;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;

public class ActionSave implements IEditorSaveHandler {

	private Opportunity service;

	public ActionSave() {
		service = new Opportunity();
	}

	@Override
	public boolean doSaveBefore(DataObjectEditorInput input,
			IProgressMonitor monitor, String operation) {
		//保存时写入，商机的状态
		DataObject data = input.getData();
		ObjectId opportunityId = (ObjectId) data.getValue(Action.FIELD_OPPORTUNIY_ID);
		if(opportunityId!=null){
			try {
				DBObject opportunity = service.get(opportunityId);
				if(opportunity!=null){
					Object progress = opportunity.get(Opportunity.FIELD_PROGRESS);
					data.setValue(Action.FIELD_OPPORTUNIY_PROGRESS, progress);
				}
				
			} catch (Exception e) {
			}
		}
		return true;
	}

	@Override
	public void doSaveAfter(DataObjectEditorInput input,
			IProgressMonitor monitor, String operation) {

	}

}
