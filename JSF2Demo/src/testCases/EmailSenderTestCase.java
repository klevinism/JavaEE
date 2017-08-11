package testCases;

import org.junit.Test;
import utils.EmailSender;

public class EmailSenderTestCase {

	@Test
	public void test() {
		
		EmailSender.send("delimetaselti@gmail.com", "Klklkl007", "klevindelimeta@hotmail.com");
		
	}

}
