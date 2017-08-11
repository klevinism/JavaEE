package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static final Pattern EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static final Pattern PASSWORD_REGEX = 
		    Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean isString(String text) {
		if(text instanceof String)
			return true;
		return false;
	}
	
	public static boolean isPassword(String text) {
		Matcher matcher = PASSWORD_REGEX.matcher(text);
		return matcher.find();
	}
	
	public static boolean isEmail(String email) {
		Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}
	
	public static boolean isAge(int age) {
		if(age>=10 && age<=80)
			return true;
		return false;
	}
	
}
