package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.logintest.AddOutaccount;
import com.example.model.tb_inaccount;
import com.example.model.tb_outaccount;

public class OutaccountDao {
	private DBOpenHleper helper;

	private SQLiteDatabase db;

	public OutaccountDao(Context context) {
		// TODO Auto-generated constructor stub
		helper=new DBOpenHleper(context);
		
	}

	public int getMaxID() {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_outaccount", null);
		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
			return cursor.getInt(0);// 获取访问到的数据，即最大编号
		}
		return 0;// 如果没有数据，则返回0
	}

	public void add(tb_outaccount tb_outaccount) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		db.execSQL(
				"insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",
				new Object[] { tb_outaccount.getId(), tb_outaccount.getOutMoney(),
						tb_outaccount.getOutTime(), tb_outaccount.getOutType(),
						tb_outaccount.getOutAddr(), tb_outaccount.getMark() });
	}

	public long getCount() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select count(_id) from tb_outaccount",
				null);// 获取支出信息的记录数
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getLong(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}

	public List<tb_outaccount> getScrollData(int first, int last) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		List<tb_outaccount> tb_outaccount=new ArrayList<tb_outaccount>();
		Cursor cursor = db.rawQuery("select * from tb_outaccount limit ?,?",
				new String[]{String.valueOf(first),String.valueOf(last)});
		while(cursor.moveToNext())
		{
			tb_outaccount.add(new tb_outaccount(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("type")), cursor.getString(cursor
					.getColumnIndex("address")), cursor.getString(cursor
					.getColumnIndex("mark"))));
		}
		return tb_outaccount;
	}

	public  tb_outaccount find(int id) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select * from tb_outaccount where _id=?",
				new String[] { String.valueOf(id) });
		if(cursor.moveToNext())
		{
			// 将遍历到的支出信息存储到Tb_outaccount类中
			return new tb_outaccount(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("address")),
					cursor.getString(cursor.getColumnIndex("mark")));
		}
		return null;// 如果没有信息，则返回null
	}

	public void update(int id,
			tb_outaccount tb_outaccount) {
		// TODO Auto-generated method stub
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		db.execSQL(
				"update tb_outaccount set money=?,time=?,type=?,address=?,mark=? where _id=?",
				new Object[] {  tb_outaccount.getOutMoney(),
						tb_outaccount.getOutTime(), tb_outaccount.getOutType(),
						tb_outaccount.getOutAddr(), tb_outaccount.getMark(),id});
	}

}
