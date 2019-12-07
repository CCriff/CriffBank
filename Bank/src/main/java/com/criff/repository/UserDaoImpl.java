package com.criff.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.criff.models.Account;
import com.criff.models.User;
import com.criff.utility.InputUtility;


public class UserDaoImpl implements UserDao {
	String url = "jdbc:postgresql://criffbanking.cua8a0jy3iil.us-east-2.rds.amazonaws.com/criffbanking";
	String user = "postgres";
	String pw = "lost1soul";
	private static Connection conn;
	private static DecimalFormat df2 = new DecimalFormat("#,###.00");

	public void connect() {
		try{
			conn = DriverManager.getConnection(url,user,pw);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public User insertUser(User user) {
			connect();
		
			String query = "INSERT INTO user_table (first_name, last_name, email, pw_hash) " +
					"VALUES (?, ?, ?, ?) RETURNING id";
			
			try {
				PreparedStatement s = conn.prepareStatement(query);
		
				s.setString(1, user.getFirstName());
				s.setString(2, user.getLastName());
				s.setString(3, user.getEmail());
				s.setString(4, user.getPass_hash());
				ResultSet resultSet = s.executeQuery();

				if (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			return user;
	}
	
	@Override
	public boolean getUserAccounts(User user, boolean hasAccts) {
		connect();
		
		String query = "SELECT * FROM account_table WHERE id = ANY (SELECT accounts_id" 
						+ "	FROM users_accounts WHERE users_id = ?) "; 
		
		try {
			PreparedStatement s = conn.prepareStatement(query);
		
			s.setInt(1, user.getId());
			ResultSet resultSet = s.executeQuery();
			
			while (resultSet.next()) {
				// retrieve and print the values for the current row
				int a = resultSet.getInt("id");
				String b = resultSet.getString("acct_type");
				String c = resultSet.getString("currency");
				double d = resultSet.getDouble("balence");
				boolean e = resultSet.getBoolean("acct_status");
				InputUtility.displayHeader("Account #:   " + a + " | " + b + ":    " + "$" + df2.format(d) + " " + c + "     " + "Account Opened: " + e);
				
				user.getAcctNumbers().add(a);
				hasAccts = true;
			}
			
			resultSet.close();
			s.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		user.setAcctNumbers(user.getAcctNumbers());
		return hasAccts;
	}
	
	@Override
	public User checkUser(User user, String email, String pwLogin) {
		connect();
				
		try {
			PreparedStatement s = conn.prepareStatement("SELECT id, first_name, last_name, pw_hash FROM user_table WHERE email = ?");
			s.setString(1, email);
			ResultSet resultSet = s.executeQuery();
			boolean emailFound = false;
			
			while(resultSet.next()) {
				// retrieve for the current row from the result set
				int id = resultSet.getInt(1);
				String first_name = resultSet.getString(2);
				String last_name = resultSet.getString(3);
				String password = resultSet.getString(4);
				emailFound = true;
				
				
				
				// If entered password equals hashed password -> set values for the user
				if(BCrypt.checkpw(pwLogin, password)){
					user.setId(id);
					user.setFirstName(first_name);
					user.setLastName(last_name);
					user.setEmail(email);
					user.setPass_hash(pwLogin);
				}else {
					InputUtility.displayHeader("         ERROR:  Incorrect Password!");
					System.exit(0);
				}
			}
			
			resultSet.close();
			s.close();
			
			// If User name or email is not found -> exit
			if(emailFound == false) {
				InputUtility.displayHeader("         ERROR:  Username Or Email Not Found!");
				System.exit(0);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public List<User> getAllUsers(int user_id) {
		connect();

		List<User> users = null;
		
		String query = "select * from user_table";
		
		try {
			
			PreparedStatement s = conn.prepareStatement(query);
			
			users = new ArrayList<User>();
			
			ResultSet resultSet = s.executeQuery();

			while (resultSet.next()) {
				users.add(
						new User(resultSet.getInt(1),
								resultSet.getString(2), 
								resultSet.getString(3),
								resultSet.getString(4),
								resultSet.getString(5)								
						));	
				
				
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return users;
		
	}
	
	@Override
	public void EditUserAccountUsername(int acct_id, String email) {
		connect();		
		String query = "UPDATE user_table SET email = ? WHERE id = ?";	
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, email);
			s.setInt(2, acct_id);
			s.executeUpdate();
			s.close();
			
		}catch(SQLException e) {
			InputUtility.displayHeader("         ERROR: User Email Or Username Could Not Be Updated.");
		}
		InputUtility.displayHeader("         EDIT SUCCESSFUL!");
	}
	
	@Override
	public void EditUserAccountPassword(int acct_id, String pw_hash) {
		connect();		
		String query = "UPDATE user_table SET pw_hash = ? WHERE id = ?";	
		try {
			PreparedStatement s = conn.prepareStatement(query);
			s.setString(1, pw_hash);
			s.setInt(2, acct_id);
			s.executeUpdate();
			s.close();
			
		}catch(SQLException e) {
			InputUtility.displayHeader("         ERROR: User Password Could Not Be Updated.");
		}
		InputUtility.displayHeader("         EDIT SUCCESSFUL!");
	}

	@Override
	public List<User> getSingleUser(int user_id) {
		connect();

		List<User> user = null;
		
		String query = "select * from user_table WHERE id = ?";
		
		try {

			
			PreparedStatement s = conn.prepareStatement(query);
			
			user = new ArrayList<User>();
			s.setInt(1, user_id);
			ResultSet resultSet = s.executeQuery();
			
			while (resultSet.next()) {
				user.add(
						new User(
								resultSet.getInt(1),
								resultSet.getString(2), 
								resultSet.getString(3),
								resultSet.getString(4),
								resultSet.getString(5)
								
						));	
				
				
			}
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return user;
	}	
}