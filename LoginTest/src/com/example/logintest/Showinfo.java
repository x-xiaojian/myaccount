package com.example.logintest;

import java.util.List;

import com.example.dao.InaccountDao;
import com.example.model.tb_inaccount;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class Showinfo extends Activity {
	private Button outBt,inBt,flagBt;
	ListView listaccount;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showinfo);
		outBt=(Button)findViewById(R.id.myOutaccount);
		inBt=(Button)findViewById(R.id.myInaccount);
		flagBt=(Button)findViewById(R.id.myflag);
		listaccount=(ListView)findViewById(R.id.listofbtn);
		
		outBt.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] strs=null;
				InaccountDao inaccount=new InaccountDao(Showinfo.this);
				List<tb_inaccount> list_inaccount=inaccount.getScrollData(0, (int)inaccount.getCount()) ; 
				strs= new String[(int)inaccount.getCount()];// 设置字符串数组的长度
				int i=0;
				for(tb_inaccount tb_inaccount:list_inaccount){
					strs[i]=tb_inaccount.getId()+"|"+tb_inaccount.getInType()+"  "
							+String.valueOf(tb_inaccount.getInMoney())+"元  "+tb_inaccount.getInTime(); 
					i++;
				}
				//listaccount.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strs));
				
				
			}
		});
	}
}
