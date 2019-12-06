package com.criff.main;

import com.criff.services.AccountService;
import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class EmpMenu implements Menu {
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
		System.out.println("                  Welcome Back Tribunal!          ");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");

			System.out.println("                                                   ");
			System.out.println("        *     1. APPROVE AN APPLICATION           *");
			System.out.println("        *     2. DENY AN APPLICATION              *");
			System.out.println("        *     3. LOGOUT                           *");
			System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
			System.out.println("        *******************************************");
		
	}
		

	@Override
	public Menu process() {
		switch(this.inputValue) {
		case 0: return null;
		case 1: acctService.approve(); break;
		case 2: acctService.deny(); break;
		case 3: userService.logout(); break;
		default:
        	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
            break;
	}
	return this;
	}

	@Override
	public void getUserInput() {
		inputValue = InputUtility.getIntChoice(6);
		
	}

}
