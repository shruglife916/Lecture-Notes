import java.util.Scanner;

public class MiddleChar {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input a String: ");
		String s = input.nextLine();
		String [] str = s.split("");
	

	for (String w: str) {
		StringBuilder sb = new StringBuilder(w);
	 if(w.length() % 2 == 0) {
		 StringBuilder f =new StringBuilder(("" + w.charAt(0)).toUpperCase());
		 System.out.println("Even Word: "+ f.append(f.substring(1,f.length())));
	 } else {	 
		 int middleIndex = w.length() / 2;
		 String middleChar =("" + w.charAt(middleIndex)).toUpperCase();
		 sb.replace(middleIndex, middleIndex + 1, middleChar);
		 System.out.println("Odd Word: " + middleIndex + " " + sb);
	
	
	}

	}
	}
}

