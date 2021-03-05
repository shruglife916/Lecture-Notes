import java.util.Scanner;
public class palindromearray {

	public static void main (String []args)	 {
	Scanner input = new Scanner(System.in);
		int [] array = new int [5];
		System.out.println("Enter 5 numbers ");
		int i, n, m, s;
		for(i=0; i<array.length; i++) {
			array[i]=input.nextInt();
		}
			System.out.println("Palindrome numbers are: ");
				for (i=0; i<array.length; i++) {
					m = array[i];
					s=0;
				while (m>0) {
					n= m/10;
					s= s*10 + n;
					m= m/10;
				}
			
				if(s == array[i]) {
					System.out.println(array[i]);
				}
	
	
	}

}
}