package com.sg.crm.opportunity.labelprovider;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.sg.crm.Activator;
import com.sg.crm.ImageResource;
import com.sg.crm.opportunity.data.Opportunity;
import com.sg.util.Utils;
import com.sg.util.file.FileUtil;

public class OpportunityMarkedLabelProviderFull extends ColumnLabelProvider {

	public OpportunityMarkedLabelProviderFull() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		DBObject opportunity = (DBObject) element;

		StringBuilder builder = new StringBuilder();

		// 进度
//		Object text = opportunity.get(Opportunity.FIELD_PROGRESS);
//		if (!Utils.isNullOrEmptyString(text)) {
//			String image = getImageURL((String) text);
//			if (image != null) {
//				builder.append("<img src='");
//				builder.append(image);
//				builder.append("' style='float:left' width='64' height='64' />");
//			}
//		}
		
		
		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:微软雅黑;font-size:11pt'><b>");

		// 显示机会的阶段
		Object text = opportunity.get(Opportunity.FIELD_PROGRESS);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(" ");
			builder.append(text);
			builder.append(" ");
		}
		
		text = opportunity.get(Opportunity.FIELD_DESC);
		builder.append(text);
		builder.append("</b>   ");

		//显示公司名称
		text = opportunity.get(Opportunity.FIELD_COMPANY_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append(text);
			builder.append("  ");
		}
		
		// 客户经理
		text = opportunity.get(Opportunity.FIELD_MANAGER_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src='");
			builder.append(FileUtil.getImageURL(ImageResource.PEOPLE1_16, Activator.PLUGIN_ID));
			builder.append("' style='padding-right:4px;padding-top:4px;' width='16' height='16' />");

			builder.append(text);
			builder.append(" ");
		}

		text = opportunity.get(Opportunity.FIELD_PM_NAME);
		if (!Utils.isNullOrEmptyString(text)) {
			builder.append("<img src='");
			builder.append(FileUtil.getImageURL(ImageResource.PEOPLE2_16, Activator.PLUGIN_ID));
			builder.append("' style='padding-right:4px;padding-top:4px;' width='16' height='16' />");
			builder.append(text);
			builder.append(" ");
		}

		
		builder.append("<br/>");

		builder.append("<small>");
		//显示需求的描述
		text = opportunity.get(Opportunity.FIELD_REQ_DESC);
		if(text instanceof BasicDBList){
			BasicDBList list = (BasicDBList)text;
			builder.append("需求: ");
			for(int i=0;i<list.size();i++){
				Object value = list.get(i);
				if(!Utils.isNullOrEmptyString(value)){
					builder.append(value);
					builder.append(" ;");
				}
			}
		}
		builder.append("<br/>");
		
		//显示需求的产品
		text = opportunity.get(Opportunity.FIELD_REQ_PROD);
		if(text instanceof BasicDBList){
			BasicDBList list = (BasicDBList)text;
			builder.append("产品: ");
			for(int i=0;i<list.size();i++){
				Object value = list.get(i);
				if(!Utils.isNullOrEmptyString(value)){
					builder.append(value);
					builder.append(" ;");
				}
			}
		}
		
		builder.append("</small>");
		builder.append("</span>");

		return builder.toString();
	}

//	private String getImageURL(String text) {
//		if(Opportunity.VALUE_PROGRESS_M10.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_10, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M20.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_20, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M30.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_30, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M40.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_40, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M50.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_50, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M60.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_60, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M70.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_70, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M80.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_80, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M90.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_90, DBActivator.PLUGIN_ID);
//		}else if(Opportunity.VALUE_PROGRESS_M00.equals(text)){
//			return FileUtil.getImageURL(ImageResource.PERCENT_00, DBActivator.PLUGIN_ID);
//		}
//		
//		return null;
//	}

}
