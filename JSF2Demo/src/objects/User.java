package objects;
public class User {
	private int id;
	private String name;
	private String password;
	private String email;
	private int age;
	private char sex;
 
	public User(int id, String username, String password, String email, int age, char sex) {
		this.id = id;
		this.name = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
 
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getAge() {
		return age;
	}
	
	public char getSex() {
		return sex;
	}
	
}
