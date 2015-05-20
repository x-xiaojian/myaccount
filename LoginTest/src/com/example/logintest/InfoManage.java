package com.example.logintest;

import com.example.dao.InaccountDao;
import com.example.dao.OutaccountDao;
import com.example.model.tb_inaccount;
import com.example.model.tb_outaccount;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoManage extends Activity {

	private String[] strInfo;
	private TextView tvtitle;
	private TextView textView;
	private EditText txtMoney;
	private EditText txtTime;
	private Spinner spType;
	private EditText txtHA;
	private EditText txtMark;
	private Button btnEdit;
	private Button btnDel;
	private String strid;
	private String strType;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_manage);
		tvtitle = (TextView) findViewById(R.id.inouttitle);// 获取标题标签对象
		textView = (TextView) findViewById(R.id.tvInOut);// 获取地点/付款方标签对象
		txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// 获取金额文本框
		txtTime = (EditText) findViewById(R.id.txtInOutTime);// 获取时间文本框
		spType = (Spinner) findViewById(R.id.spInOutType);// 获取类别下拉列表
		txtHA = (EditText) findViewById(R.id.txtInOut);// 获取地点/付款方文本框
		txtMark = (EditText) findViewById(R.id.txtInOutMark);// 获取备注文本框
		btnEdit = (Button) findViewById(R.id.btnInOutEdit);// 获取修改按钮
		btnDel = (Button) findViewById(R.id.btnInOutDelete);// 获取删除按钮

		Bundle myBundelForGetName = this.getIntent().getExtras();
		strid = myBundelForGetName.getString("id");// 记录id
		strType = myBundelForGetName.getString("type");
		;// 记录类型

		if (strType.equals("btnoutinfo")) 
		{
			OutaccountDao outaccountDAO = new OutaccountDao(InfoManage.this);// 创建OutaccountDAO对象
			tvtitle.setText("支出管理");// 设置标题为“支出管理”
			textView.setText("地  点：");// 设置“地点/付款方”标签文本为“地 点：”
			// 根据编号查找支出信息，并存储到Tb_outaccount对象中
			tb_outaccount tb_outaccount = outaccountDAO.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getOutMoney()));// 显示金额
			txtTime.setText(tb_outaccount.getOutTime());// 显示时间
			spType.setPrompt(tb_outaccount.getOutType());// 显示类别
			txtHA.setText(tb_outaccount.getOutAddr());// 显示地点
			txtMark.setText(tb_outaccount.getMark());// 显示备注

			btnEdit.setOnClickListener(new OnClickListener() {

				@SuppressLint("NewApi")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String strMoney = txtMoney.getText().toString();
					if (!strMoney.isEmpty()) { // 金额不为空，则创建数据库并添加数据
						OutaccountDao outaccout = new OutaccountDao(
								InfoManage.this);
						tb_outaccount tb_outaccount = new tb_outaccount(Integer
								.parseInt(strid), Double.parseDouble(strMoney),
								txtTime.getText().toString(), spType
										.getSelectedItem().toString(), txtHA
										.getText().toString(), txtMark
										.getText().toString());
						outaccout.update(Integer.parseInt(strid), tb_outaccount);
						// 弹出信息提示
						Toast.makeText(InfoManage.this, "〖我的支出〗数据修改成功！",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(InfoManage.this, "请输入金额！",
								Toast.LENGTH_SHORT).show();

					}

				}
			});
		}
		
		else if(strType.equals("btnininfo")){
			tvtitle.setText("收入管理");// 设置标题为“支出管理”
			textView.setText("支付方：");// 设置“地点/付款方”标签文本为“地 点：”
			InaccountDao inaccount=new InaccountDao(InfoManage.this);
			// 根据编号查找支出信息，并存储到Tb_outaccount对象中
			tb_inaccount tb_inaccount = inaccount.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getInMoney()));// 显示金额
			txtTime.setText(tb_inaccount.getInTime());// 显示时间
			spType.setPrompt(tb_inaccount.getInType());// 显示类别
			txtHA.setText(tb_inaccount.getInHanddle());// 显示地点
			txtMark.setText(tb_inaccount.getMark());// 显示备注

			btnEdit.setOnClickListener(new OnClickListener() {

				@SuppressLint("NewApi")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String strMoney = txtMoney.getText().toString();
					if (!strMoney.isEmpty()) { // 金额不为空，则创建数据库并添加数据
						InaccountDao inaccout = new InaccountDao(
								InfoManage.this);
						tb_inaccount tb_inaccount = new tb_inaccount(Integer
								.parseInt(strid), Double.parseDouble(strMoney),
								txtTime.getText().toString(), spType
										.getSelectedItem().toString(), txtHA
										.getText().toString(), txtMark
										.getText().toString());
						inaccout.update(Integer.parseInt(strid), tb_inaccount);
						// 弹出信息提示
						Toast.makeText(InfoManage.this, "〖我的收入〗数据修改成功！",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(InfoManage.this, "请输入金额！",
								Toast.LENGTH_SHORT).show();

					}

				}
			});
		}

	}
}
