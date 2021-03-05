
public class CRUDoperations {

	public static void main(String[] args) {
		int [] array = new int [8];
		array[0] = 100;
		array[1] = 200;
		array[2] = 300;
		array[3] = 400;
		array[4] = 500;
		array[5] = 600;
		int n=6;
		System.out.println("Before Insertion");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i]+" ");
		}
		int element=700;
		int position=7;
		for(int i=n;i>position-1;i--) {
			array[i]=array[i-1];   
		}
		array[position-1]=element; //inserting 
		n++;
		
		System.out.println("\n\nAfter Insertion");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i]+" ");
		}
		
		int deleteposition=2;
		for (int i = deleteposition-1; i < n; i++) {
			array[i]=array[i+1];
		}
		n--;
		System.out.println("\n\nAfter Deletion from positon "+deleteposition);
		for (int i = 0; i < n; i++) {
			System.out.print(array[i]+" ");
	
	}
		}

	}


