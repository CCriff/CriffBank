package com.criff.repository;

import java.util.List;

import com.criff.models.User;

public interface UserDao {

	User insertUser(User user);
	
	boolean getUserAccounts(User user, boolean hasAccts);
	
	User checkUser(User user, String email, String pwLogin);
	
	List<User> getAllUsers(int user_id);
	
	List<User> getSingleUser(int user_id);
	
	void EditUserAccountUsername(int acct_id, String email);
	
	void EditUserAccountPassword(int acct_id, String pw_hash);
	
}
