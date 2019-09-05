package com.capgemini.bankingproject.presentation;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.bankingproject.bean.Customer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;
import com.capgemini.bankingproject.service.BankServiceImpl;
import com.capgemini.bankingproject.service.IBankService;

public class MainUI {

	public static void main(String[] args) {
		String continueChoice;
		boolean continueValue = false;

		Scanner scanner = null;
		do {

			System.out.println("*** welcome to Banking Application");
			System.out.println("1.Register/Create account");
			System.out.println("2.Show Balance");
			System.out.println("3.Deposit");
			System.out.println("4.Withdraw");
			System.out.println("5.Fund Transfer");
			System.out.println("6.Print Transactions");

			IBankService service = new BankServiceImpl();

			int choice = 0;
			boolean choiceFlag = false;

			do {
				scanner = new Scanner(System.in);
				System.out.println("Enter input:");
				try {
					choice = scanner.nextInt();
					choiceFlag = true;

					boolean nameFlag = false;

					String name = "";
					switch (choice) {

					case 1:
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter Customer name:");
							name = scanner.nextLine();
							try {
								service.custNameValidation(name);
								nameFlag = true;
							} catch (BankException e) {
								nameFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!nameFlag);

						String mobile = "";
						boolean mobileFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter mobile :");
							try {
								mobile = scanner.nextLine();
								service.custMobValidation(mobile);
								mobileFlag = true;
							} catch (InputMismatchException e) {
								mobileFlag = false;
								System.err.println("Cost should be in digits");
							} catch (BankException e) {
								mobileFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!mobileFlag);

						String email = "";
						boolean emailFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter email :");
							try {
								email = scanner.nextLine();
								service.custEmailValidation(email);
								emailFlag = true;
							} catch (InputMismatchException e) {
								emailFlag = false;
								System.err.println("enter valid email id");
							} catch (BankException e) {
								emailFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!emailFlag);

						System.out.println("Enter customer address:");
						String address = scanner.nextLine();

						Customer customer = new Customer(name, mobile, email, address);

						try {
							double custId = service.addToCustomer(customer);
							System.out.println("Your customer Id is " + custId);
						} catch (BankException e) {
							System.err.println(e.getMessage());
						}

						break;
					case 2: {

						boolean custFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter customerId to check balance:");
							try {
								int custId = scanner.nextInt();
								service.validateAccount(custId);

								service.showBalance(custId);
								break;
							} catch (BankException e) {
								custFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!custFlag);

						break;
					}
					case 3: {

						boolean custFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter your customerId to deposit balance:");
							try {
								int custId = scanner.nextInt();
								service.validateAccount(custId);
								System.out.println("Enter amount to deposit");
								double amount = scanner.nextDouble();
								Transcation transaction = new Transcation(custId,amount);
								service.depositBalance(custId, transaction);
								break;
							} catch (BankException e) {
								custFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!custFlag);

						break;
					}

					case 4: {

						boolean custFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter custId to withdraw balance:");
							try {
								int custId = scanner.nextInt();
								service.validateAccount(custId);
								System.out.println("Enter amount to withdraw");
								double amount = scanner.nextDouble();
								Transcation transaction = new Transcation(custId,amount);
								service.withdrawBalance(custId, transaction);
								break;
							} catch (BankException e) {
								custFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!custFlag);
						break;
					}

					case 5: {

						boolean custFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter your customer Id to send money:");
							try {
								int sourceCustId = scanner.nextInt();
								service.validateAccount(sourceCustId);
								System.out.println("Enter destinationId to trasfer money:");
								int destinationCustId = scanner.nextInt();
								service.validateAccount(destinationCustId);
								System.out.println("Enter amount to transfer");
								double amount = scanner.nextDouble();
								Transcation transaction = new Transcation(sourceCustId, destinationCustId,amount);
								int transId= service.transferFunds(transaction, sourceCustId, destinationCustId);
								System.out.println("Succesfull transaction with transaction Id:"+transId);
								break;
							} catch (BankException e) {
								custFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!custFlag);

						break;
					}

					case 6: {

						boolean custIdFlag = false;
						do {
							scanner = new Scanner(System.in);
							System.out.println("Enter custId to check transaction:");
							try {
								int custId = scanner.nextInt();
								service.validateAccount(custId);
								System.out.println(service.printTransactionDetails(custId));
								break;
							} catch (BankException e) {
								custIdFlag = false;
								System.err.println(e.getMessage());
							}
						} while (!custIdFlag);

						break;
					}

					case 7:
						System.out.println("Thank u, visit again");
						System.exit(0);
						break;
					default:
						System.out.println("input should be 1,2,3,4,5,6,7");
						choiceFlag = false;
						break;
					}

				} catch (InputMismatchException exception) {
					choiceFlag = false;
					System.err.println("input should contain only digits");
				}
			} while (!choiceFlag);

			do {
				scanner = new Scanner(System.in);
				System.out.println("do you want to continue again [yes/no]");
				continueChoice = scanner.nextLine();
				if (continueChoice.equalsIgnoreCase("yes")) {
					continueValue = true;
					break;
				} else if (continueChoice.equalsIgnoreCase("no")) {
					System.out.println("thank you");
					continueValue = false;
					break;
				} else {
					System.out.println("enter yes or no");
					continueValue = false;
					continue;
				}
			} while (!continueValue);

		} while (continueValue);
		scanner.close();

	}

}
