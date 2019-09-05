package com.capgemini.bankingproject.dao;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;

public interface IBankDao {
	public static Map<Integer, Customer> customerList = new HashMap<>();

	long addToCustomer(Customer customer) throws BankException;

	void showBalance(int custId) throws BankException;

	int transferFunds(Transcation transaction, int sourceCustId, int destinationCustId) throws BankException;

	int depositBalance(int custId, Transcation transaction) throws BankException;

	int withdrawBalance(int custId, Transcation transaction) throws BankException;

	public Map<Integer, Transcation> printTransactionDetails(int custId);

}
