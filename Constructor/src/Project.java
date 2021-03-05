
public class Project {

private int projectid;
private String projectname;
private String clientname;


public Project(int projectid, String projectname, String clientname) {
		this.projectid=projectid;
		this.projectname=projectname;
		this.clientname=clientname;
}		
public String toString() {
	return  "Project id= " + projectid + ", Project name= " + projectname + ", Client name= "  + clientname;
}
public void getProjectid(int projectid) {
		this.projectid=projectid;

}	
public void getProjectname(String projectname) {
		this.projectname=projectname;
}
public void getClientname(String clientname) {
		this.clientname=clientname;
}

}
