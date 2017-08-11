import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import objects.User;
import utils.Util;

public class UserManager {
	private Map<Integer, User> users;
	 
	public UserManager() {
		users = new HashMap<Integer, User>();
		addUser(new User(1, "Mike", "1", "mike@hotmail.com",21,'m'));
		addUser(new User(2, "John", "12", "John@hotmail.com",25,'m'));
		addUser(new User(3, "Jane", "123", "Jane@hotmail.com",22,'f'));
	}
 
	public User findUser(Integer id) {
		return users.get(id);
	}
	
	public User findUser(String name, String password){
		Iterator itr = users.entrySet().iterator();
		
		while(itr.hasNext()) {
			Map.Entry pair = (Map.Entry)itr.next();
			User usr = (User) pair.getValue();
	        
			System.out.println(usr.getName());
			System.out.println(usr.getPassword());

			System.out.println(name);
			System.out.println(password);
			
			
			if(usr.getName().equals(name) && usr.getPassword().equals(password)) {
				return usr;
			}
		}
		return null;
		
	}
 
	private void addUser(User user) {
		users.put(user.getId(), user);
	}

	public boolean checkUser(User user) {
		//validate name
		if(!Util.isString(user.getName())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username",
							"Please enter a valid Username"));
			return false;
		}else if(!Util.isPassword(user.getPassword())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Password",
							"Please enter a valid Password"));
			return false;
		}else if(!Util.isEmail(user.getEmail())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Email",
							"Please enter a valid Email"));
			return false;
		}else if(!Util.isAge(user.getAge())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Age",
							"Please enter a valid Age (10-80)"));
			return false;
		}
		
			return true;
	}
	
}
