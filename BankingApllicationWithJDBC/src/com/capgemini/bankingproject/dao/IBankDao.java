package com.capgemini.bankingproject.dao;

import java.util.List;

import com.capgemini.bankingproject.bean.BankCustomer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;

public interface IBankDao {

	int insertCustomer(BankCustomer customer);

	double showBalance(int custId);

	double depositBalance(double amount, int custId) throws BankException;

	void transferFunds(int sourceCustId, int destinationCustId, double amount) throws BankException;

	double withdrawBalance(double amount, int custId);

	public List<Transcation> printTransaction(int custId);

}
