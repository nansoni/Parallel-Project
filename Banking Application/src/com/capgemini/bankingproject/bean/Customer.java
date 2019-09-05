package com.capgemini.bankingproject.bean;

import java.util.List;

public class Customer {
	private int custId;
	private String name;
	private String mobile;
	private String email;
	private String address;

	private double balance;
	private List<Transcation> transaction;

	public Customer() {
		super();
	
	}

	public Customer(int custId, String name, String mobile, String email, String address, double balance,
			List<Transcation> transaction) {
		super();
		this.custId = custId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;

		this.balance = balance;
		this.transaction = transaction;
	}

	public Customer(int custId, String name, String mobile, String email, String address, double balance) {
		super();
		this.custId = custId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;

		this.balance = balance;

	}

	public Customer(String name, String mobile, String email, String address) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transcation> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transcation> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", mobile=" + mobile + ", email=" + email
				+ ", address=" + address + ", balance=" + balance + ", transaction=" + transaction + "]";
	}

}
