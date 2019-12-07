package com.criff.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.criff.models.Account;
import com.criff.models.User;
import com.criff.utility.InputUtility;

public class AccountsDaoImpl implements AccountDao {
	String url = "jdbc:postgresql://criffbanking.cua8a0jy3iil.us-east-2.rds.amazonaws.com/criffbanking";
	String user = "postgres";
	String pw = "lost1soul";
	private static Connection conn;
	public static Account acct = new Account();
	private static DecimalFormat df2 = new DecimalFormat("#,###.00");

	public void connect() {
		try{
			conn = DriverManager.getConnection(url,user,pw);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Account createAcct(Account acct, int user_id) {
		connect();
		String query = "INSERT INTO account_table (acct_type, currency, balence, acct_status) " +
				"VALUES (?, ?, ?, ?) RETURNING id";
		String query2 = "INSERT INTO users_accounts (users_id, accounts_id) VALUES (?,?)";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, acct.getType());
			s.setString(2, acct.getCurrency());
			s.setDouble(3, acct.getBalance());
			s.setBoolean(4, acct.isAcctStatus());
			ResultSet resultSet = s.executeQuery();	
			
			if (resultSet.next()) {
				acct.setAcctID(resultSet.getInt("id"));
			}
			s.close();
		
			PreparedStatement s2 = conn.prepareStatement(query2);
			s2.setInt(1, user_id);
			s2.setInt(2, acct.getAcctID());
			s2.executeUpdate();
			s2.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return acct;
	}
	
	
	public void depositTo(int acct_id, double amt) {
		connect();		
		String query = "UPDATE account_table SET balence = balence + ? WHERE id = ?";	
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, amt);
			s.setInt(2, acct_id);
			s.executeUpdate();
			s.close();
			
		}catch(SQLException e) {
			InputUtility.displayHeader("         ERROR: Account Number Not Found.");
		}
		InputUtility.displayHeader("         DEPOSIT SUCCESSFUL!");
	}
	
	public void withdrawFrom(int acct_id, double amt) {
		connect();
		String query = "UPDATE account_table SET balence = balence - ? WHERE id = ?";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, amt);
			s.setInt(2, acct_id);
			s.executeUpdate();
			s.close();
			
		}catch(SQLException e) {
			InputUtility.displayHeader("         ERROR: Account Number Not Found.");
		}
		InputUtility.displayHeader("         WITHDRAW SUCCESSFUL!");
	}
	
	public void transferMoney(int acct_idFrom, int acct_idTo, double amt) {
		connect();
	
		String query = "UPDATE account_table SET balence = balence - ? WHERE id = ?";
		String query2 = "UPDATE account_table SET balence = balence + ? WHERE id = ?";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setDouble(1, amt);
			s.setInt(2, acct_idFrom);
			s.executeUpdate();
			s.close();
			
			PreparedStatement s2 = conn.prepareStatement(query2);
			s2.setDouble(1, amt);
			s2.setInt(2, acct_idTo);
			s2.executeUpdate();
			s2.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		InputUtility.displayHeader("         TRANSFER SUCCESSFUL!");
	}
	
	public void addUserToAcct(int acct_id, String newUserEmail) {
		connect();
		
		String query = "INSERT INTO users_accounts (users_id, accounts_id) VALUES ((SELECT id FROM user_table WHERE email = ?), ?);";
		//String query2 = "INSERT INTO users_accounts (users_id, accounts_id) VALUES (?,?)";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, newUserEmail);
			s.setInt(2, acct_id);
			s.executeUpdate();
			s.close();
			
			InputUtility.displayHeader("         USER ADDED SUCCESSFULLY! You Now Have A Joint Account.");
		}catch(SQLException e) {
			InputUtility.displayHeader("         ERROR: Username Or Email Not Found.");
		}
	}
	
	public void deleteAccount(int acct_id, int user_id) {
		connect();
		
		String query = "DELETE FROM users_accounts WHERE accounts_id = ? AND users_id = ?";
		String query2 = "DELETE FROM account_table WHERE id = ?";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, acct_id);
			s.setInt(2, user_id);
			s.executeUpdate();
			
			PreparedStatement s2 = conn.prepareStatement(query2);
			s2.setInt(1, acct_id);
			s2.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void approveAccount(int acct_id) {
		connect();
		
		String query = "update account_table set acct_status = true where id = ?";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, acct_id);
			s.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void denyAccount(int acct_id) {
		connect();
		
		String query = "update account_table set acct_status = false where id = ?";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setInt(1, acct_id);
			s.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Account> getAllAccounts(int acct_id) {
		connect();

		List<Account> accounts = null;
		
		String query = "select * from account_table";
		
		try {

			
			PreparedStatement s = conn.prepareStatement(query);
			
			accounts = new ArrayList<Account>();
			
			ResultSet resultSet = s.executeQuery();

			while (resultSet.next()) {
				accounts.add(
						new Account(
								resultSet.getInt(1),
								resultSet.getString(2), 
								resultSet.getString(3),
								resultSet.getFloat(4),
								resultSet.getBoolean(5)
								
						));	
				
				
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return accounts;
		
	}
	
	
	
	
}