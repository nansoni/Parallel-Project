package com.capgemini.bankingproject.test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.dao.BankDaoImpl;
import com.capgemini.bankingproject.dao.IBankDao;
import com.capgemini.bankingproject.exception.BankException;

public class BankingApplicationDaoTest {
	static IBankDao dao;
	static Customer customer;
	static Transcation transaction;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new BankDaoImpl();
		customer = new Customer();
		transaction = new Transcation();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
		customer = null;
		transaction = null;
	}

	@Before
	public void setUp() throws Exception {
		customer = new Customer(123,"Shreyasi","8962389127","samantashreyas@gmail.com","address",
				556774.7);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToCustomer() {
		assertNotNull(customer);
		
	}

	@Test
	public void testGetCustomerList() throws BankException {
		dao.customerList.clear();
		long custId= dao.addToCustomer(customer);
		assertNotEquals(1,dao.customerList.size()>1);
	}
		

	/*@Test
	public void testSetCustomerList() {
		
	}

	@Test
	public void testGettransactionList() {
	}

	@Test
	public void testSettransactionList() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransferFunds() {
		
	}

	@Test
	public void testShowBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testDepositBalance() {
		
	}

	@Test
	public void testWithdrawBalance() {
		
	}

	@Test
	public void testPrintTransactionDetails() {
		
	}*/

}
