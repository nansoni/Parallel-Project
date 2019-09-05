package com.capgemini.bankingproject.presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.bankingproject.bean.BankCustomer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;
import com.capgemini.bankingproject.service.BankServiceImpl;
import com.capgemini.bankingproject.service.IBankService;
import com.sun.webkit.ContextMenu.ShowContext;

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

					switch (choice) {

					case 1:
						System.out.println("Enter Customer name:");
						String name = scanner.nextLine();
						scanner.nextLine();
						System.out.println("Enter mobile :");
						String mobile = scanner.nextLine();

						System.out.println("Enter email :");
						String email = scanner.nextLine();

						System.out.println("Enter customer address:");
						String address = scanner.nextLine();
						long accountNo = 0;
						double balance = 0.0;

						BankCustomer customer = new BankCustomer(name, mobile, email, address, accountNo, +balance);
						int custId = service.addToCustomer(customer);

						System.out.println("generated id " + custId);
						break;

					case 2: {

						boolean custFlag = false;

						scanner = new Scanner(System.in);
						System.out.println("Enter custId to check balance:");
						int id = scanner.nextInt();
						double balance1 = service.showBalance(id);
						System.out.println("Your balance is :" + balance1);
						break;
					}

					case 3: {
						scanner = new Scanner(System.in);
						System.out.println("Enter custId to deposit amount:");
						int id = scanner.nextInt();
						System.out.println("Enter amount to deposit");
						double amount = scanner.nextDouble();
						int row = (int) service.depositBalance(amount, id);
						break;
					}
					case 4: {
						scanner = new Scanner(System.in);
						System.out.println("Enter your custId to withdraw amount:");
						int id = scanner.nextInt();
						System.out.println("Enter amount to withdraw");
						double amount = scanner.nextDouble();
						int row = (int) service.withdrawBalance(amount, id);
						break;

					}

					case 5: {
						scanner = new Scanner(System.in);
						System.out.println("Enter your custId to send amount:");
						int senderCustId = scanner.nextInt();
						System.out.println("Enter custId of recipient:");
						int destCustId = scanner.nextInt();
						if (senderCustId == destCustId) {
							System.out.println("Senders and receivers account cannot be same.");
							break;
						}
						System.out.println("Enter ampount to send  to recipient:");
						double amount = scanner.nextDouble();
						if (amount > service.showBalance(senderCustId)) {
							System.err.println("insufficient balance");

						} else
							service.transferFunds(senderCustId, destCustId, amount);
						break;
					}
					case 6: {
						System.out.println("Transactions");
						System.out.println("Enter the custId to check your transaction");
						int id = scanner.nextInt();

						List<Transcation> list = service.printTransaction(id);
						System.out.println(list);
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
				} catch (BankException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
