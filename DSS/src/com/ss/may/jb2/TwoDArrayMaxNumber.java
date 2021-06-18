package com.ss.may.jb2;

public class TwoDArrayMaxNumber {
	public static void main(String args[]) 
	{
		int[][] testArray = {{12,-7},{14,27},{-80,2}};
		int max = testArray[0][0];
		for(int i=0;i < testArray.length; i++) {
			for(int j=0; j < testArray[i].length; j++) {
				if(max < testArray[i][j]) {
					max = testArray[i][j];
				}
			}
		}
		System.out.println("Max: "+ max);
	}
}
