
public class EmployeeMain {

	public static void main(String[] args) {
		Employee e1 = new Employee(666,"Daniel");
			System.out.println(e1);
						
		
		Employee e2 = new Employee(667,"Kevin");
			System.out.println(e2);
			
			e1.setAge(30);
			e1.setEmail("Dlo@yahoo.com");
			
			e2.setAge(40);
			e2.setEmail("K9@gmail.com");	
		
		Employee e3 = new Employee(668, "Bryan" ,50,"beehive@yahoo.com"); 
		System.out.println(e3);
		
		System.out.println("e1: "+ e1);
		System.out.println("e2: "+ e2);

		Address a2 = new Address(595,"Market Street ","San Francisco ", "California ", 94105);
		Employee e4 = new Employee(669, "Jack", 20, "jbox@gmail.com", a2);	
		System.out.println(e4);
	}
}