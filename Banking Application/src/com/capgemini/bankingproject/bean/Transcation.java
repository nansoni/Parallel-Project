package com.capgemini.bankingproject.bean;

import java.util.Date;

public class Transcation {
	private int transId;
	private String transType;
	private Date transDate;
	private int sourceCustId;

	private long destinationCustId;
	private double amount;

	public Transcation() {
		super();

	}

	public Transcation(int sourceCustId, double amount) {
		super();
		this.sourceCustId = sourceCustId;
		this.amount = amount;
	}

	public Transcation(int sourceCustId, long destinationCustId, double amount) {
		super();
		this.sourceCustId = sourceCustId;
		this.destinationCustId = destinationCustId;
		this.amount = amount;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public int getSourceCustId() {
		return sourceCustId;
	}

	public void setSourceCustId(int sourceCustId) {
		this.sourceCustId = sourceCustId;
	}

	public long getDestinationAccNo() {
		return destinationCustId;
	}

	public void setDestinationAccNo(long destinationCustId) {
		this.destinationCustId = destinationCustId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transcation [transId=" + transId + ", transType=" + transType + ", transDate=" + transDate
				+ ", sourceCustId=" + sourceCustId + ", destinationCustId=" + destinationCustId + ", amount=" + amount
				+ "]";
	}

}