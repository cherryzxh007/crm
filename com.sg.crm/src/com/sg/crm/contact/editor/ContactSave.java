package com.sg.crm.contact.editor;

import org.eclipse.core.runtime.IProgressMonitor;

import com.sg.crm.contact.data.Contact;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.part.editor.IEditorSaveHandler;

public class ContactSave implements IEditorSaveHandler {

	public ContactSave() {
	}

	@Override
	public boolean doSaveBefore(DataObjectEditorInput input,
			IProgressMonitor monitor, String operation) {
		DataObject data = input.getData();
		String name1 = data.getText(Contact.FIELD_FIRSTNAME);
		String name2 = data.getText(Contact.FIELD_LASTNAME);
		data.setValue(Contact.FIELD_NAME, name1+name2);
		return true;
	}

	@Override
	public void doSaveAfter(DataObjectEditorInput input,
			IProgressMonitor monitor, String operation) {
	}

}
