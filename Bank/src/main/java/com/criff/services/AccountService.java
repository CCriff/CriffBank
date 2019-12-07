package com.criff.services;

import com.criff.main.CurrencyMenu;
import com.criff.repository.AccountsDaoImpl;
import com.criff.repository.UserDaoImpl;
import com.criff.models.Account;
import com.criff.models.User;
import com.criff.utility.InputUtility;

public class AccountService {
	private static UserDaoImpl userDao = new UserDaoImpl();
	private static User user = new User();
	private static Account acct = new Account();
	private static AccountsDaoImpl acctDao = new AccountsDaoImpl();
	private static UserService userService = new UserService();
	private static AccountService acctService = new AccountService();
	private static EmployeeService empService = new EmployeeService();


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

		// Create account in DB and add returned id(unique account number) to user accounts array
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

		// Create account in DB and add returned id(unique account number) to user accounts array
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
		
		System.out.println();
		System.out.print("         Enter Amount To Be Deposited: ");
		double amt = InputUtility.getDoubleInput(500_000);
		
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
		
//		if (status == false) {
//			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
//			} else {
		
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
		
		double balance = acct.getBalance();
		
		if(amt  > balance) {
			InputUtility.displayHeader("Transaction Cannot Be Completed. You Have Insuffient Funds.");
			System.out.print("         Enter Amount To Be Withdrawn: ");
			amt = InputUtility.getDoubleInput(100_000);			
		}  
		
		acctDao.withdrawFrom(acct_id, amt);
		//}
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
		
//		if (acct.isAcctStatus() == false) {
//			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
//			} else {
		
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
		double amt = InputUtility.getDoubleInput(500_000);
		
//		double balance = acct.getBalance();
//		
//		if(amt  > balance) {
//			InputUtility.displayHeader("Transaction Cannot Be Completed. You Have Insuffient Funds.");
//			System.out.print("         Enter Amount To Be Transferred: ");
//			amt = InputUtility.getDoubleInput(100_000);			
//		}
		
		acctDao.transferMoney(acct_idFrom, acct_idTo, amt);
			//}
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
		
//		if (acct.isAcctStatus() == false) {
//			InputUtility.displayHeader("New Accounts Need To Be Approved Before Use. Please See Bank Teller If Account Not Approved/Denied in 24 Hours.");
//			} else {
		
		if(acctFound == false) {
			do {
				System.out.println("         ERROR: Please Enter A Correct Account Number!");
				System.out.print("         Enter Account To Add User: "); // id for account
				System.out.println();
				acct_id = InputUtility.getIntChoice(100);
				acctFound = userService.getAcctNumbers().contains(acct_id);
			}while(acctFound == false);
		}
		
		System.out.print("         Enter Username Or Email Of New User: "); // id for account
		String newUserEmail = InputUtility.getStringInput(100);
		
		acctDao.addUserToAcct(acct_id, newUserEmail);
		//}
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
		
		InputUtility.displayHeader(acct_id + " Has Been Deleted!");
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
		acctDao.approveAccount(acct_id);
		
		InputUtility.displayHeader("Your Account Has Been Approved And Is Ready For Immediate Use!");
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
		
		
		InputUtility.displayHeader("Sorry, Your Account Has Been Denied Or Put On A Freeze! Please Contact Management For Further Information!");
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
		
		System.out.print("         Enter Account To View: "); // id for account
		int acct_id = InputUtility.getIntChoice(100);

		System.out.println("");
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
			System.out.println("What Do You Want To Change Your Uername/Email To:");
			String changeue = InputUtility.getStringInput(30);
			userService.setUserEmail(changeue);
			
			System.out.println("Your Username/Email Has Been Changed.");
			userDao.EditUserAccountUsername(edit, changeue);
		} else if (edit == 2) {
			System.out.println("What Do You Want To Change Your Password To:");
			String changepw = InputUtility.getStringInput(30);
			userService.setUserEmail(changepw);
			
			System.out.println("Your Password Has Been Changed.");
			userDao.EditUserAccountPassword(edit, changepw);
		} else {
			InputUtility.displayHeader("Please Enter (1) to Change Your Username Or Email Enter (2) To Change Your Password.");
		}
		
		
	}
	
}
