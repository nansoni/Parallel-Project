package com.capgemini.bankingproject.service;



import java.util.List;

import com.capgemini.bankingproject.bean.BankCustomer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;

public interface IBankService {
	/*public boolean custNameValidation(String name) throws BankException;
	public boolean custMobValidation(String mobile) throws BankException;
	public boolean custEmailValidation(String email)throws BankException;
	
	public boolean validateDestinationAccount(int custId) throws BankException;
	public int transferFunds(Transcation transaction,int sourceCustId,int destinationCustId) throws BankException;
	void showBalance(int custId) throws BankException;
	public int depositBalance(int custId,Transcation transaction) throws BankException;
	public int withdrawBalance(int custId, Transcation transaction)throws BankException;
	public boolean validateCustId(int custId)throws BankException;
	public Map<Integer, Transcation> printTransactionDetails(int custId) throws BankException;
	*/
		
	/*
	 * int saveCustomer(BankCustomer customer);
	 *  
	 * boolean updateCustomer(BankCustomer customer); boolean removeCustomer(int
	 * custId); BankCustomer viewById(int custId); List<BankCustomer> viewAll();
	 */
	  public int addToCustomer(BankCustomer customer);
	  public double showBalance(int custId);
	  public double depositBalance(double amount, int custId) throws BankException;
	  void transferFunds(int sourceCustId,int destinationCustId,double amount) throws BankException;
	public double withdrawBalance(double amount, int id);
	public List<Transcation> printTransaction(int custId);
}
