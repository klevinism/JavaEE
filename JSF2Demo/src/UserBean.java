import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import dao.SignUpDAO;
import objects.User;
import session.SessionUtils;
import utils.EmailSender;

@ManagedBean
@ViewScoped
public class UserBean {
	private String userId;
	private String username;
	private String password;
	private String email;
	private int age;
	private char sex;
	private String msg;
	
	private UserManager service = new UserManager();

	public String getUserId() {
		return userId;
	}
 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public char getSex() {
		return this.sex;
	}
	
	private User user;
 
	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	public String search() {
		Integer id = -1;
		try {
			id = Integer.valueOf(userId);
		} catch (Exception e) {
			return ("unknownUser");
		}
 
		user = service.findUser(id);
 
		if (user == null) {
			return ("unknownUser");
		} else {
			return ("displayUser");
		}
	}
	
	public String login() {
		String name = "";
		String password = "";
		
		try {
			name = String.valueOf(this.username);
			password = String.valueOf(this.password);
		}catch(Exception e){
			e.printStackTrace();
		}

		boolean valid = LoginDAO.validate(name, password);

		if(valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", name);
			return ("findUser");
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			
			return null;
		}
		
		//user = service.findUser(name, password); /*  Checking for username & password in User.java HashMap  */	
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return ("login");
	}

	public String signup() {
		String name = "";
		String password = "";
		String email = "";
		int age = 0;
		char sex = 's';
		
		// get all form data
		name = String.valueOf(this.username);
		password = String.valueOf(this.password);
		email = String.valueOf(this.email);
		age = Integer.valueOf(this.age);
		sex = Character.valueOf(this.sex);
		
		User newUser = new User(name,password,email,age,sex);
		
		//validate all form data
		boolean validUser = service.checkUser(newUser);
		//register to db

		if(validUser) {
			SignUpDAO.SignUp(newUser);
			EmailSender.send("delimetaselti@gmail.com", "Klklkl007", "klevindelimeta@hotmail.com");
			return "signupVerification";
		}else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Something Wrong",
							"Something went wrong. Please check signup fields again !!!"));
			return null;
		}
		
	}
}
