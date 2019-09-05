package com.capgemini.bankingproject.service;

import java.util.Map;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;

public interface IBankService {
	public boolean custNameValidation(String name) throws BankException;
	public boolean custMobValidation(String mobile) throws BankException;
	public boolean custEmailValidation(String email)throws BankException;
	public long addToCustomer(Customer customer) throws BankException;
	public boolean validateAccount(int custId) throws BankException;
	public int transferFunds(Transcation transaction,int sourceCustId,int destinationCustId) throws BankException;
	void showBalance(int custId) throws BankException;
	public int depositBalance(int custId,Transcation transaction) throws BankException;
	public int withdrawBalance(int custId, Transcation transaction)throws BankException;
	public boolean validateCustId(int custId)throws BankException;
	public Map<Integer, Transcation> printTransactionDetails(int custId) throws BankException;
	
	

}
