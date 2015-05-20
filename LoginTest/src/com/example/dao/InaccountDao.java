package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.logintest.AddOutaccount;
import com.example.model.tb_inaccount;
import com.example.model.tb_inaccount;

public class InaccountDao {
	private DBOpenHleper helper;

	private SQLiteDatabase db;

	public InaccountDao(Context context) {
		// TODO Auto-generated constructor stub
		helper=new DBOpenHleper(context);
		
	}

	public int getMaxID() {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_inaccount", null);
		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
			return cursor.getInt(0);// 获取访问到的数据，即最大编号
		}
		return 0;// 如果没有数据，则返回0
	}

	public void add(tb_inaccount tb_inaccount) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		db.execSQL(
				"insert into tb_inaccount (_id,money,time,type,handler,mark) values (?,?,?,?,?,?)",
				new Object[] { tb_inaccount.getId(), tb_inaccount.getInMoney(),
						tb_inaccount.getInTime(), tb_inaccount.getInType(),
						tb_inaccount.getInHanddle(), tb_inaccount.getMark() });
	}

	public long getCount() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select count(_id) from tb_inaccount",
				null);// 获取支出信息的记录数
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getLong(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}

	public List<tb_inaccount> getScrollData(int first, int last) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		List<tb_inaccount> tb_inaccount=new ArrayList<tb_inaccount>();
		Cursor cursor = db.rawQuery("select * from tb_inaccount limit ?,?",
				new String[]{String.valueOf(first),String.valueOf(last)});
		while(cursor.moveToNext())
		{
			tb_inaccount.add(new tb_inaccount(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("type")), cursor.getString(cursor
					.getColumnIndex("handler")), cursor.getString(cursor
					.getColumnIndex("mark"))));
		}
		return tb_inaccount;
	}

	public  tb_inaccount find(int id) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select * from tb_inaccount where _id=?",
				new String[] { String.valueOf(id) });
		if(cursor.moveToNext())
		{
			// 将遍历到的支出信息存储到tb_inaccount类中
			return new tb_inaccount(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("handler")),
					cursor.getString(cursor.getColumnIndex("mark")));
		}
		return null;// 如果没有信息，则返回null
	}

	public void update(int id,
			tb_inaccount tb_inaccount) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		db.execSQL(
				"update tb_inaccount set money=?,time=?,type=?,handler=?,mark=? where _id=?",
				new Object[] {  tb_inaccount.getInMoney(),
						tb_inaccount.getInTime(), tb_inaccount.getInType(),
						tb_inaccount.getInHanddle(), tb_inaccount.getMark(),id});
	}

}
