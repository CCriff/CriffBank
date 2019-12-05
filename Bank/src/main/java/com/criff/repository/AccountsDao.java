package com.criff.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.criff.models.Account;

public class AccountsDao {
	String url = "jdbc:postgresql://criffbanking.cua8a0jy3iil.us-east-2.rds.amazonaws.com/criffbanking";
	String user = "postgres";
	String pw = "lost1soul";
	private static Connection conn;

	public void connect() {
		try{
			conn = DriverManager.getConnection(url,user,pw);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Account createAcct(Account acct, int user_id) {
		connect();
		String query = "INSERT INTO account_table (acct_type, currency, balence) " +
				"VALUES (?, ?, ?) RETURNING id";
		String query2 = "INSERT INTO users_accounts (users_id, accounts_id) VALUES (?,?)";
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, acct.getType());
			s.setString(2, acct.getCurrency());
			s.setDouble(3, acct.getBalance());
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
			System.out.println("         ERROR: Account Number Not Found.");
		}
		System.out.println("         DEPOSIT SUCCESSFUL!");
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
			System.out.println("         ERROR: Account Number Not Found.");
		}
		System.out.println("         WITHDRAW SUCCESSFUL!");
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
		
		System.out.println("         TRANSFER SUCCESSFUL!");
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
			
			System.out.println("         USER ADDED SUCCESSFULLY!");
		}catch(SQLException e) {
			System.out.println("         ERROR: Username Or Email Not Found.");
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
}