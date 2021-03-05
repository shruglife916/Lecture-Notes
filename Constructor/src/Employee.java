
public class Employee {

private int id;
private String name;
private int age;
private String email;
private Address personAddress;
private Address permanentAddress;

public Employee (int id, String name) {
	this.id=id;
	this.name=name;
}


public Employee(int id, String name, int age, String email) {
	this(id,name);
	this.age=age;
	this.email=email;
}
public Employee(int id, String name, int age, String email, Address personAddress) {
	this(id,name,age,email);
	this.personAddress=personAddress;	
}

public String toString()  {
 return "Employee [id=" + id + ", + name=" + name +"]";

}
public void setAge(int age) {
	this.age=age;
}
public void setEmail(String email) {
	this.email=email;
}



}

