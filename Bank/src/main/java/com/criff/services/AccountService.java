package com.criff.services;

import com.criff.main.CurrencyMenu;
import com.criff.repository.AccountsDao;
import com.criff.models.Account;
import com.criff.utility.InputUtility;

public class AccountService {
	public static Account acct = new Account();
	private static AccountsDao acctDao = new AccountsDao();
	private static UserService userService = new UserService();

	public static Account getAccount() {
		return acct;
	}

	public static void setAccount(Account acct) {
		AccountService.acct = acct;
	}
	
	public int getAcctId() {
		return acct.getAcctID();
	}
	
	public void setAcctId(int id) {
		id = acct.getAcctID();
	}
	
	public double getBalance() {
		return acct.getBalance();
	}
	
	public void setBalance(double balance) {
		balance = acct.getBalance();
	}
	
	public String getCurrency() {
		return acct.getCurrency();
	}
	
	public void setCurrency(String currency) {
		currency = acct.getCurrency();
	}
	
	public String getType() {
		return acct.getType();
	}
	
	public void setType(String acctType) {
		acctType = acct.getType();
	}
	
	public void openChecking() {
		acct.setBalance(0.00);
		acct.setCurrency(CurrencyMenu.process());
		acct.setType("CHECKING");

		// Create account in DB and add returned id(unique account number) to user accounts array
		acct = acctDao.createAcct(acct, userService.getUserId());
		UserService.newUser = false;
	}
	
	public void openSavings() {
		acct.setBalance(0.00);
		acct.setCurrency(CurrencyMenu.process());
		acct.setType("SAVINGS");

		// Create account in DB and add returned id(unique account number) to user accounts array
		acct = acctDao.createAcct(acct, userService.getUserId());
		UserService.newUser = false;
	}
	
	public void deposit() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *                 DEPOSIT                 *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		
		
		System.out.print("         Enter Account To Deposit Into: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		
		System.out.println();
		System.out.print("         Enter Amount To Be Deposited: ");
		double amt = InputUtility.getDoubleInput(100_000);
		
		acctDao.depositTo(acct_id, amt);
	}
	
	public void withdraw() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *                WITHDRAW                 *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		
		
		System.out.print("         Enter Account To Withdraw From: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		System.out.println();
		
		boolean acctFound = userService.getAcctNumbers().contains(acct_id);
		
		if(acctFound == false) {
			do {
				System.out.println("         ERROR: Please Enter A Correct Account Number!");
				System.out.print("         Enter Account To Withdraw From: "); // id for account
				System.out.println();
				acct_id = InputUtility.getIntChoice(100);
				acctFound = userService.getAcctNumbers().contains(acct_id);
			}while(acctFound == false);
		}
		
		System.out.print("         Enter Amount To Be Withdrawn: ");
		double amt = InputUtility.getDoubleInput(100_000);
		
		acctDao.withdrawFrom(acct_id, amt);
	}
	
	public void transfer() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *             TRANSFER MONEY              *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		
		
		System.out.print("         Enter Account To Transfer From: "); // id for account
		int acct_idFrom = InputUtility.getIntChoice(100);
		System.out.println();
		
		boolean acctFound = userService.getAcctNumbers().contains(acct_idFrom);
		
		if(acctFound == false) {
			do {
				System.out.println("         ERROR: Please Enter A Correct Account Number!");
				System.out.print("         Enter Account To Transfer From: "); // id for account
				System.out.println();
				acct_idFrom = InputUtility.getIntChoice(100);
				acctFound = userService.getAcctNumbers().contains(acct_idFrom);
			}while(acctFound == false);
		}
		
		System.out.print("         Enter Account To Transfer To: "); // id for account
		int acct_idTo = InputUtility.getIntChoice(100);
		System.out.println();
		
		System.out.print("         Enter Amount To Be Transferred: ");
		double amt = InputUtility.getDoubleInput(100_000);
		
		acctDao.transferMoney(acct_idFrom, acct_idTo, amt);
	}
	
	public void addUserToAccount() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *          ADD USER TO ACCOUNT            *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		
		
		System.out.print("         Enter Account To Add User: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		System.out.println();
		
		boolean acctFound = userService.getAcctNumbers().contains(acct_id);
		
		if(acctFound == false) {
			do {
				System.out.println("         ERROR: Please Enter A Correct Account Number!");
				System.out.print("         Enter Account To Transfer From: "); // id for account
				System.out.println();
				acct_id = InputUtility.getIntChoice(100);
				acctFound = userService.getAcctNumbers().contains(acct_id);
			}while(acctFound == false);
		}
		
		System.out.print("         Enter Username Or Email Of New User: "); // id for account
		String newUserEmail = InputUtility.getStringInput(100);
		
		acctDao.addUserToAcct(acct_id, newUserEmail);
	} 
	
	public void deleteAcct() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *         DELETE BANK ACCOUNT             *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		
		
		System.out.print("         Enter Account To Delete: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		acctDao.deleteAccount(acct_id, userService.getUserId());
	}
	
	public double exchangeCurrency(String startCurrency, double startAmount, String newCurrency) {
		double newAmount = 0.0;
		return newAmount;
	}
	
}
