/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.longwu.ijkplayer.utlis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.longwu.ijkplayer.R;



/**
 * 此类主要用于通用的  title (标题)/message ,edittext (中间内容区)/ 按钮(单双) 的弹框
 * 不包括图片
 */
public class CommonDialog extends Dialog implements View.OnClickListener {

	private TextView content, gap, titleBlock, txtTip;
	private Button cancel, enter, neutral;
	private myDiaLogListener mListener;
	private String messageStr, warnMsgStr;
	private EditText editText;
	private int type = 0;
	private LinearLayout layout;
	private ScrollView scrollView;
	private View titleLayout;
	/**
	 * 传入相应的显示类型
	 */
	public static final int FLAG_EditView = 1;  //带有Edittext可编辑的对话框
	public static final int FLAG_SingleButton = 2;  //只有一个按钮的对话框
	public static final int FLAG_TwoButton = 3;     //常用的,也是默认的两个按钮的对话框
	public static final int FLAG_PasswordEditView = 4; // 带有密码输入样式的对话框
	public static final int FLAG_scrollView_And_SingleButton = 5; //带有可滚动的,一个按钮的对话框

	private boolean isOneButton = false;

	public CommonDialog(Context context) {
		super(context, R.style.LoadingProDialog);
	}

	public CommonDialog(Context context, myDiaLogListener myDiaLogListener) {

		super(context, R.style.LoadingProDialog);
		mListener = myDiaLogListener;
		// TODO Auto-generated constructor stub
	}

	public CommonDialog(Context context, myDiaLogListener myDiaLogListener, int t) {

		super(context, R.style.LoadingProDialog);
		mListener = myDiaLogListener;
		type = t;
		// TODO Auto-generated constructor stub
	}

	public void setDialogListener(myDiaLogListener myDiaLogListener) {
		mListener = myDiaLogListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.mydialog);
		setContentView(R.layout.my_new_dialog);
		InitView();
		setMessage(messageStr);
		setMessageEx(messageStr);
		setWarnMessage(warnMsgStr);
		setTwoButton();
		// 设置ContentView
		if (mCustomContentView != null) {
			layout.setVisibility(View.VISIBLE);
			layout.addView(mCustomContentView);
			scrollView.setVisibility(View.GONE);
		}
	}

	public interface myDiaLogListener {
		public void onClick(View view);
	}

	private void InitView() {
		editText = (EditText) findViewById(R.id.editText_mydialog);
		cancel = (Button) findViewById(R.id.button_mydialog_cancel);
		enter = (Button) findViewById(R.id.button_mydialog_enter);
		neutral = (Button) findViewById(R.id.button_mydialog_neutral);
		gap = (TextView) findViewById(R.id.textView_mydialog_gap);
		layout = (LinearLayout) findViewById(R.id.LinearLayout_mydialog);
		scrollView = (ScrollView) findViewById(R.id.scrollView_update);
		titleLayout = findViewById(R.id.titleLayout);
		titleBlock = (TextView) findViewById(R.id.titleBlock);
		txtTip = (TextView) findViewById(R.id.txtTip);
		enter.setOnClickListener(this);
		cancel.setOnClickListener(this);
		neutral.setOnClickListener(this);
		setDiaLogType();
		setOneBtn();
	}

	boolean isProceesCancelClickWhenBackBtn = true;

	public void setProceesCancelClickWhenBackBtn(boolean isProceesCancelClickWhenBackBtn) {
		this.isProceesCancelClickWhenBackBtn = isProceesCancelClickWhenBackBtn;
	}

	boolean isCancelable = false;

	public void setBackCancelable(boolean isCancelable) {
		//setCancelable(isCancelable);
		this.isCancelable = isCancelable;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (mListener != null && isProceesCancelClickWhenBackBtn) {

			mListener.onClick(cancel);
		} else {

			if (isCancelable)
				cancel();
		}
	}

	private void setDiaLogType() {
		if (!TextUtils.isEmpty(mCancleBtnName)) {
			cancel.setText(mCancleBtnName);
		}
		if (!TextUtils.isEmpty(mEnterBtnName)) {
			enter.setText(mEnterBtnName);
		}
		if (!TextUtils.isEmpty(mTitleString)) {
			titleLayout.setVisibility(View.VISIBLE);
			titleBlock.setText(mTitleString);
		}
		switch (type) {
		case FLAG_EditView:
			editText.setVisibility(View.VISIBLE);// 含有editview的dialog
			editText.setHint("");
			editText.setFocusable(true);
			editText.requestFocus();
			changeInputState();
			break;
		case FLAG_SingleButton:
			gap.setVisibility(View.GONE);
			editText.setVisibility(View.GONE);
			cancel.setVisibility(View.GONE);// 只有一个确定的dialog
			break;
		case FLAG_TwoButton:
			layout.setVisibility(View.GONE);// 升级弹出框  标准的默认的的两个按钮的  确定和取消
			scrollView.setVisibility(View.VISIBLE);
			break;
		case FLAG_PasswordEditView:
			editText.setVisibility(View.VISIBLE);// 含有editview的输入密码 dialog
			editText.setInputType(0x81);

			editText.setFocusable(true);
			editText.requestFocus();
			changeInputState();
			break;
         case FLAG_scrollView_And_SingleButton:
            gap.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);//
            scrollView.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.GONE);// 只有一个确定的dialog
            break;
		default:      //当都没有符合的时候默认使用正常的两个按钮的
			layout.setVisibility(View.GONE);// 升级弹出框  标准的默认的的两个按钮的  确定和取消
			scrollView.setVisibility(View.VISIBLE);
			break;
		}
	}

	/**
	 * 更改输入法的状态，以保证初始即让pEditText上弹出软键盘
	 */
	private void changeInputState() {
		// 更改状态为SOFT_INPUT_STATE_ALWAYS_VISIBLE 当窗口获得焦点时，显示输入法区域
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	}

	/**
	 * 重置回弹软键盘时设置的状态，以避免干扰其他界面操作
	 */
	public void resetInputState() {
		// 回复到初始状态，edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}
    // 获取EditText
	public EditText getEditTextView() {
		editText = (EditText) findViewById(R.id.editText_mydialog);
		return editText;
	}
    //设置edittext的hint显示文字   默认是:请输入密码
	public void setEditTextHint(String hint) {
		if (editText == null) {
			editText = (EditText) findViewById(R.id.editText_mydialog);
		}
		editText.setHint(hint);
	}
	// 设置显示界面的类型
	public void setShowType(int t) {
		this.type = t;
	}

	public void setMessage(String str) {
		messageStr = str;
		content = (TextView) findViewById(R.id.textView_mydialog_content);
		if (content != null) {
			if(TextUtils.isEmpty(messageStr))
			{
				content.setVisibility(View.GONE);
			}else{
				content.setVisibility(View.VISIBLE);
			}
			content.setText(str);
		}

	}

	public void setWarnMessage(String wranMsg) {
		warnMsgStr = wranMsg;
		txtTip = (TextView) findViewById(R.id.txtTip);
		if (txtTip != null && wranMsg != null) {
			txtTip.setVisibility(View.VISIBLE);
			txtTip.setText(wranMsg);
		}
	}

	public void setMessageEx(String str) {
		messageStr = str;
		content = (TextView) findViewById(R.id.textView_mydialog_content1);
		if (content != null) {
			if(TextUtils.isEmpty(messageStr))
			{
				content.setVisibility(View.GONE);
			}else{
				content.setVisibility(View.VISIBLE);
			}
			content.setText(str);

			int maxWidth =  FSScreen.dip2px((280-20));
			content.setMaxWidth(maxWidth);
            content.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int maxHeight = FSScreen.getScreenHeight() - FSScreen.dip2px(50+70+50+50);
            int contentHeight = content.getMeasuredHeight();
            LayoutParams lParams = (LayoutParams)scrollView.getLayoutParams();
            if(contentHeight > maxHeight){
                lParams.height = maxHeight;
                scrollView.setLayoutParams(lParams);
            }
		}

	}

	/**
	 * 设置确定取消的按钮
	 * @param str
     */
	public void setPositiveButton(String str) {
		mEnterBtnName = str;
	}

	public void setNegativeButton(String str) {
		mCancleBtnName = str;
	}

	/**
	 * 设置Neutral Button text 并显示
	 * */
	public void setNeutralButton(String str) {
		mNeutralBtnName = str;
	}

	/**
	 * 设置自定义 Custom View
	 * */
	public void setCustomContentView(View view) {
		mCustomContentView = view;
	}

	public void setTwoButton() {

		if (mCancleBtnName.length() > 0) {
			cancel.setText(mCancleBtnName);
		}

		if (mEnterBtnName.length() > 0) {
			enter.setText(mEnterBtnName);
		}

		if (mNeutralBtnName.length() > 0) {
			neutral.setVisibility(View.VISIBLE);
			neutral.setText(mNeutralBtnName);
		}
	}

	/**
	 * 根据字数设置button的宽度和padding值
	 * @param btn
	 * @param str
     */
	private void setBtn_paddingAndWeigh(Button btn , String str)
	{
		/*if (str.length() > 2 ){
			btn.setPadding(btn_padding,btn_padding,btn_padding,btn_padding);
			// 设置包裹内容或者填充父窗体大小
			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//	btn.setLayoutParams(linearParams);
		}else if (str.length() == 2 ){
		//	btn.setWidth(FSScreen.dp2px(64));
			LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) btn.getLayoutParams(); //取控件textView当前的布局参数

			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp2.width = FSScreen.dp2px(64);

		//	linearParams.width =FSScreen.dp2px(64);// 控件的宽强制设成30
			btn.setLayoutParams(lp2);
		}*/



	}
	private void setOneBtn() {
		if (isOneButton) {
			gap.setVisibility(View.GONE);
			editText.setVisibility(View.GONE);
			cancel.setVisibility(View.GONE);
		}
	}

	/**
	 * 只有一个按钮的   例如:知道了
	 */
	public void setUpdateOneButton() // 只有一个按钮
	{
		if (gap != null) {

			gap.setVisibility(View.GONE);
		}
		if (editText != null) {

			editText.setVisibility(View.GONE);
		}
		if (cancel != null) {

			cancel.setVisibility(View.GONE);
		}
		isOneButton = true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (mListener != null) {
			// dismiss();
			mListener.onClick(v);
		} else {
			// dismiss();
			cancel();
		}
	}

	/**
	 * 创建只有一个button的dialog
	 *
	 * @param context
	 * @param message
	 * @param myDiaLogListener
	 * @param mOnDismissListener
	 * @return
	 */
	public static Dialog createDialog(Context context, String message, final myDiaLogListener myDiaLogListener, OnDismissListener mOnDismissListener) {
		final CommonDialog myDialog = new CommonDialog(context, myDiaLogListener, 2);
		myDialog.setDialogListener(myDiaLogListener);
		myDialog.setTitle("注意");
		myDialog.setMessage(message);
		myDialog.setCanceledOnTouchOutside(true);
		myDialog.show();
		myDialog.setOnDismissListener(mOnDismissListener);
		return myDialog;
	}

	/**
	 * 创建有两个button的dialog
	 *
	 * @param context
	 * @param message
	 * @return
	 */
	public static CommonDialog createTwoButtonDialog(Context context, String message) {
		CommonDialog myDialog = new CommonDialog(context);
		myDialog.setMessage(message);
		myDialog.setCanceledOnTouchOutside(true);
		return myDialog;
	}

	/**
	 * 设置两个button的监听（button_mydialog_enter和button_mydialog_cancel）
	 *
	 * @param myDiaLogListener
	 */
	public void initTwoButtonDialogListenerTShow(myDiaLogListener myDiaLogListener) {
		setDialogListener(myDiaLogListener);
		show();
	}

	String mCancleBtnName = "";
	String mEnterBtnName = "";
	String mNeutralBtnName = "";
	String mTitleString = "";
	View mCustomContentView = null;
	int    btn_padding = FSScreen.dp2px(8);

	/**
	 * 设置两个button的监听（button_mydialog_enter和button_mydialog_cancel），并设置按钮名称
	 *
	 * @param myDiaLogListener
	 */
	public void initTwoButtonDialogListenerTShow(String cancleBtnName, String enterBtnName, myDiaLogListener myDiaLogListener) {
		btn_padding = FSScreen.dp2px(8);
		if (cancel != null && !TextUtils.isEmpty(cancleBtnName)) {

			cancel.setText(cancleBtnName);
		}
		if (enter != null && !TextUtils.isEmpty(enterBtnName)) {
			enter.setText(enterBtnName);
		}
		mCancleBtnName = cancleBtnName;
		mEnterBtnName = enterBtnName;
		setDialogListener(myDiaLogListener);
		show();
	}

	public void setCancelButtonTitle(String cancelTitle) {
		if (cancel != null && !TextUtils.isEmpty(cancelTitle)) {
			cancel.setText(cancelTitle);
		}

		mCancleBtnName = cancelTitle;
	}
	/**
	 * 设置确定取消的按钮
	 * @param okTitle
	 */
	public void setOkButtonTitle(String okTitle) {

		if (enter != null && !TextUtils.isEmpty(okTitle)) {
			enter.setText(okTitle);
		}
		mEnterBtnName = okTitle;
	}

	public void setTitle(String title) {
		if (!TextUtils.isEmpty(title.toString()) && titleBlock != null && titleLayout != null) {
			titleLayout.setVisibility(View.VISIBLE);
			titleBlock.setText(title);
		}else{
			setNoTitle();
		}
		mTitleString = title.toString();
	}
	public  void setNoTitle() {
		if ( titleLayout != null) {
			titleLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * @param message
	 *            弹窗显示信息
	 * @param title
	 *            弹窗标题
	 * @param okButtonTitle
	 *            右侧取消按钮显示文字 默认确定
	 * @param cancelButtonTitle
	 *            左侧取消按钮显示文字 默认取消
	 * @param isCancelable
	 *            是否响应返回操作
	 * @param isProcessCancleClickWhenBack
	 *            在点击了返回键后，是否执行cancel按钮的点击事件
	 * @param setCanceledOnTouchOutside
	 *            是否允许点击弹框窗体外侧让对话框消失
	 * @param showType
	 *            显示中间文本的类型
	 * @param confirmListener
	 *            确认按钮监听事件
	 * @param cancelListener
	 *            取消按钮监听事件
	 */
	public static CommonDialog showDialog(final Context context, String message, String title, String okButtonTitle, String cancelButtonTitle, boolean isCancelable,
										  boolean isProcessCancleClickWhenBack, boolean setCanceledOnTouchOutside, int showType, final android.view.View.OnClickListener confirmListener, final android.view.View.OnClickListener cancelListener) {
		if (context instanceof Activity) {
			Activity act = (Activity) context;
			if (act.isFinishing()) {
				return null;
			}
		}
		final CommonDialog confirmDialog = new CommonDialog(context, null, showType);
		confirmDialog.setDialogListener(new CommonDialog.myDiaLogListener() {

			@Override
			public void onClick(View view) {

				if (view.getId() == R.id.button_mydialog_cancel) {
					confirmDialog.dismiss();
					if (cancelListener != null) {
						cancelListener.onClick(view);
					}

				} else if (view.getId() == R.id.button_mydialog_enter) {
					confirmDialog.dismiss();
					if (confirmListener != null)
						confirmListener.onClick(view);
				}

			}
		});

		if (!TextUtils.isEmpty(title))
			confirmDialog.setTitle(title);
		if (!TextUtils.isEmpty(okButtonTitle))
			confirmDialog.setOkButtonTitle(okButtonTitle);
		if (!TextUtils.isEmpty(cancelButtonTitle))
			confirmDialog.setCancelButtonTitle(cancelButtonTitle);

		confirmDialog.setProceesCancelClickWhenBackBtn(isProcessCancleClickWhenBack);
		confirmDialog.setBackCancelable(isCancelable);
		confirmDialog.setCanceledOnTouchOutside(setCanceledOnTouchOutside);
		confirmDialog.setMessage(message);
		confirmDialog.show();

		return confirmDialog;
	}

}
