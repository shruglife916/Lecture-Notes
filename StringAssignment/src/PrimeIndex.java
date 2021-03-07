import java.util.Scanner;
public class PrimeIndex {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Input a string. ");
		String s = input.nextLine();
		String [] s1 = s.split("");
		for (int i=0; i < s.length(); i++) {
			if(isPrime(i)) {
				System.out.println(s1[i].toUpperCase());
			}
		}
	}     
	public static boolean isPrime(int n) { 
	        
	        if (n < 2) 
	        	return false; 
	     
	        for (int i = 2; i < n; i++) {
	            if (n % i == 0) 
	                return false; 
	        } 
	        return true; 
	     
	   
	 
	}

}
