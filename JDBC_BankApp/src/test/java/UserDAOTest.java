import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bankapp.DAO.UserDAO;
import bankapp.DAO.UserDAOImpl;
import bankapp.exceptions.InternalErrorException;
import bankapp.exceptions.UserNotFoundException;
import bankapp.models.Customer;
import bankapp.models.User;

class UserDAOTest {
	private UserDAO ud;
	
	@BeforeAll
	public void  UserDAOImplementation() throws Exception {
		ud = new UserDAOImpl();
	}

	@Test
	void test() throws UserNotFoundException, InternalErrorException, SQLException{
		//fail("Not yet implemented");
		
		User user = null;
		try {
			user = ud.findOne("quang@gmail.com", "12345", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User userTest = new Customer("Quang","Trung",3326,"popsicles", "qtrung");
		assertEquals(userTest.getFirstName(),user.getFirstName());
		assertEquals(userTest.getLastName(),user.getLastName());
		assertEquals(userTest.getSsn(), user.getSsn());
		assertEquals(userTest.getUsername(), user.getUsername());
		assertEquals(userTest.getPassword(), user.getPassword());
	}

}
