package com.criff.main;

import com.criff.services.EmployeeService;
import com.criff.services.UserService;
import com.criff.utility.InputUtility;

public class LoginMenu implements Menu {
	public int inputValue;
	private static UserService userService = new UserService();
	private static EmployeeService empService = new EmployeeService();

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
		System.out.println("        *     3. LOGIN TO EMPLOYEE ACCOUNT        *");
		System.out.println("        *     0. EXIT CRIFF BANKING SYSTEM        *");
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
				log.debug(InputUtility.displayHeader("LOGIN TO EMPLOYEE PORTAL..."));
				empService.employeeLogin();
				return new AdminMenu();
			case 0: 
				log.debug(InputUtility.displayHeader("EXITING CRIFF BANKING SYSTEM..."));
				System.exit(0); break;
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