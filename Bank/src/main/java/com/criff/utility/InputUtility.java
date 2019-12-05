package com.criff.utility;

import java.util.Scanner;

import org.apache.logging.log4j.message.Message;

public class InputUtility {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int getIntChoice(int max) {
		int inputValue;
		
		// Confirm user input is int type
		do {
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("         ERROR: Please enter a whole number.");
			}
			
			// Retrieve user input
			inputValue = scanner.nextInt();
			scanner.nextLine();
			
			// Confirm user input is within the range of 0 to max
			if(inputValue <= 0 || inputValue > max) {
				System.out.println("         ERROR: Please enter a number between 1 and " + max);
			}
			
		} while(inputValue < 0 || inputValue > max);

		// Return user input
		return inputValue;
	}
	
	public static double getDoubleInput(int max) {
		double inputValue;
		
		// Confirm user input is double type
		do {
			while(!scanner.hasNextDouble()) {
				scanner.nextLine();
				System.out.println("         ERROR: Please enter a number.");
			}
			
			// Retrieve user input
			inputValue = scanner.nextDouble();
			scanner.nextLine();
			
			// Confirm user input is within the range of 0 to max
			if(inputValue <= 0 || inputValue > max) {
				System.out.println("         ERROR: Please enter a number between 1 and " + max);
			}
			
		} while(inputValue < 0 || inputValue > max);

		// Return user input
		return inputValue;
	}
	
	public static String getStringInput(int max) {
		String input;
		
		while(true) {
			input = scanner.nextLine();
			
			input = input.trim();
			if(input.length() == 0){
				System.out.println("         String has no content");
				continue;
			}
			
			if(input.length() > max){
				System.out.println("         Enter string less than" + max);
				continue;
			}
			
			return input;
		}
	}
	
	public static Message displayHeader(String message){
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("        " + "+");
        for(int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("        "+"|   " + message + "   |");
        System.out.println(sb.toString());
		return null;
    }

}
