
public class Palindrome {
//Check if n is Palindrome
	
		
	private static boolean isPalindrome(int num) {
		 int reverse = 0;
			for (int i = num; i > 0; i /= 10)
			   reverse = reverse * 10 + i % 10;
			return num == reverse;
	}
	
	public static void printPal(int min, int max) {
		  for (int i = min; i <= max; i++)
			 if (isPalindrome(i))
			   System.out.print(i + " ");
	}	  

	public static void main(String[] args) {
		  int min = 100;
		  int max = 999;

		  printPal(min, max);
	}
	
	
	}
