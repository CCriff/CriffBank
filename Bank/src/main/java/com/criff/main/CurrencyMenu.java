package com.criff.main;

import com.criff.utility.InputUtility;

public class CurrencyMenu {
	public static int inputValue;
	
	public static String process() {
		showMenu();
		getUserInput();
		switch(inputValue) {
			case 1: return "USD";
			case 2: return "EUR";
			case 3: return "JPY";
			case 4: return "GBP";
			case 5: return "AUD";
			case 6: return "CHF";
			case 7: return "CAD";
			case 8: backToMain(); break;
		}
		return "USD"; // Default the account to USD
	}
	
	public static void showMenu() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *            CURRENCY EXCHANGE            *");
		System.out.println("    	*******************************************");
		System.out.println("        *                                         *");
		System.out.println("        *     PLEASE SELECT YOUR CURRENCY:        *");
		System.out.println("        *                                         *");
		System.out.println("        *     1. US DOLLAR (USD)                  *");
		System.out.println("        *     2. EURO (EUR)                       *");
		System.out.println("        *     3. JAPANESE (JPY)                   *");
		System.out.println("        *     4. BRITISH POUND (GBP)              *");
		System.out.println("        *     5. AUSTRALIAN DOLLAR (AUD)          *");
		System.out.println("        *     6. SWISS FRANC (CHF)                *");
		System.out.println("        *     7. CANADIAN DOLLAR (CAD)            *");
		System.out.println("        *     8. BACK                             *");
		System.out.println("        *******************************************");
	}
	
	public static Menu backToMain() {
		return new UserMainMenu();
	}

	public static void getUserInput() {
		inputValue = InputUtility.getIntChoice(8);
	}
}
