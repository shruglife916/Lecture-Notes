
public class SumOddEven {

	public static void main(String[] args) {
		int oddsum = 0;
			for(int i=1; i < 101; i++) {
				if(i % 2 !=0) {
					oddsum = oddsum + i;
				}
			}
		int evensum =0;
			for(int i=1; i < 101; i++) {
				if(i % 2 == 0) {
					evensum = evensum + i;
				}
			}
		System.out.println("Sum of odd numbers: " + oddsum);
		System.out.println("Sum of even numbers: " + evensum);
		
		if (oddsum > evensum) {
			System.out.println("Oddsum is the largest. ");
		} else {
			System.out.println("Evensum is the largest. ");
		}

		}
}