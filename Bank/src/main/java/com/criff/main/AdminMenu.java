package com.criff.main;

import com.criff.services.AccountService;
import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class AdminMenu implements Menu {
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
		System.out.println("             Welcome Back The One Above All!       ");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
				
			System.out.println("                                                   ");
			System.out.println("        *     1. OPEN A NEW CHECKING ACCOUNT      *");
			System.out.println("        *     2. OPEN A NEW SAVINGS ACCOUNT       *");
			System.out.println("        *     3. APPROVE AN APPLICATION           *");
			System.out.println("        *     4. DENY AN APPLICATION              *");
			System.out.println("        *     5. DEPOSIT MONEY                    *");
			System.out.println("        *     6. WITHDRAW MONEY                   *");
			System.out.println("        *     7. TRANSFER MONEY                   *");
			System.out.println("        *     8. ADD USER TO ACCOUNT              *");
			System.out.println("        *     9. VIEW AN ACCOUNT INFORMATION      *");
			System.out.println("        *     10. EDIT AN ACCOUNT INFORMATION     *");
			System.out.println("        *     11. VIEW ALL ACCOUNTS               *");
			System.out.println("        *     12. CLOSE ACCOUNT                   *");
			System.out.println("        *     13. LOGOUT                          *");
			System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
			System.out.println("        *******************************************");

	}
		

	@Override
	public Menu process() {
		switch(this.inputValue) {
		case 0: return null;
		case 1: acctService.openChecking();break;
		case 2: acctService.openSavings(); break;
		case 3: acctService.approve(); break;
		case 4: acctService.deny(); break;
		case 5: acctService.deposit(); break;
		case 6: acctService.withdraw(); break;
		case 7: acctService.transfer(); break;
		case 8: acctService.addUserToAccount();break;
		case 9: acctService.view(); break;
		case 10: acctService.edit(); break;
		case 11: acctService.viewAll(); break;
		case 12: acctService.deleteAcct(); break;
		case 13: userService.logout(); break;
		default:
        	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
            break;
	}
	return this;
	}

	@Override
	public void getUserInput() {
		inputValue = InputUtility.getIntChoice(14);
		
	}

}
