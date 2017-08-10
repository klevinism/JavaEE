import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import objects.User;
import session.SessionUtils;

@ManagedBean
@SessionScoped
public class UserBean {
	private String userId;
	private String username;
	private String password;
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
			
		}

		boolean valid = LoginDAO.validate(name, password);

		if(valid) {
			
			//Set session
			System.out.println("Login");
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", name);
			return ("findUser");
		}else {
			System.out.println("ERROR");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("fads"));
			
			return null;
		}
		
		//user = service.findUser(name, password); /*  Checking for username & password in User.java HashMap  */	
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return ("login");
	}
}
