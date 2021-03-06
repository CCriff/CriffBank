package com.criff.services;

import com.criff.main.CurrencyMenu;
import com.criff.repository.AccountsDaoImpl;
import com.criff.repository.UserDaoImpl;
import com.criff.models.Account;
import com.criff.models.User;
import com.criff.utility.InputUtility;

public class AccountService {
	private static UserDaoImpl userDao = new UserDaoImpl();
	private static Account acct = new Account();
	private static AccountsDaoImpl acctDao = new AccountsDaoImpl();
	private static UserService userService = new UserService();

	AccountsDaoImpl acct_Dao = new AccountsDaoImpl();
	User users = new User();
	
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
	
	public float getBalance() {
		return acct.getBalance();
	}
	
	public void setBalance(float balance) {
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
	
	public boolean isAcctStatus() {
		return acct.isAcctStatus();
	}
	
	public void setAcctStatus(boolean acctStatus) {
		acctStatus = acct.isAcctStatus();
	}
	
	public void openChecking() {
		acct.setBalance((float) 0.00);
		acct.setCurrency(CurrencyMenu.process());
		acct.setType("CHECKING");

		
		acct = acctDao.createAcct(acct, userService.getUserId());
		UserService.newUser = false;
		acct.setAcctStatus(false);
		
		InputUtility.displayHeader("Checking Account Was Created Successfully!");
		InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
	}
	
	public void openSavings() {
		acct.setBalance((float) 0.00);
		acct.setCurrency(CurrencyMenu.process());
		acct.setType("SAVINGS");

		
		acct = acctDao.createAcct(acct, userService.getUserId());
		UserService.newUser = false;
		acct.setAcctStatus(false);
		
		InputUtility.displayHeader("Savings Account Was Created Successfully!");
		InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
		
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
		
		if (acctDao.get_acct_status(acct_id) == true) {
					
		System.out.println();
		System.out.print("         Enter Amount To Be Deposited: ");
		double amt = InputUtility.getDoubleInput(500_000);
		
		acctDao.depositTo(acct_id, amt);
		} else {
			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
		}
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
		
		if (acctDao.get_acct_status(acct_id) == true) {
		
		System.out.print("         Enter Amount To Be Withdrawn: ");
		double amt = InputUtility.getDoubleInput(100_000);
		
		double balance = acct.getBalance();
		
		if(amt  > balance) {
			InputUtility.displayHeader("Transaction Cannot Be Completed. You Have Insuffient Funds.");
			System.out.print("         Enter Amount To Be Withdrawn: ");
			amt = InputUtility.getDoubleInput(100_000);			
		}  
		
		acctDao.withdrawFrom(acct_id, amt);
		} else {
			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
		}
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
		
		if (acctDao.get_acct_status(acct_idFrom) && (acctDao.get_acct_status(acct_idTo) == true)) {
		
		System.out.print("         Enter Amount To Be Transferred: ");
		double amt = InputUtility.getDoubleInput(500_000);
		
		acctDao.transferMoney(acct_idFrom, acct_idTo, amt);
			} else {
				InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
			}
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
				System.out.print("         Enter Account To Add User: "); // id for account
				System.out.println();
				acct_id = InputUtility.getIntChoice(100);
				acctFound = userService.getAcctNumbers().contains(acct_id);
			}while(acctFound == false);
		}
		
		if (acctDao.get_acct_status(acct_id) == true) {
		
		System.out.print("         Enter Username Or Email Of New User: "); // id for account
		String newUserEmail = InputUtility.getStringInput(100);
		
		acctDao.addUserToAcct(acct_id, newUserEmail);
		} else {
			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
		}
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
		
		InputUtility.displayHeader("Account With Account #: " + acct_id + " Has Been Closed! If You Had Funds In This Account,"
								 + " A Paper Check Will Be Mailed Out To You.");
	}
	
	public double exchangeCurrency(String startCurrency, double startAmount, String newCurrency) {
		double newAmount = 0.00;
		return newAmount;
	}

	public void approve() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *         APPROVE BANK ACCOUNT            *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		System.out.print("         Enter Account To Approve: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		
		if (acctDao.get_acct_status(acct_id) != true) {
		
		acctDao.approveAccount(acct_id, true);
		
		InputUtility.displayHeader("Account With Account #: " + acct_id + " Has Been Approved And Is Ready For Immediate Use!");
		} else {
			InputUtility.displayHeader("This Account Is Already On The Approved List!");
		}
	}

	public void deny() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *          DENY BANK ACCOUNT              *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		System.out.print("         Enter Account To Deny: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);
		
		if (acctDao.get_acct_status(acct_id) != false) {
		
		acctDao.denyAccount(acct_id);
		InputUtility.displayHeader("Account With Account #: " + acct_id + " Has Been Denied Or"
				                 + " Put On A Freeze! Please Contact Management For Further Information!");
		} else {
			InputUtility.displayHeader("This Account Is Already On The Denied List!");
		}
	}

	public void viewSingleAccount() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *       VIEW BANK ACCOUNT INFO            *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		System.out.print("         Enter Account ID To View: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);

		System.out.println(acctDao.getSingleAccount(acct_id));
	}

	public void viewAllAccounts() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *        VIEW ALL BANK ACCOUNTS           *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		int acct_id = 0;
		
		System.out.println(acctDao.getAllAccounts(acct_id));
				
	}
	
	public void viewSingleUser() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *       VIEW USER ACCOUNT INFO            *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		System.out.print("         Enter User ID To View: "); // id for account
		int user_id = InputUtility.getIntChoice(100);

		System.out.println(userDao.getSingleUser(user_id));
	}
	
	public void viewAllUsers() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *        VIEW ALL USER ACCOUNTS           *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		int user_id = 0;
		
		System.out.println(userDao.getAllUsers(user_id));
				
	}
	
	public void edit() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *        EDIT BANK ACCOUNT INFO           *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		System.out.print("         Which User Account Do You Want To Edit: "); // id for account
		int user_id = InputUtility.getIntChoice(100);
		
		System.out.print("         What Do You Want To Edit: (1) Username/Email (2) Password ");
		int edit = InputUtility.getIntChoice(2);
		if (edit == 1) {
			System.out.println("         What Do You Want To Change Your Uername/Email To:");
			String changeue = InputUtility.getStringInput(30);
			userService.setUserEmail(changeue);
			
			System.out.println("         Your Username/Email Has Been Changed To " + changeue + " .");
			userDao.EditUserAccountUsername(edit, changeue);
		} else if (edit == 2) {
			System.out.println("         What Do You Want To Change Your Password To:");
			String changepw = InputUtility.getStringInput(30);
			userService.setUserEmail(changepw);
			
			System.out.println("         Your Password Has Been Changed To " + changepw + " .");
			userDao.EditUserAccountPassword(edit, changepw);
		} else {
			InputUtility.displayHeader("Please Enter (1) to Change Your Username Or Email Enter (2) To Change Your Password.");
		}
		
		
	}
	
}
