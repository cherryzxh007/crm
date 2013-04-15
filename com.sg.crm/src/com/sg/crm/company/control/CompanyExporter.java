package com.sg.crm.company.control;

import org.eclipse.swt.widgets.Display;

import com.sg.ui.part.IExporter;
import com.sg.ui.part.Navigator;
import com.sg.ui.registry.config.NavigatorColumnConfigurator;
import com.sg.util.file.ExcelExportJob;

public class CompanyExporter implements IExporter {

	public CompanyExporter() {
	}

	@Override
	public Object doExport(Navigator navigator) {
		
		NavigatorColumnConfigurator[] columns = navigator.getConfigurator().getSortColumns();
		ExcelExportJob job = new ExcelExportJob();

		job.setColumnExportDefinition(columns);
		job.setInput(navigator.getViewer().getInput());
		job.setUser(true);
		job.setFormat(false);
		Display display = Display.getCurrent();
		job.start(display);
		return null;
	}

}
