package com.example.model;

public class tb_outaccount {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(double outMoney) {
		this.outMoney = outMoney;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public String getOutAddr() {
		return outAddr;
	}
	public void setOutAddr(String outAddr) {
		this.outAddr = outAddr;
	}
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	private int id;//支出id
	private double outMoney;//支出金额
	private String outTime;//支出日期
	private String outType;//支出类型
	private String outAddr;//支出地址
	private String Mark;//支出备注

	public tb_outaccount() {
		// TODO Auto-generated constructor stub
		super();
	}
	public tb_outaccount(int id,double outMoney,String outTime,
			String outType,String outAddr,String Mark){
		super();
		this.id=id;
		this.outMoney=outMoney;
		this.outTime=outTime;
		this.outAddr=outAddr;
		this.outType=outType;
		this.Mark=Mark;
	}
	
	

}
