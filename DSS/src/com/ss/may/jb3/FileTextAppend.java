/**
 * 
 */
package com.ss.may.jb3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author lukej
 *
 */
public class FileTextAppend {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileTextAppend f = new FileTextAppend();
		f.appendText("../DSS/appendFile");
	}
	public void appendText(String path) throws IOException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Please enter text to be appended");
		String textToAppend = myObj.nextLine();
		System.out.println(textToAppend);
		try (FileWriter file = new FileWriter(path, StandardCharsets.UTF_8, true)){
				file.append(textToAppend);
				file.close();
				// TODO Auto-generated catch block
		
		}
	}

}
