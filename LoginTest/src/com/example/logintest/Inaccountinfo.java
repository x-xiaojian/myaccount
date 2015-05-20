package com.example.logintest;

import java.util.List;

import com.example.dao.InaccountDao;
import com.example.model.tb_inaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Inaccountinfo extends Activity {
	ListView listInaccount;
	private String[] strs=null;
	private String strType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inaccountinfo);
		listInaccount=(ListView)findViewById(R.id.listInaccount);
		InaccountDao inaccount=new InaccountDao(Inaccountinfo.this);
		strType = "btnininfo";
		List<tb_inaccount> list_inaccount=inaccount.getScrollData(0, (int)inaccount.getCount()) ; 
		strs= new String[(int)inaccount.getCount()];// 设置字符串数组的长度
		int i=0;
		for(tb_inaccount tb_inaccount:list_inaccount){
			strs[i]=tb_inaccount.getId()+"|"+tb_inaccount.getInType()+"  "
					+String.valueOf(tb_inaccount.getInMoney())+"元  "+tb_inaccount.getInTime(); 
			i++;
		}
		
		listInaccount.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strs));

		
		
		listInaccount.setOnItemClickListener(new OnItemClickListener() {

			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String StrInfo=String.valueOf(((TextView) arg1).getText());
				String Strid=StrInfo.substring(0, StrInfo.indexOf('|'));
				Bundle inBundle=new Bundle();
				inBundle.putString("id", Strid);
				inBundle.putString("type", strType);
				Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);
				intent.putExtras(inBundle);
				startActivity(intent);
				
			}
		});
	}
	private void showinfo(int intType){
		InaccountDao inaccount=new InaccountDao(Inaccountinfo.this);
		strType = "btnininfo";
		List<tb_inaccount> list_inaccount=inaccount.getScrollData(0, (int)inaccount.getCount()) ; 
		strs= new String[(int)inaccount.getCount()];// 设置字符串数组的长度
		int i=0;
		for(tb_inaccount tb_inaccount:list_inaccount){
			strs[i]=tb_inaccount.getId()+"|"+tb_inaccount.getInType()+"  "
					+String.valueOf(tb_inaccount.getInMoney())+"元  "+tb_inaccount.getInTime(); 
			i++;
		}
		
		listInaccount.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strs));
	}
	
	
	protected void onRestart(){
		super.onRestart();
		showinfo(R.id.btnininfo);

		
	}
}
