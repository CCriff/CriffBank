package com.criff.driver;

import com.criff.main.AdminMenu;
import com.criff.main.EmpMenu;
import com.criff.main.LoginMenu;
import com.criff.main.UserMainMenu;
import com.criff.utility.InputUtility;
import com.criff.main.Menu;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	
	private static final Logger log = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		runStartMenu();
	}
	
	public static void runStartMenu() {
		log.debug(InputUtility.displayHeader("^^^^^^           ++ Please Dont Be Alarmed... Dont Adjust Your Eyes... It Is Just My Log! Please Stand By! ++              ^^^^^^"));
		
		System.out.println("\n         ____              _    _               ____            _         \n"
                +"        " +  "| __ )  __ _ _ __ | | _(_)_ __   __ _  / ___| _   _ ___| |_ ___ _ __ ___   \n"
                +"        " +  "|  _ \\ / _` | '_ \\| |/ / | '_ \\ / _` | \\___ \\| | | / __| __/ _ \\ '_ ` _  |\n"
                +"        " +  "| |_) | (_| | | | |   <| | | | | (_| |  ___) | |_| \\__ \\ ||  __/ | | | | | \n"
                +"        " +  "|____/ \\__,_|_| |_|_|\\_\\_|_| |_|\\__, | |____/ \\__, |___/\\__\\___|_| |_| |_| \n"
                +"        " +  "                                |___/         |___/             \n");

        InputUtility.displayHeader("WELCOME TO THE CRIFF BANKING SYSTEM! PLEASE PRESS ENTER TO LOGIN OR CREATE A NEW ACCOUNT");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        InputUtility.displayHeader("STARTING SYSTEM....");
		Menu menu = new LoginMenu();
		do {
			menu.showMenu();
			menu.getUserInput();
			menu = menu.process();
		} while(menu!=null);
		
		System.out.println();
		InputUtility.displayHeader("         Thank You For Banking With CRIFF BANKING! Have A Nice Day!!");
	}
	
	public static void toUserMainMenu() {
		Menu menu = new UserMainMenu();
		do {
			menu.showMenu();
			menu.getUserInput();
			menu = menu.process();
		} while(menu!=null);
		
		System.out.println();
		InputUtility.displayHeader("         Thank You For Banking With CRIFF BANKING! Have A Nice Day!!");
	}
	
	public static void toAdminMainMenu() {
		Menu menu = new AdminMenu();
		do {
			menu.showMenu();
			menu.getUserInput();
			menu = menu.process();
		} while(menu!=null);
		
		System.out.println();
		InputUtility.displayHeader("         Thank You For Banking With CRIFF BANKING! Have A Nice Day!!");
	}
	
	public static void toEmpMainMenu() {
		Menu menu = new EmpMenu();
		do {
			menu.showMenu();
			menu.getUserInput();
			menu = menu.process();
		} while(menu!=null);
		
		System.out.println();
		InputUtility.displayHeader("         Thank You For Banking With CRIFF BANKING! Have A Nice Day!!");
	}
}