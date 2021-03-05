import java.util.Scanner;
public class minmaxarray {
	
		public static void main(String args[]){
	    
	    int [] array = new int [] {10, 20, 30, 40, 50};
		
	    // Calling getMax() method for getting max value
	    int max = getMax(array);
	    System.out.println("Maximum Value is: "+max);
	 
	    // Calling getMin() method for getting min value
	    int min = getMin(array);
	    System.out.println("Minimum Value is: "+min);
	  }
	 
	  // Method for getting the maximum value
	  public static int getMax(int[] inputArray){ 
	    int maxValue = inputArray[0]; 
	    for(int i=1;i < inputArray.length;i++){ 
	      if(inputArray[i] > maxValue){ 
	         maxValue = inputArray[i]; 
	      } 
	    } 
	    return maxValue; 
	  }
	 
	  // Method for getting the minimum value
	  public static int getMin(int[] inputArray){ 
	    int minValue = inputArray[0]; 
	    for(int i=1;i<inputArray.length;i++){ 
	      if(inputArray[i] < minValue){ 
	        minValue = inputArray[i]; 
	      } 
	    } 
	    return minValue; 
	  } 
	}

