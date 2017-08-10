package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class LoginDAO { //Data Access Object
	
	public static boolean validate(String username, String password) {
		
		Connection conn = null;
		PreparedStatement ps = null;
				
		try {
			conn = DBConnection.openConnection();
			ps = conn.prepareStatement("Select uname,password from Users where uname=? and password=?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.closeConnection(conn);
		}
		
		return false;
	}
	
}
