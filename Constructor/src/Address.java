
public class Address {

private int aid;
private String streetname;
private String city;
private String state;
private int zipcode;


public Address (int aid, String streetname, String city, String state, int zipcode) {
	this.aid=aid;
	this.streetname=streetname;
	this.city=city;
	this.state=state;
	this.zipcode=zipcode;
}
public String toString () {
	return "Address aid= " + aid + ", Streetname= "+ streetname + ", City= " + city + ", State= " + state + ", Zipcode= " + zipcode;
}
public void getid (int aid) {
	this.aid=aid;
	
}
public void getStreetname(String streetname) {
	this.streetname=streetname;
}
public void getCity(String city) {
	this.city=city;
}
public void getState(String state) {
	this.state=state;
}
public void getZipcode(int zipcode) {
	this.zipcode=zipcode;

}
}

