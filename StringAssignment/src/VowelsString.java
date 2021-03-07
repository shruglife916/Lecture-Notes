import java.util.Scanner;
public class VowelsString {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("Input a string. ");
		String str=input.nextLine();
		
		System.out.print("Number of Vowels: " + countVowels(str));
	}
		public static int countVowels(String str) {
		int count = 0;
			for(int i=0; i < str.length(); i++) {
		
			if (str.charAt(i) == 'a' ||str.charAt(i) == 'e' ||str.charAt(i) == 'i' 
					||str.charAt(i) == 'o' ||str.charAt(i) == 'u' || str.charAt(i) == 'A' 
					||str.charAt(i) == 'E' ||str.charAt(i) == 'I' 
					||str.charAt(i) == 'O' ||str.charAt(i) == 'U' ) {
				count++;			
		
		}
		
	}
			return count;
	

		}
}
