import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class CRUDQueue {

	public static void main(String[] args) {
		    Queue<String> queue = new LinkedList<>(); 
		    queue.add("North");
		    queue.add("East");
		    queue.add("South");
		    queue.add("West");
		    
		    System.out.println("Linked List Queue is:" + queue);
		    System.out.println("Linked List Queue Peek is :" + queue.peek());
		    
		    queue.poll();
		    System.out.println("Linked List Queue after remove is:" + queue);
		    
		    
		    Queue<Integer> queuenew = new PriorityQueue<>();
		    
		    
		    queuenew.add(7);
		    queuenew.add(8);
		    queuenew.add(6);
		    queuenew.add(5);
		    queuenew.add(9);
		    
		    System.out.println("Priority Queue is:" + queuenew);
		    System.out.println("Priority Queue Peek is :" + queuenew.peek());
		    
		    int RemoveFirst=queuenew.remove();
		    System.out.println("Priority Queue Element Removed is:" + RemoveFirst);
		    int RemoveSecond=queuenew.remove();
		    System.out.println("Priority Queue Element Removed is:" + RemoveSecond);
		    System.out.println("Priority Queue after remove is:" + queuenew);
		  }



	}
