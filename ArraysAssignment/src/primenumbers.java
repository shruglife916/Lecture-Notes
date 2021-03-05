import java.util.Scanner;
public class primenumbers {

	public static void main(String[] args) {
		int i, size, j, prime;
        int array[] = new int[100];
        Scanner input = new Scanner(System.in);

        System.out.print("Enter size of an array:");
        size = input.nextInt();

        System.out.print("Enter array elements:");
        for (i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }

        System.out.print("All prime numbers are:");

        for (i = 0; i < size; i++) {
            j = 2;
            prime = 1;
            while (j < array[i]) {
                if (array[i] % j == 0) {
                    prime = 0;
                    break;
                }
                j++;
            }
            if (prime == 1) {
                System.out.print(" " + array[i]);
            }
        }

    }
}