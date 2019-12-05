package com.criff.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface Menu {
	static final Logger log = LogManager.getLogger(Menu.class);
	/**
	 * Display a Menu of options to the User
	 */
	void showMenu();
	
	/**
	 * Hooks user choice into a functional service process
	 * @return View
	 */
	Menu process();
	
	/**
	 * Method for getting user input regarding the 
	 * menu shown in {@link #showMenu()}
	 */
	void getUserInput();
}


