package com.criff.services;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import com.criff.main.AdminMenu;
import com.criff.main.EmpMenu;
import com.criff.models.User;
import com.criff.repository.UserDaoImpl;
import com.criff.utility.InputUtility;

public class UserService {
	private static EmployeeService empService = new EmployeeService();
	private static UserService userService = new UserService();
	private static AdminMenu adminMenu = new AdminMenu();
	private static EmpMenu empMenu = new EmpMenu();
	public static User user = new User();
	private static UserDaoImpl userDao = new UserDaoImpl();
	public static boolean newUser = false;
	public boolean loggedIn = false;
    public static User currentUser = null;
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserService.user = user;
	}

	public int getUserId() {
		return user.getId();
	}
	
	public void setUserId(int id) {
		id = user.getId();
	}
	
	public String getUserFirstName() {
		return user.getFirstName();
	}
	
	public void setUserFirstName(String firstName) {
		firstName = user.getFirstName();
	}
	
	public String getUserLastName() {
		return user.getLastName();
	}
	
	public void setUserLastName(String lastName) {
		lastName = user.getLastName();
	}
	
	public String getUserEmail() {
		return user.getEmail();
	}
	
	public void setUserEmail(String email) {
		email = user.getEmail();
	}
	
	public String getUserPass_hash(String pass_hash) {
		return user.getPass_hash();
	}
	
	public void setPass_hash(String pass_hash) {
		pass_hash = user.getPass_hash();
	}
	
	public ArrayList<Integer> getAcctNumbers() {
		return user.getAcctNumbers();
	}
	
	public void setAcctNumbers(ArrayList<Integer> acctNumbers) {
		acctNumbers = user.getAcctNumbers();
	}
	
	public void createUser() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *       New User Account Sign Up!         *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		System.out.print("         Please Enter Your First Name: ");
		user.setFirstName(InputUtility.getStringInput(30));
		
		System.out.print("         Please Enter Your Last Name: ");
		user.setLastName(InputUtility.getStringInput(30));
		
		System.out.print("         Please Enter An Username Or Email: ");
		user.setEmail(InputUtility.getStringInput(30));
		
		if (user.getEmail().contains(getUserEmail())) {
			
			InputUtility.displayHeader("Someone With This Uername Or Email Already Exists. Please Enter Unique Username Or Email: ");
			
			user.setEmail(InputUtility.getStringInput(30));
		}

		System.out.print("         Please Enter Your Password: ");
		user.setPass_hash(BCrypt.hashpw(InputUtility.getStringInput(30), BCrypt.gensalt()));
		
		user = userDao.insertUser(user);
		newUser = true;
	}
	
	public void userLogin() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *              User Login                 *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
		InputUtility.displayHeader("         Please Enter Your Username Or Email: ");
		String email = InputUtility.getStringInput(30);
		
		InputUtility.displayHeader("         Please Enter Your Password: ");
		String pw = InputUtility.getStringInput(30);
		
		user = userDao.checkUser(user, email, pw);
		System.out.println();
		InputUtility.displayHeader("         Logging Into Your Account . . .");
		
		
	}
	
	public void logout() {
		System.out.println("                                                   ");
		System.out.println("                                                   ");
		System.out.println("    	*******************************************");
		System.out.println("        *        CRIFF  BANKING  SYSTEM           *");
		System.out.println("        *                                         *");
		System.out.println("        *              User Logout                *");
		System.out.println("    	*******************************************");
		System.out.println("                                                   ");
		
        boolean logIn = false;
        currentUser = null;
        InputUtility.displayHeader(userService.getUserFirstName() + " " + userService.getUserLastName() + " Has Logged Out.");
        InputUtility.displayHeader("Enter 1 To Login, 2 To Login As An Employee, Or Enter 0 To Quit The CRIFF BANKING SYSTEM.");
        String choice = InputUtility.getStringInput(1);
        while (logIn == false) {
            if (choice.equals("1")) {
                userLogin();
                logIn = true;
            } else if (choice.equals("0")) {
                logIn = false;
                System.exit(0);
            } else if (choice.equals("2")) {
            	empService.employeeLogin();
                logIn = true;
            } else
                {
            	InputUtility.displayHeader("Please Enter 1 To Login, 2 To Login As An Employee, Or 0 to Exit!");
                choice = InputUtility.getStringInput(2);
                logIn = false;
            }

        }

    }
	
	public boolean displayAccts() {  
		boolean hasAccts = false;
		hasAccts = userDao.getUserAccounts(user, hasAccts); 
		return hasAccts;
	}
}



