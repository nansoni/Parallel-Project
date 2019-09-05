package com.capgemini.bankingproject.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.bean.Transcation;

public class BankDaoImpl<Transaction> implements IBankDao {

	public static Map<Integer, Customer> customerList = new HashMap<>();
	public static Map<Integer, Transcation> transactionList = new HashMap<>();

	@Override
	public long addToCustomer(Customer customer) {
		int custId = (int) (Math.random() * 100000);

		customer.setCustId(custId);

		customerList.put(custId, customer);

		return custId;

	}

	public static Map<Integer, Customer> getCustomerList() {
		return customerList;
	}

	public static void setCustomerList(Map<Integer, Customer> customerList) {
		BankDaoImpl.customerList = customerList;
	}

	public static Map<Integer, Transcation> gettransactionList() {
		return transactionList;
	}

	public static void settransactionList(Map<Integer, Transcation> transactionList) {
		BankDaoImpl.transactionList = transactionList;
	}

	@Override
	public int transferFunds(Transcation transaction, int sourceCustId, int destinationCustId) {

		int transId = 0;
		double balanceSourceCust = 0.0;
		double balanceDestinationCust = 0.0;
		Customer customer1 = BankDaoImpl.customerList.get(sourceCustId);
		Customer customer2 = BankDaoImpl.customerList.get(destinationCustId);
		Set keys = customerList.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			int key = (int) iterator.next();
			if (key == sourceCustId) {
				balanceSourceCust = customer1.getBalance() - transaction.getAmount();
				if (balanceSourceCust > customer1.getBalance()) {
					transaction.setTransType("Debit");
				} else
					transaction.setTransType("Credit");
				customer1.setBalance(balanceSourceCust);
				balanceDestinationCust = customer1.getBalance() + transaction.getAmount();
				customer2.setBalance(balanceDestinationCust);
				break;

			}

		}

		transId = (int) (Math.random() * 1000);
		Date transDate = new Date();
		transaction.setTransDate(transDate);
		transaction.setTransId(transId);
		transactionList.put(sourceCustId, transaction);
		return transId;
	}

	@Override
	public void showBalance(int custId) {
		Customer customer = BankDaoImpl.customerList.get(custId);
		Set keys = customerList.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			int key = (int) iterator.next();
			if (key == custId) {
				double balance = customer.getBalance();
				System.out.println("Your available balance is:" + balance);
			}
		}
	}

	@Override
	public int depositBalance(int custId, Transcation transaction) {
		int transId = 0;
		Customer customer = BankDaoImpl.customerList.get(custId);
		Set keys = customerList.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			int key = (int) iterator.next();
			if (key == custId) {
				double balance = customer.getBalance() + transaction.getAmount();
				customer.setBalance(balance);
				transaction.setTransType("debit");
				transaction.setAmount(balance);
				Date transDate = new Date();
				transaction.setTransDate(transDate);
				transId = (int) (Math.random() * 1000);
				transaction.setTransId(transId);
				transactionList.put(custId, transaction);
				System.out.println("Transaction Succesfull!!Your transaction ID is:" + transId
						+ "Your available balance is:" + balance);

			}

		}
		return transId;

	}

	@Override
	public int withdrawBalance(int custId, Transcation transaction) {
		int transId = 0;
		Customer customer = BankDaoImpl.customerList.get(custId);
		Set keys = customerList.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			int key = (int) iterator.next();
			if (key == custId) {

				if (customer.getBalance() < transaction.getAmount()) {
					double balance = customer.getBalance() - transaction.getAmount();
					customer.setBalance(balance);
					transaction.setTransType("debit");
					transaction.setAmount(balance);
					Date transDate = new Date();
					transaction.setTransDate(transDate);
					transId = (int) (Math.random() * 1000);
					transaction.setTransId(transId);
					transactionList.put(custId, transaction);
					System.out.println("Transaction Succesfull!!Your transaction ID is:" + transId
							+ "Your available balance is:" + balance);
				} else {
					System.err.println(
							"Low Balance!Available balance= " + customer.getBalance() + " cannot initiate transaction");
				}

			}
		}
		return transId;

	}

	@Override
	public Map<Integer, Transcation> printTransactionDetails(int custId) {
		return BankDaoImpl.gettransactionList();

	}

}
