package com.criff.main;

import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class LoginMenu implements Menu {
	public int inputValue;
	private static UserService userService = new UserService();

	@Override
	public void showMenu() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *        Welcome to Criff Banking!        *");
		System.out.println("        *                                         *");
		System.out.println("        *     1. OPEN NEW USER ACCOUNT            *");
		System.out.println("        *     2. LOGIN TO EXISTING ACCOUNT        *");
		System.out.println("        *     3. EXIT CRIFF BANKING SYSTEM        *");
		System.out.println("        *                                         *");
		System.out.println("        *******************************************");
	}
	
	@Override
	public Menu process() {
		switch(inputValue) {
			case 1: 
				log.debug(InputUtility.displayHeader("OPEN NEW USER ACCOUNT..."));
				userService.createUser();
				return new UserMainMenu();
			case 2:
				log.debug(InputUtility.displayHeader("LOGIN TO AN EXISTING ACCOUNT..."));
				userService.userLogin();
				return new UserMainMenu();
			case 3: 
				log.debug(InputUtility.displayHeader("EXITING CRIFF BANKING SYSTEM..."));
				return null;
			default:
            	log.debug(InputUtility.displayHeader("Invalid Choice. Please Choose Again."));
                break;
		}
		return null;
	}

	@Override
	public void getUserInput() {
		this.inputValue = InputUtility.getIntChoice(3);
	}
	
}