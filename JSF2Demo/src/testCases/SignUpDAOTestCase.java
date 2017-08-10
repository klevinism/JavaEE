package testCases;

import org.junit.Test;
import objects.User;
import dao.SignUpDAO;

public class SignUpDAOTestCase {

	@Test
	public void test() {
		User user = new User(1, "kl", "de", "kl.de@hotmail.com", 20, 'm');
		/*User user = new User(1, "kl", "de", "kl.de@hotmail.com", 20, 'm');
		User user = new User(1, "kl", "de", "kl.de@hotmail.com", 20, 'm');
		User user = new User(1, "kl", "de", "kl.de@hotmail.com", 20, 'm');
		*/
		
		
		boolean signup = SignUpDAO.SignUp(user);
		
		if(signup)
			System.out.println("TRUE");
		else
			System.out.println("FALSE");
	}

}
