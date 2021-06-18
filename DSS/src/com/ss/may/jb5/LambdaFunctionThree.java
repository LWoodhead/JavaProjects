package com.ss.may.jb5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaFunctionThree {

	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(1,7,6,9,27,2);
		List<String> stringList = Arrays.asList("xoana","xylophonex","dog","rex","coelacanth","cat","zebra","efreet","zenith","antidisestablishmentarianism","eagle","ace","aid");
		List<String> byLength = stringList.stream().sorted((s1,s2) -> (s1.length() - s2.length())).collect(Collectors.toList());
		List<String> byLengthReverse = stringList.stream().sorted((s1,s2) -> (s2.length() - s1.length())).collect(Collectors.toList());
		List<String> byLetterE = stringList.stream().sorted((s1,s2)-> {
			if(s1.charAt(0) == 'e' && s2.charAt(0) != 'e'){
				return -1;
			}else if(s1.charAt(0) != 'e' && s2.charAt(0) == 'e'){
				return 1;
			}else{
				return 1;
			}
		}).collect(Collectors.toList());	
		List<String> byLetterEHelper = stringList.stream().sorted((s1,s2) -> eComperator(s1,s2)).collect(Collectors.toList());
		String evenOddString = intList.stream().map(n -> {
			if(n % 2 == 0) {
				return "e" + n;
			}else{
				return "o" + n;
			}
		}).collect(Collectors.joining(","));
		List<String> threeA = stringList.stream().filter(str -> {
			if(str.length() == 3 && str.charAt(0) == 'a'){
				return true;
			}else {
				return false;
			}
		}).collect(Collectors.toList());
		System.out.println("Short to Long");
		byLength.forEach(str -> System.out.println(str));
		System.out.println("\nLong To Short");
		byLengthReverse.forEach(str -> System.out.println(str));
		System.out.println("\nE First");
		byLetterE.forEach(str -> System.out.println(str));
		System.out.println("\nE First With Helper");
		byLetterEHelper.forEach(str -> System.out.println(str));
		System.out.println("\nOnly Three With An a");
		threeA.forEach(str -> System.out.println(str));
		System.out.println("\nEven Odd");
		System.out.println(evenOddString);
	}
	private static int eComperator(String s1, String s2) {
		if(s1.charAt(0) == 'e' && s2.charAt(0) != 'e'){
			return -1;
		}else if(s1.charAt(0) != 'e' && s2.charAt(0) == 'e'){
			return 1;
		}else{
			return 1;
		}
	}
}
