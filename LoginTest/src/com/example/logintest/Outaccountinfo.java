package com.example.logintest;

import java.util.List;




import com.example.dao.OutaccountDao;
import com.example.model.tb_outaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Outaccountinfo extends Activity {
	public static final String FLAG = "id";// 定义一个常量，用来作为请求码
	ListView lvinfo;// 创建ListView对象
	String strType = "";// 创建字符串，记录管理类型

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outaccountinfo);// 设置布局文件
		lvinfo = (ListView) findViewById(R.id.listoutaccount);// 获取布局文件中的ListView组件
		ShowInfo(R.id.btnoutinfo);// 显示收入信息

//		ShowInfo(R.id.btnoutinfo);// 调用自定义方法显示支出信息

		lvinfo.setOnItemClickListener(new OnItemClickListener()// 为ListView添加项单击事件
		{
			// 覆写onItemClick方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());// 记录支出信息
				String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从支出信息中截取支出编号
				Intent intent = new Intent(Outaccountinfo.this,
						InfoManage.class);// 创建Intent对象
				Bundle myBundelForName=new Bundle(); 
				myBundelForName.putString("id",strid); 
				myBundelForName.putString("type",strType); 
				intent.putExtras(myBundelForName);// 设置传递数据
				startActivity(intent);// 执行Intent操作
			}
		});
	}

	private void ShowInfo(int intType) {// 用来根据传入的管理类型，显示相应的信息
		String[] strInfos = null;// 定义字符串数组，用来存储支出信息
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		strType = "btnoutinfo";// 为strType变量赋值
		OutaccountDao outaccountinfo = new OutaccountDao(Outaccountinfo.this);// 创建OutaccountDAO对象
		// 获取所有支出信息，并存储到List泛型集合中
		List<tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
				(int) outaccountinfo.getCount());
		strInfos = new String[listoutinfos.size()];// 设置字符串数组的长度
		int i = 0;// 定义一个开始标识
		for (tb_outaccount tb_outaccount : listoutinfos) {// 遍历List泛型集合
			// 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[i] = tb_outaccount.getId() + "|" + tb_outaccount.getOutType()
					+ " " + String.valueOf(tb_outaccount.getOutMoney()) + "元     "
					+ tb_outaccount.getOutTime();
			i++;// 标识加1
		}
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源

//		String[] strInfos = null;// 定义字符串数组，用来存储支出信息
//		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
//		strType = "btnoutinfo";// 为strType变量赋值
//		OutaccountDao outaccountinfo = new OutaccountDao(Outaccountinfo.this);// 创建OutaccountDAO对象
//		// 获取所有支出信息，并存储到List泛型集合中
//		List<tb_outaccount> listoutinfos = outaccountinfo.getScrollData(0,
//				(int) outaccountinfo.getCount());
//		strInfos = new String[listoutinfos.size()];// 设置字符串数组的长度
//		int i = 0;// 定义一个开始标识
//		for (tb_outaccount tb_outaccount : listoutinfos) {// 遍历List泛型集合
//			// 将支出相关信息组合成一个字符串，存储到字符串数组的相应位置
//			strInfos[i] = tb_outaccount.getId() + "|" + tb_outaccount.getOutType()
//					+ " " + String.valueOf(tb_outaccount.getOutMoney()) + "元     "
//					+ tb_outaccount.getOutTime();
//			i++;// 标识加1
//		}
//		// 使用字符串数组初始化ArrayAdapter对象
//		arrayAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, strInfos);
//		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();// 实现基类中的方法
		ShowInfo(R.id.btnoutinfo);// 显示收入信息
	}
}