package com.criff.main;

import com.criff.services.AccountService;
import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class UserMainMenu implements Menu{
	public int inputValue;
	private static UserService userService = new UserService();
	private static AccountService acctService = new AccountService();
	
	@Override
	public void showMenu() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("                 Welcome Back " + userService.getUserFirstName() + " " + userService.getUserLastName() + "!            ");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		if(userService.displayAccts()){
			System.out.println("                                                   ");
			System.out.println("        *     1. OPEN A NEW CHECKING ACCOUNT      *");
			System.out.println("        *     2. OPEN A NEW SAVINGS ACCOUNT       *");
			System.out.println("        *     3. DEPOSIT MONEY                    *");
			System.out.println("        *     4. WITHDRAW MONEY                   *");
			System.out.println("        *     5. TRANSFER MONEY                   *");
			System.out.println("        *     6. ADD USER TO ACCOUNT              *");
			System.out.println("        *     7. CLOSE ACCOUNT                    *");
			System.out.println("        *     8. LOGOUT                           *");
			System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
			System.out.println("        *******************************************");
		}else {
			System.out.println("                                                   ");
			System.out.println("        *     1. OPEN A NEW CHECKING ACCOUNT      *");
			System.out.println("        *     2. OPEN A NEW SAVINGS ACCOUNT       *");
			System.out.println("        *     8. LOGOUT                           *");
			System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
			System.out.println("        *******************************************");
		}
	}

	@Override
	public Menu process() {
		switch(this.inputValue) {
			case 0: System.exit(0); break;
			case 1: acctService.openChecking();break;
			case 2: acctService.openSavings(); break;
			case 3: acctService.deposit(); break;
			case 4: acctService.withdraw(); break;
			case 5: acctService.transfer(); break;
			case 6: acctService.addUserToAccount(); break;
			case 7: acctService.deleteAcct(); break;
			case 8: userService.logout(); break;
			default:
            	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
                break;
		}
		return this;
	}

	@Override
	public void getUserInput() {
		inputValue = InputUtility.getIntChoice(9);
	}
}
