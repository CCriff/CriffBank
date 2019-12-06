package com.criff.main;

import com.criff.services.AccountService;
import com.criff.services.EmployeeService;
import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class AdminMenu implements Menu {
	public int inputValue;
	private static EmployeeService empService = new EmployeeService();
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
			System.out.println("        *     1. APPROVE AN APPLICATION           *");
			System.out.println("        *     2. DENY AN APPLICATION              *");
			System.out.println("        *     3. DEPOSIT MONEY TO AN ACCOUNT      *");
			System.out.println("        *     4. WITHDRAW MONEY FROM AN ACCOUNT   *");
			System.out.println("        *     5. TRANSFER MONEY TO/FROM AN ACCOUNT*");
			System.out.println("        *     6. VIEW AN ACCOUNT INFORMATION      *");
			System.out.println("        *     7. EDIT AN ACCOUNT INFORMATION      *");
			System.out.println("        *     8. CLOSE ACCOUNT                    *");
			System.out.println("        *     9. LOGOUT                           *");
			System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
			System.out.println("        *******************************************");

	}
		

	@Override
	public Menu process() {
		switch(this.inputValue) {
		case 0: System.exit(0); break;
		case 1: acctService.approve(); break;
		case 2: acctService.deny(); break;
		case 3: acctService.deposit(); break;
		case 4: acctService.withdraw(); break;
		case 5: acctService.transfer(); break;
		case 6: acctService.view(); break;
		case 7: acctService.edit(); break;
		case 8: acctService.deleteAcct(); break;
		case 9: empService.employeelogout(); break;
		default:
        	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
            break;
	}
	return this;
	}

	@Override
	public void getUserInput() {
		inputValue = InputUtility.getIntChoice(10);
		
	}

}
