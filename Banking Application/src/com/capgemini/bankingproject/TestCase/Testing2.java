package com.capgemini.bankingproject.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.exception.BankException;
import com.capgemini.bankingproject.service.BankServiceImpl;
import com.capgemini.bankingproject.service.IBankService;

public class Testing2 {
	static IBankService obj2 = null;
	Customer customer = new Customer("Nandini", "nandini@gmail", "255", "nmno");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj2 = new BankServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			assertNotNull(obj2.addToCustomer(customer));
			assertNotEquals(obj2.addToCustomer(customer), 123);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
