package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBOpenHleper extends SQLiteOpenHelper{
	private static final int VERSION=1;
	private static final String DBNAME="account.db";
	private static final String id="id";
	public DBOpenHleper(Context Context) { 
		// TODO Auto-generated constructor stub
		super(Context,DBNAME,null,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table tb_outaccount (_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),address varchar(100),mark varchar(200))");// 创建支出信息表
		db.execSQL("create table tb_inaccount (_id integer primary key,money decimal,time varchar(10),"
				+ "type varchar(10),handler varchar(100),mark varchar(200))");// 创建收入信息表
		db.execSQL("create table tb_pwd (password varchar(20))");// 创建密码表
		db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))");// 创建便签信息表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
