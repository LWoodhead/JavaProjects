package com.ss.may.jb3;

import java.io.File;

public class DirectoryNames {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File file = new File("C:\\Users\\lukej\\Documents\\School\\Databases");
		if(file.exists()) {
			DirectoryNames d1 = new DirectoryNames();
			d1.printAllFiles(file);
		}else {
			System.out.println("File does not exist");
		}
		
	}
	
	public void printAllFiles(File path) {
		//if the  initial path is a directory move through it else print it
		if(path.isDirectory()) {
			File[] subDirectory = path.listFiles();
			//Recursively call our function
			for(File file : subDirectory) {
				printAllFiles(file);
			}
			//Print the file otherwise
		}else {
			System.out.println(path.getName());
		}
	}
}
