import java.util.Scanner;
public class lastCharsUpperCase {
	
	 public static void main(String args[]) {
	    Scanner input = new Scanner(System.in);
	    System.out.println("Please input a string");
	    String s = input.nextLine();
	    String str[] = s.split(" ");
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0; i < str.length; i++) {
	    	sb.append(str[i].substring(0,str[i].length()-1))
	    		.append(Character.toUpperCase(str[i].charAt(str[i].length() -1))).append(" ");
	    }
	 System.out.println(sb.toString().trim());
	 }
	 
	   
	 }


