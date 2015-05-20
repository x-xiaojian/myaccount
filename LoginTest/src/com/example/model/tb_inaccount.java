package com.example.model;

public class tb_inaccount {
	
	private int id;//支出id
	private double inMoney;//支出金额
	private String inTime;//支出日期
	private String inType;//支出类型
	private String inHanddle;//支出方式
	private String Mark;//支出备注

	public tb_inaccount() {
		// TODO Auto-generated constructor stub
		super();
	}
	public tb_inaccount(int id,double inMoney,String inTime,
			String inType,String inHanddle,String Mark){
		super();
		this.id=id;
		this.inMoney=inMoney;
		this.inTime=inTime;
		this.inType=inType;
		this.inHanddle=inHanddle;
		this.Mark=Mark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getInMoney() {
		return inMoney;
	}
	public void setInMoney(double inMoney) {
		this.inMoney = inMoney;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getInType() {
		return inType;
	}
	public void setInType(String inType) {
		this.inType = inType;
	}
	public String getInHanddle() {
		return inHanddle;
	}
	public void setInHanddle(String inHanddle) {
		this.inHanddle = inHanddle;
	}
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	
	

}
