package com.capgemini.bankingproject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.bankingproject.bean.BankCustomer;
import com.capgemini.bankingproject.bean.Transcation;
import com.capgemini.bankingproject.exception.BankException;
import com.capgemini.connectionclass.DBConnection;

public class BankDaoImpl implements IBankDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean status = false;
	int row = -1;
	Transcation transaction;

	public int insertCustomer(BankCustomer customer) {
		int custId = 0;
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select BANKSEQ.NEXTVAL from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				custId = resultSet.getInt(1);
				statement = connection.prepareStatement("insert into BankCustomer values(?,?,?,?,?,?,?)");
				statement.setInt(1, custId);
				statement.setString(2, customer.getMobile());
				statement.setString(3, customer.getEmail());
				statement.setString(4, customer.getAddress());
				statement.setString(5, customer.getName());
				statement.setLong(6, customer.getAccountNo());
				statement.setDouble(7, customer.getAvailableBalance());

				row = statement.executeUpdate();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return custId;
	}

	@Override
	public double showBalance(int custId) {
		double balance=0;
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select availableBalance from bankcustomer where custId = ?");

			statement.setInt(1, custId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				//customer = new BankCustomer();

				balance = resultSet.getDouble("availableBalance");
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return balance;

	}

	@Override
	public double depositBalance(double amount, int custId) throws BankException {

		double availableBalance = 0, updatedBalance = 0;
		try (Connection connection = DBConnection.getConnection();) {
			statement = connection.prepareStatement("select sysdate from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Date transDate = resultSet.getDate(1);
				statement = connection.prepareStatement("select availablebalance from bankCustomer where custId=?");
				statement.setLong(1, custId);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					availableBalance = rs.getLong("availableBalance");
					statement = connection
							.prepareStatement("update bankCustomer set availableBalance=? where custId=?");
					updatedBalance = availableBalance + amount;
					statement.setDouble(1, updatedBalance);
					statement.setDouble(2, custId);
					statement.executeUpdate();
					statement = connection.prepareStatement("select TRANSSEQ.NEXTVAL from dual");
					resultSet = statement.executeQuery();
					if (resultSet.next()) {
						int transId = resultSet.getInt(1);

						statement = connection.prepareStatement("insert into TRANSACTION values(?,?,?,?,?,?,? )");

						statement.setDouble(1, transId);
						statement.setString(2, "credit");

						statement.setDate(3, transDate);
						statement.setString(4, String.valueOf(custId));
						statement.setString(5, String.valueOf(custId));
						statement.setDouble(6, amount);
						statement.setInt(7, custId);

						/* statement.setInt(8, sourceCustId); */

						statement.executeUpdate();

					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updatedBalance;
	}

	@Override
	public void transferFunds(int sourceCustId, int destinationCustId, double amount) throws BankException {

		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select sysdate from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Date transDate = resultSet.getDate(1);

				statement = connection.prepareStatement("select availableBalance from bankCustomer where custId=?");
				statement.setLong(1, sourceCustId);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					double availableBalance = rs.getLong("availableBalance");
					statement = connection
							.prepareStatement("update bankCustomer set availableBalance=? where custId=?");
					double updatedBalanceAtSource = availableBalance - amount;
					statement.setDouble(1, updatedBalanceAtSource);
					statement.setDouble(2, sourceCustId);
					statement.executeUpdate();
					statement = connection.prepareStatement("select TRANSSEQ.NEXTVAL from dual");
					resultSet = statement.executeQuery();
					if (resultSet.next()) {
						int transId = resultSet.getInt(1);

						statement = connection.prepareStatement("insert into TRANSACTION values(?,?,?,?,?,?,? )");

						statement.setDouble(1, transId);
						statement.setString(2, "debit");

						statement.setDate(3, transDate);
						statement.setString(4, String.valueOf(destinationCustId));
						statement.setString(5, String.valueOf(sourceCustId));
						statement.setDouble(6, amount);
						statement.setInt(7, sourceCustId);

						/* statement.setInt(8, sourceCustId); */

						statement.executeUpdate();
					}
				}
				while (rs.next()) {
					double availableBalance = rs.getLong("availableBalance");
					statement = connection
							.prepareStatement("update bankCustomer set availableBalance=? where custId=?");
					double updatedBalanceAtDest = availableBalance + amount;
					statement.setDouble(1, updatedBalanceAtDest);
					statement.setDouble(2, destinationCustId);
					statement.executeUpdate();
					statement = connection.prepareStatement("select TRANSSEQ.NEXTVAL from dual");
					resultSet = statement.executeQuery();
					if (resultSet.next()) {
						int transId = resultSet.getInt(1);

						statement = connection.prepareStatement("insert into TRANSACTION values(?,?,?,?,?,?,? )");

						statement.setDouble(1, transId);
						statement.setString(2, "credit");

						statement.setDate(3, transDate);
						statement.setString(4, String.valueOf(destinationCustId));
						statement.setString(5, String.valueOf(sourceCustId));
						statement.setDouble(6, amount);
						statement.setInt(7, sourceCustId);

						statement.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public double withdrawBalance(double amount, int custId) {
		double availableBalance = 0, updatedBalance = 0;
		try (Connection connection = DBConnection.getConnection();) {
			statement = connection.prepareStatement("select sysdate from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Date transDate = resultSet.getDate(1);
				statement = connection.prepareStatement("select availablebalance from bankCustomer where custId=?");
				statement.setLong(1, custId);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					availableBalance = rs.getLong("availableBalance");
					statement = connection
							.prepareStatement("update bankCustomer set availableBalance=? where custId=?");
					updatedBalance = availableBalance - amount;
					statement.setDouble(1, updatedBalance);
					statement.setDouble(2, custId);
					statement.executeUpdate();
					if (resultSet.next()) {
						int transId = resultSet.getInt(1);

						statement = connection.prepareStatement("insert into TRANSACTION values(?,?,?,?,?,?,? )");

						statement.setDouble(1, transId);
						statement.setString(2, "debit");

						statement.setDate(3, transDate);
						statement.setString(4, String.valueOf(custId));
						statement.setString(5, String.valueOf(custId));
						statement.setDouble(6, amount);
						statement.setInt(7, custId);

						statement.executeUpdate();

					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return updatedBalance;
	}

	@Override
	public List<Transcation> printTransaction(int custId) {

		List<Transcation> transactionlist = new ArrayList<>();
		Transcation transcation = null;

		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select * from Transaction where custId=?");
			statement.setLong(1, custId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				transaction = new Transcation();
				transaction.setTransId(rs.getInt("transId"));
				transaction.setTransType(rs.getString("transType"));
				transaction.setTransDate(rs.getDate("transDate"));
				transaction.setCustId(rs.getInt("custId"));
				transaction.setSourceAccNo(rs.getLong("sourceAccNo"));
				transactionlist.add(transaction);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return transactionlist;
	}

}
