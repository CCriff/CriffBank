package com.criff.main;

import com.criff.services.AccountService;
import com.criff.services.EmployeeService;
import com.criff.utility.InputUtility;

public class AdminMenu implements Menu {
	public int inputValue;
	private static EmployeeService empService = new EmployeeService();
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
			System.out.println("        *     1. APPROVE AN APPLICATION           *");
			System.out.println("        *     2. DENY AN APPLICATION              *");
			System.out.println("        *     3. DEPOSIT MONEY TO AN ACCOUNT      *");
			System.out.println("        *     4. WITHDRAW MONEY FROM AN ACCOUNT   *");
			System.out.println("        *     5. TRANSFER MONEY TO/FROM AN ACCOUNT*");
			System.out.println("        *     6. VIEW AN ACCOUNT                  *");
			System.out.println("        *     7. VIEW All ACCOUNTS                *");
			System.out.println("        *     8. VIEW AN USER                     *");
			System.out.println("        *     9. VIEW All USERS                   *");
			System.out.println("        *     10. EDIT AN ACCOUNT INFORMATION     *");
			System.out.println("        *     11. CLOSE AN ACCOUNT                *");
			System.out.println("        *     12. LOGOUT                          *");
			System.out.println("        *     13. EXIT CRIFF BANKING SYSTEM       *");
			System.out.println("        *******************************************");


	}
		

	@Override
	public Menu process() {
		switch(this.inputValue) {
		case 13: System.exit(0); break;
		case 1: acctService.approve(); break;
		case 2: acctService.deny(); break;
		case 3: acctService.deposit(); break;
		case 4: acctService.withdraw(); break;
		case 5: acctService.transfer(); break;
		case 6: acctService.viewSingleAccount(); break;
		case 7: acctService.viewAllAccounts(); break;
		case 8: acctService.viewSingleUser(); break;
		case 9: acctService.viewAllUsers(); break;
		case 10: acctService.edit(); break;
		case 11: acctService.deleteAcct(); break;
		case 12: empService.employeelogout(); break;
		default:
        	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
            break;
	}
	return this;
	}

	@Override
	public void getUserInput() {
		inputValue = InputUtility.getIntChoice(13);
		
	}

}
