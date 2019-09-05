package com.capgemini.bankingproject.bean;

import java.util.List;

public class BankCustomer {
	private int custId;
	private String name;
	private String mobile;
	private String email;
	private String address;
	private long accountNo;
	private double availableBalance;

	public BankCustomer() {
		super();
	}

	public BankCustomer(String name,String mobile, String email, String address, long accountNo, double balance) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.accountNo = accountNo;
		this.availableBalance = balance;
	}

	public BankCustomer(int custId, String name, String mobile, String email, String address, long accountNo,
			double balance) {
		super();
		this.custId = custId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.accountNo = accountNo;
		this.availableBalance = balance;
	}
	

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}



	
}
