package com.ss.may.jb5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 */

/**
 * @author lukej
 *
 */
public class LambdaFunctionsTwo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numberList = new ArrayList<>();
		List<Integer> firstDigitList = new ArrayList<>();
		List<Integer> doubleList = new ArrayList<>();
		List<String> stringList = Arrays.asList("xoana","xylophonex","dog","rex","coelacanth","cxxaxxt");
		List<String> noLetterX = new ArrayList<>();
		List<Integer> possibleSum = Arrays.asList(1,2,2,3,3,2,3,1,1,4,7,8,8,6,7,9,9,12);//1 4 6 2 3 2 4 7 16 6 7 18 12
		List<Integer> noRepeatPossibleSum = new ArrayList<>();
		Random rand = new Random();
		for(int i=0;i<10;i++) {
			int numToAdd = rand.nextInt(101);
			numberList.add(numToAdd);
		}
		System.out.print("Original List: ");
		for(int num : numberList) {
			System.out.print(num + " ");
		}
		System.out.println();
	    firstDigitList.addAll(numberList);
		firstDigitList.replaceAll(n -> n % 10);
		System.out.print("First Digit: ");
		for(int num : firstDigitList) {
			System.out.print(num + " ");
		}
		System.out.println();
		doubleList.addAll(numberList);
		doubleList.replaceAll(n -> n * 2);
		System.out.print("Doubled: ");
		for(int num : doubleList) {
			System.out.print(num + " ");
		}
		System.out.println();
		System.out.print("X Replaced: ");
		noLetterX.addAll(stringList);
		noLetterX.replaceAll(str -> str.replaceAll("x",""));
		for(String str : noLetterX) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.print("Uncondensed: ");
		for(int num : possibleSum) {
			System.out.print(num + " ");
		}
		System.out.println();
		for(int i=0;i<possibleSum.size()-1;i++){
//			System.out.print(i + " ");
			if(possibleSum.get(i) == possibleSum.get(i+1)){
				if(i < possibleSum.size()-3){
					for(int j=i+2;j < possibleSum.size();j++){
						int dupliCount = 2; 
						if(possibleSum.get(i) != possibleSum.get(j)){
							noRepeatPossibleSum.add(possibleSum.get(i)*dupliCount);
							i = j-1;
							break;
						}else {
							dupliCount++;
						}
					}
				}else{
					//no more space just add
					noRepeatPossibleSum.add(possibleSum.get(i)*2);
					break;
				}
			}else {
				//no repeat add 
				noRepeatPossibleSum.add(possibleSum.get(i));
			}
		}
		//Add last element
		noRepeatPossibleSum.add((possibleSum.get(possibleSum.size()-1)));
//		System.out.println("size is: " + possibleSum.size());
		System.out.print("Condensed: ");
		for(int num : noRepeatPossibleSum) {
			System.out.print(num + " ");
		}
//		System.out.println("Condensed: (1 4 6 2 3 2 4 7 16 6 7 18 12) (1,2,2,3,3,2,3,1,1,4,7,8,8,6,7,9,9,12)");
		System.out.println();
		boolean result = recSubSum(noRepeatPossibleSum,noRepeatPossibleSum.size(),107);
		System.out.println("Result:" + result);
	}	

	static Boolean recSubSum(List<Integer> list, int n, int sum) {
		if(sum == 0) {
			return true;
		}
		if(n == 0) {
			return false;
		}
		if(list.get(n-1) != null && list.get(n-1) > sum){
			return recSubSum(list,n-1,sum);
		}
		return recSubSum(list,n-1,sum) || recSubSum(list,n-1,sum - list.get(n-1));
	}
}
