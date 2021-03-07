import java.util.Arrays;
import java.util.Scanner;
public class MaxMinletters {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input a string: ");
		String s =input.nextLine();
		String [] str = s.split(" ");
		
	int shortest = Integer.MAX_VALUE;
	int longest = Integer.MIN_VALUE;
	
	for (String w:str) {
		if (w.length() > longest) {
			longest = w.length();
		}
		if (w.length() < shortest) {
			shortest = w.length();
		}
	}
	String [] shortestw = new String[str.length];
	String [] longestw = new String[str.length];
	int count=0;
	
	for (String w : str) {
		if(w.length() == shortest) {
			shortestw[count] = w;
			count++;
		} else if (w.length() == longest) {
			longestw[count] =w;
			count++;	
		}
	}
	System.out.println(Arrays.toString(longestw));
	System.out.println(Arrays.toString(shortestw));
	
	  System.out.println("Shortest words: ");
	 
		for (String w: shortestw) {
			if (w!= "" && w!= null) {
			System.out.println(w);
		}
		}
	
	  System.out.println("Longest words: ");	
	 
		for (String w:longestw) {
			if(w!= ""&& w!= null) {
				System.out.println(w);
		}
	}
	

		}
	}

