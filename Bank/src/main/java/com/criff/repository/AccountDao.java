package com.criff.repository;

import java.util.List;

import com.criff.models.Account;

public interface AccountDao {
	
	Account createAcct(Account acct, int user_id);
	
	List<Account> getAllAccounts(int acct_id);
	
	List<Account> getSingleAccount(int acct_id);
	
	void change_account_status (int acct_id, boolean status);

    boolean get_acct_status(int acct_id);
    
    void denyAccount(int acct_id);
    
    void approveAccount(int acct_id, boolean status);
    
    void deleteAccount(int acct_id, int user_id);
    
    void addUserToAcct(int acct_id, String newUserEmail);
    
    void transferMoney(int acct_idFrom, int acct_idTo, double amt);
    
    void withdrawFrom(int acct_id, double amt);
    
    void depositTo(int acct_id, double amt);

}
