package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import objects.User;

public class SignUpDAO {

	public static boolean SignUp(User user) {
		
		//open connection
		//prepared statement
		//if user exists
			//return false
		//if user doesnt exists
			//return true (signed up)
		
		
		Connection conn = null;
		PreparedStatement statement = null; 
		
		try {
			conn = DBConnection.openConnection();
			statement = conn.prepareStatement("Insert into users (uname, password, email, age, sex) VALUES (?, ?, ?, ?, ?)");
			
			System.out.println(user.getName()+"-"+user.getPassword()+"-"+user.getEmail()+"-"+user.getAge()+"-"+user.getSex());
			
			statement.setString(1, user.getName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setInt(4, user.getAge());
			statement.setString(5, String.valueOf(user.getSex()));
			
			int rs = statement.executeUpdate();
			
			if(rs == 1) {
				return true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
