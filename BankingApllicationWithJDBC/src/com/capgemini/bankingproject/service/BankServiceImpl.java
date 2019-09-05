package com.capgemini.bankingproject.service;


import java.util.List;

import com.capgemini.bankingproject.bean.BankCustomer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.dao.BankDaoImpl;
import com.capgemini.bankingproject.dao.IBankDao;
import com.capgemini.bankingproject.exception.BankException;


public class BankServiceImpl implements IBankService {
	IBankDao dao = new BankDaoImpl();

	@Override
	public int addToCustomer(BankCustomer customer){
		
			return dao.insertCustomer(customer);
		
		}

	@Override
	public double showBalance(int id) {
		
		return dao.showBalance(id);
		
	}

	@Override
	public double depositBalance(double amount, int custId) throws BankException {
		return dao.depositBalance(amount, custId);
	}

	@Override
	public void transferFunds(int sourceCustId, int destinationCustId, double amount) throws BankException {
		dao.transferFunds(sourceCustId, destinationCustId, amount);
		
	}

	@Override
	public double withdrawBalance(double amount, int custId) {
		return dao.withdrawBalance(amount, custId);
	}

	@Override
	public List<Transcation> printTransaction(int custId) {
		 return dao.printTransaction(custId);
		
	}


	
	}
	
	/*public boolean custNameValidation(String name) throws BankException {
		boolean resultNameFlag=false;
		String nameRegex="[A-Z]{1}[a-z]{8,}$";
		if(!Pattern.matches(nameRegex, name)) {
			resultNameFlag = false;
			throw new BankException("Customer Name should start with capital and lenght gt 9");
		}
		else {
			resultNameFlag = true;
		}
		return resultNameFlag;
	}

	@Override
	public boolean custMobValidation(String mobile) throws BankException {
		boolean resultMobFlag= false;
		String mobRegex ="[6,7,8,9]{1}[0-9]{9}$";
		if(!Pattern.matches(mobRegex, mobile)) {
			throw new BankException("Mobile no should start with 7,8,9 and should be 10 digit");
		}
		else {
			resultMobFlag= true;
		}
		return resultMobFlag;
	}

	@Override
	public boolean custEmailValidation(String email) throws BankException {
		boolean resultEmailFlag= false;
		String emailRegex="^[a-z]{5,}@[a-z]{3,}.com$";
		if(!Pattern.matches(emailRegex, email)){
			throw new BankException("Email should be in small case end with '.com' ");
		}
		else {
			resultEmailFlag= true;
		}
		return resultEmailFlag;
	}*/



	/*@Override
	public boolean validateDestinationAccount(int custId) throws BankException {
		boolean flag = false;
		Customer customer = BankDaoImpl.getCustomerList().get(custId);
		if(customer!= null) {
			flag = true;
		
		}
		else {
			flag= false;
			throw new BankException("Entered customer id is invalid");
		}
		return flag;
	}

	@Override
	public int transferFunds(Transcation transaction,int sourceCustId, int destinationCustId) throws BankException {
	
		return dao.transferFunds(transaction,sourceCustId,destinationCustId );
	}

	@Override
	public void showBalance(int custId) throws BankException {
		dao.showBalance(custId);
	}

	@Override
	public int depositBalance(int custId,Transcation transaction) throws BankException {
		
		
		return dao.depositBalance(custId,transaction);
	}

	@Override
	public int withdrawBalance(int custId, Transcation transaction) throws BankException {
		return dao.withdrawBalance(custId,transaction);
		
	}

	@Override
	public boolean validateCustId(int custId) throws BankException {
		boolean flag = false;
		Transcation transaction = BankDaoImpl.transactionList.get(custId);
		if(transaction!= null) {
			flag = true;
		
		}
		else {
			flag= false;
			throw new BankException("Entered customer id is invalid");
		}
		return flag;
	
	}

	@Override
	public Map<Integer, Transcation> printTransactionDetails(int custId) throws BankException {
		return dao.printTransactionDetails(custId);  
			
	}
*/
	
	




	
	
											
	


