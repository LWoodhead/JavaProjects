/**
 * 
 */
package com.ss.may.jb3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class FileCharCounter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in);
		System.out.println("Please enter a character to be counted");
		String targetChar = myObj.nextLine();
		FileCharCounter fcc = new FileCharCounter();
		fcc.countChar("../DSS/charCountFile", targetChar.charAt(0));
	}
	
	/**
	 * @param args
	 * String path is the path to the file we want to count
	 * char Target is the character we need to count
	 */
	public void countChar(String path, char target) {
		try {
			int count = 0;
			File file = new File(path);
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				char[] charLine = line.toCharArray();
				for(char ch: charLine) {
					if(ch == target) {
						count++;
					}
				}
			}
			System.out.println("Target Char: " + target + " Count: " + count);
		}catch(FileNotFoundException e) {
			System.out.println("Error Opening File");
			e.printStackTrace();
		}
	}
}
