import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import objects.User;

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
	
	public User findUser(String name, String password) {
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
}
