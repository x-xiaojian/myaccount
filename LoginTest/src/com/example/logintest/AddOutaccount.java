package com.example.logintest;

import java.io.Console;
import java.util.Calendar;

import com.example.dao.OutaccountDao;
import com.example.model.tb_outaccount;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOutaccount extends Activity {
	protected static final int DATE_DIALOG_ID=0;
	EditText txtTime,txtAddress,txtMark,txtMoney;
	Spinner spType;// 创建Spinner对象
	private Button btnSaveButton;
	private Button btnCancelButton;
	private int mYear;
	private int mMonth;
	private int mDay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_outaccount);
		txtMoney = (EditText) findViewById(R.id.txtMoney);// 获取金额文本框
		txtTime = (EditText) findViewById(R.id.txtTime);// 获取时间文本框
		txtAddress = (EditText) findViewById(R.id.txtAddress);// 获取地点文本框
		txtMark = (EditText) findViewById(R.id.txtMark);// 获取备注文本框
		spType = (Spinner) findViewById(R.id.spType);// 获取类别下拉列表 
		btnSaveButton = (Button) findViewById(R.id.btnSave);// 获取保存按钮
		btnCancelButton = (Button) findViewById(R.id.btnCancel);// 获取取消按钮
		txtTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		btnSaveButton.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strMoney=txtMoney.getText().toString();
				if(!strMoney.isEmpty()){  //金额不为空，则创建数据库并添加数据 
					OutaccountDao outaccout=new OutaccountDao(AddOutaccount.this);
					tb_outaccount tb_outaccount = new tb_outaccount(
							outaccout.getMaxID() + 1, Double
									.parseDouble(strMoney), txtTime
									.getText().toString(), spType
									.getSelectedItem().toString(),
							txtAddress.getText().toString(), txtMark
									.getText().toString());
					outaccout.add(tb_outaccount);
					// 弹出信息提示
					Toast.makeText(AddOutaccount.this, "〖新增支出〗数据添加成功！",
							Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(AddOutaccount.this, "请输入金额！",
							Toast.LENGTH_SHORT).show();
					
				}
			}
		});
		btnCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtAddress.setText("");
				txtMark.setText("");
				txtTime.setText("");
				txtTime.setHint("2015-04-23");
				txtMoney.setHint("0.00");
				spType.setSelection(0);
			}
		});
		final Calendar c=Calendar.getInstance();//获取当前系统时间
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
	}
	@Override
	protected Dialog onCreateDialog(int id)// 重写onCreateDialog方法
	{
		switch (id) {
		case DATE_DIALOG_ID:// 弹出日期选择对话框
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;// 为年份赋值
			mMonth = monthOfYear;// 为月份赋值
			mDay = dayOfMonth;// 为天赋值
			updateDisplay();// 显示设置的日期
		}
	};
	private void updateDisplay() {
		// 显示设置的时间
		txtTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));
	}
}
