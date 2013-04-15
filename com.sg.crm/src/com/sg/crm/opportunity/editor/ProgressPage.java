package com.sg.crm.opportunity.editor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.bson.types.ObjectId;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.forms.IFormPart;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.sg.crm.Activator;
import com.sg.crm.ImageResource;
import com.sg.crm.action.data.Action;
import com.sg.crm.action.data.ActionOfCompanyInput;
import com.sg.crm.opportunity.data.Opportunity;
import com.sg.ui.UI;
import com.sg.ui.UIConstants;
import com.sg.ui.UIUtils;
import com.sg.ui.model.DataObject;
import com.sg.ui.model.DataObjectEditorInput;
import com.sg.ui.model.input.IInputReceiver;
import com.sg.ui.model.text.Enumerate;
import com.sg.ui.part.editor.page.IPageDelegator;
import com.sg.ui.registry.config.BasicPageConfigurator;
import com.sg.util.Utils;
import com.sg.util.file.FileUtil;

public class ProgressPage implements IPageDelegator, IInputReceiver {

	private Enumerate[] progressList;
	private Vector<Control>[] lastBrick;
	private Composite panel;
	private int margin = 8;
	private int brickWidth = 320;
	private int labelBrickHeight = 72;
	private DataObject opportunityData;
	private int currentProgressIndex;

	public ProgressPage() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Composite createPageContent(Composite parent,
			DataObjectEditorInput input, BasicPageConfigurator conf) {
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		this.opportunityData = input.getData();
		Object currentProgress = opportunityData
				.getValue(Opportunity.FIELD_PROGRESS);
		this.panel = parent;
		parent.setLayout(new FormLayout());

		// 读取com.sg.crm.enumerate.opportunity
		Enumerate root = (Enumerate) UI.getEnumRegistry().getConfigurator(
				"com.sg.crm.enumerate.opportunity");
		progressList = root.getChildren();
		currentProgressIndex = getUpperControlIndex(currentProgress,null);

		lastBrick = new Vector[progressList.length + 1];
		for (int i = 0; i < lastBrick.length; i++) {
			lastBrick[i] = new Vector<Control>();
		}

		// 创建表头区

		Composite brick = new Composite(parent, SWT.NONE);
		brick.setData(RWT.CUSTOM_VARIANT, "brick");

		brick.setLayout(new FillLayout());
		createHeadLabel("商机确定", brick);

		FormData fd = new FormData();
		brick.setLayoutData(fd);
		fd.left = new FormAttachment(0, margin);
		fd.width = brickWidth;
		fd.top = new FormAttachment(0, margin);
		fd.height = labelBrickHeight;
		lastBrick[0].add(brick);

		for (int i = 0; i < progressList.length; i++) {
			brick = new Composite(parent, SWT.NONE);
			brick.setData(RWT.CUSTOM_VARIANT, "brick");

			brick.setLayout(new FillLayout());
			createHeadLabel(progressList[i].getLabel(), brick);
			fd = new FormData();
			brick.setLayoutData(fd);
			fd.left = new FormAttachment(lastBrick[i].lastElement(), margin);
			fd.width = brickWidth;
			fd.top = new FormAttachment(0, margin);
			fd.height = labelBrickHeight;
			if (i == progressList.length - 1) {
				fd.right = new FormAttachment(100, -margin);
			}
			lastBrick[i + 1].add(brick);
		}

		// 读取活动
		//公司id
		ObjectId id = (ObjectId) input.getData().getValue(
				Opportunity.FIELD_COMPANY_ID);
		if (id == null) {
			return new Composite(parent, SWT.NONE);
		}

		final ActionOfCompanyInput actionInput = new ActionOfCompanyInput(id);
		actionInput.addInputReceiver(this);

		panel.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent event) {
				actionInput.removeInputReceiver(ProgressPage.this);				
			}
		});
		
		return panel;

	}

	private Label createHeadLabel(String text, Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
		label.setText(getHtmlHeadBrick(text));
		return label;
	}

	private String getHtmlHeadBrick(String progress) {
		int index = getUpperControlIndex(progress,null);

		StringBuilder builder = new StringBuilder();

		builder.append("<span style='float:left;padding:4px 4px 4px 4px;FONT-FAMILY:微软雅黑;font-size:13pt'>");
		builder.append(getIconHtml(progress));
		builder.append(progress);
		// 显示是否是单前的状态
		builder.append("<img src='");
		if (index <= currentProgressIndex) {
			builder.append(FileUtil.getImageURL(ImageResource.BALL_GREEN,
					Activator.PLUGIN_ID));
		} else if (index > currentProgressIndex) {
			builder.append(FileUtil.getImageURL(ImageResource.BALL_YELLOW,
					Activator.PLUGIN_ID));
		}
		builder.append("' width='16' height='16' />");
		builder.append("<br/><small>");
		// 显示时间
		if (index > 1) {
			Object date = opportunityData.getValue("m" + index + "0");
			if (date instanceof Date) {
				boolean estimate = Boolean.TRUE.equals(opportunityData
						.getValue("m" + index + "0_1"));

				if (estimate) {
					builder.append("预计: ");
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						Utils.SDF_DATE_COMPACT_SASH);
				builder.append(sdf.format(date));
			}
		}

		builder.append("</small>");
		builder.append("</span>");
		return builder.toString();
	}

	private String getIconHtml(String progress) {
		StringBuilder builder = new StringBuilder();
		builder.append("<img src='");
		if ("商机确定".equals(progress)) {
			builder.append(FileUtil.getImageURL(
					ImageResource.OPPORTUNITY_PROGRESS[0], Activator.PLUGIN_ID));
		} else {
			int index = getUpperControlIndex(progress,null);
			if (index < ImageResource.OPPORTUNITY_PROGRESS.length) {
				builder.append(FileUtil.getImageURL(
						ImageResource.OPPORTUNITY_PROGRESS[index],
						Activator.PLUGIN_ID));
			} else {
				builder.append(FileUtil.getImageURL(
						ImageResource.OPPORTUNITY_PROGRESS[0],
						Activator.PLUGIN_ID));
			}
		}
		builder.append("' style='float:left' width='64' height='64' />");
		return builder.toString();
	}

	@Override
	public IFormPart getFormPart() {
		return null;
	}

	@Override
	public void inputReceived(Object input) {
		@SuppressWarnings("unchecked")
		ArrayList<DBObject> actionList = (ArrayList<DBObject>) input;
		if (Utils.isNullOrEmpty(actionList)) {
			return;
		}
		FormData fd;
		FormData layoutData;
		Composite brick;
		for (int i = 0; i < actionList.size(); i++) {
			final DBObject action = actionList.get(i);
			Object type = action.get(Action.FIELD_OPPORTUNIY_PROGRESS);
			int index = getUpperControlIndex(type,action);

			brick = new Composite(panel, SWT.NONE);
			brick.setData(RWT.CUSTOM_VARIANT, "brickitem");
			brick.setLayout(new FormLayout());
			fd = new FormData();
			brick.setLayoutData(fd);
			fd.top = new FormAttachment(lastBrick[index].lastElement(), margin);
			fd.left = new FormAttachment(lastBrick[index].lastElement(),
					-brickWidth - 2);
			fd.width = brickWidth;

			Label title = new Label(brick, SWT.NONE);
			title.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
			title.setData(RWT.CUSTOM_VARIANT, "brickitemlabel");
			String text = getActionIcon(action);
			text = text + "<abbr>" + action.get(Action.FIELD_TYPE);
			text = text + " " + action.get(Action.FIELD_ACTOR_NAME) + "</abbr>";
			title.setText(text);
			layoutData = new FormData();
			title.setLayoutData(layoutData);
			layoutData.top = new FormAttachment(0, 4);
			layoutData.left = new FormAttachment(0, 6);
			layoutData.right = new FormAttachment(100, -4);
			title.addMouseListener(new MouseAdapter() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.swt.events.MouseAdapter#mouseDown(org.eclipse
				 * .swt.events.MouseEvent)
				 */
				@Override
				public void mouseDown(MouseEvent e) {
					openAction(action);
				}

			});
			final Label plan = new Label(brick, SWT.NONE);
			plan.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
			text = getPlanText(action);
			plan.setText(text);
			layoutData = new FormData();
			plan.setLayoutData(layoutData);
			layoutData.top = new FormAttachment(title, 6);
			layoutData.left = new FormAttachment(0, 6);
			layoutData.right = new FormAttachment(100, -4);

			final Composite detail = new Composite(brick, SWT.NONE);
			detail.setLayout(new FormLayout());

			Label desc = new Label(detail, SWT.WRAP);
			text = "活动:\n"
					+ Utils.getLimitLengthString(
							(String) action.get(Action.FIELD_DESC), 80);
			desc.setText(text);
			layoutData = new FormData();
			desc.setLayoutData(layoutData);
			layoutData.top = new FormAttachment(0, 6);
			layoutData.left = new FormAttachment(0, 6);
			layoutData.right = new FormAttachment(100, -4);

			// 获得进展
			Control top = desc;
			BasicDBList list = (BasicDBList) action
					.get(Action.FIELD_ACTION_PROGRESS);
			if (list != null && list.size() > 0) {
				Label actionProgress = new Label(detail, SWT.WRAP);
				text = "";
				for (int j = 0; j < list.size(); j++) {
					text = text + list.get(j) + "; ";
				}
				text = "进展:\n" + Utils.getLimitLengthString(text, 80);
				actionProgress.setText(text);
				layoutData = new FormData();
				actionProgress.setLayoutData(layoutData);
				layoutData.top = new FormAttachment(desc, 4);
				layoutData.left = new FormAttachment(0, 6);
				layoutData.right = new FormAttachment(100, -4);
				top = actionProgress;
			}

			// 获得评估
			list = (BasicDBList) action.get(Action.FIELD_EVALUATION);
			if (list != null && list.size() > 0) {
				Label evaluation = new Label(detail, SWT.WRAP);
				text = "";
				for (int j = 0; j < list.size(); j++) {
					text = text + list.get(j) + "; ";
				}
				text = "评估:\n" + Utils.getLimitLengthString(text, 80);
				evaluation.setText(text);
				layoutData = new FormData();
				evaluation.setLayoutData(layoutData);
				layoutData.top = new FormAttachment(top, 4);
				layoutData.left = new FormAttachment(0, 6);
				layoutData.right = new FormAttachment(100, -4);
			}

			layoutData = new FormData();
			detail.setLayoutData(layoutData);
			layoutData.top = new FormAttachment(plan, 4);
			layoutData.left = new FormAttachment(0, 0);
			layoutData.right = new FormAttachment(100, 0);

			// 添加折叠按钮
			final Button closeButton = new Button(brick, SWT.PUSH);
			closeButton.setImage(UI
					.getImage(com.sg.ui.ImageResource.COLLAPSE_UP_18));
			closeButton.setData(RWT.CUSTOM_VARIANT, "whitebutton");
			fd = new FormData();
			closeButton.setLayoutData(fd);
			fd.top = new FormAttachment(0, -1);
			fd.right = new FormAttachment(100, -1);
			fd.width = 24;
			fd.height = 24;
			closeButton.moveAbove(null);
			closeButton.setData("closed", false);
			closeButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					FormData formData = new FormData();
					detail.setLayoutData(formData);
					if (Boolean.TRUE.equals(closeButton.getData("closed"))) {
						// 如果关闭，需要打开
						closeButton.setImage(UI
								.getImage(com.sg.ui.ImageResource.COLLAPSE_UP_18));
						formData.top = new FormAttachment(plan, 4);
						formData.left = new FormAttachment(0, 0);
						formData.right = new FormAttachment(100, 0);
						closeButton.setData("closed", false);
					} else {
						// 如果打开，需要关闭
						closeButton.setImage(UI
								.getImage(com.sg.ui.ImageResource.EXPAND_18));
						formData.top = new FormAttachment(plan, 4);
						formData.left = new FormAttachment(0, 0);
						formData.right = new FormAttachment(100, 0);
						formData.height = 0;
						closeButton.setData("closed", true);
					}
					panel.layout(new Control[] { detail });

				}
			});

			lastBrick[index].add(brick);
		}
		
	}

	protected void openAction(DBObject action) {
		try {
			UIUtils.open((ObjectId) action.get(Action.FIELD_SYSID),
					"crm.action.editor", true, true, null);
		} catch (Exception e) {
			MessageBox mb = new MessageBox(panel.getShell(),
					SWT.CLOSE | SWT.ERROR);
			mb.setText(UIConstants.TEXT_OPEN);
			mb.setMessage(e.getMessage());
			mb.open();
			
		}
	}

	private String getPlanText(DBObject action) {
		// 计划时间
		StringBuilder builder = new StringBuilder();
		builder.append("<span style='float:left;font-size:11pt'>");
		builder.append("<small>");
		builder.append("计划:");
		Object value = action.get(Action.FIELD_PLANSTART);
		try {
			value = Utils.getText(Utils.TYPE_DATE, value);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(value)) {
			builder.append(value);
			builder.append("~");
		}
		value = action.get(Action.FIELD_PLANFINISH);
		try {
			value = Utils.getText(Utils.TYPE_DATE, value);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(value)) {
			builder.append(value);
			builder.append("  ");
		}

		// 实际时间
		builder.append(" 实际:");
		value = action.get(Action.FIELD_ACTUALSTART);
		try {
			value = Utils.getText(Utils.TYPE_DATE, value);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(value)) {
			builder.append(value);
			builder.append("~");
		}
		value = action.get(Action.FIELD_ACTUALFINISH);
		try {
			value = Utils.getText(Utils.TYPE_DATE, value);
		} catch (Exception e) {
		}
		if (!Utils.isNullOrEmptyString(value)) {
			builder.append(value);
		}
		builder.append("</small></span>");

		return builder.toString();
	}

	private String getActionIcon(DBObject action) {
		StringBuilder builder = new StringBuilder();
		builder.append("<img src='");

		Object type = action.get(Action.FIELD_TYPE);
		if ("电话联系".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_CALL,
					Activator.PLUGIN_ID));

		} else if ("公司拜访".equals(type)||"公司初访".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_VISIT,
					Activator.PLUGIN_ID));

		} else if ("售前技术".equals(type)||"准备资料".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_DEMO,
					Activator.PLUGIN_ID));

		} else if ("商务活动".equals(type)||"商务公关".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_BUSINESS,
					Activator.PLUGIN_ID));

		} else if ("技术服务".equals(type)) {
			builder.append(FileUtil.getImageURL(
					ImageResource.ACTION_TECHNOLOGY, Activator.PLUGIN_ID));

		} else if ("服务跟踪".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_SUPPORT,
					Activator.PLUGIN_ID));

		} else if ("市场活动".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_MARKET,
					Activator.PLUGIN_ID));

		} else if ("内部培训".equals(type)) {
			builder.append(FileUtil.getImageURL(ImageResource.ACTION_TRAINING,
					Activator.PLUGIN_ID));

		}
		builder.append("' style='float:left' width='24' height='24' />");
		return builder.toString();
	}

	// private String getActionText(DBObject action) {
	// return actionLabelProvider.getText(action);
	// }

	private int getUpperControlIndex(Object type,DBObject action) {
		if (Utils.isNullOrEmptyString(type)) {
			return 0;
		}
		ObjectId opportunityId = opportunityData.getSystemId();
		if(action!=null){
			Object actionOpporId = action.get(Action.FIELD_OPPORTUNIY_ID);
			if(opportunityId.equals(actionOpporId) ){
				return getTypeIndex(type);
			}else {
				return 0;
			}
		}else{
			return getTypeIndex(type);
		}
	}

	private int getTypeIndex(Object type) {
		for (int i = 0; i < progressList.length; i++) {
			Object value = progressList[i].getValue();
			if (type.equals(value)) {
				return i + 1;
			}
		}
		return 0;
	}
	
	

}
