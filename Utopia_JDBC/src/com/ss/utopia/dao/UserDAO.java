/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.User;

/**
 * @author lukej
 *
 */
public class UserDAO extends TemplateDAO<User>{

	public UserDAO(Connection conn) {
		super(conn);
	}

	public void addUser(User user) throws ClassNotFoundException, SQLException {
		save("insert into user (role_id,given_name,family_name,username,email,password,phone) values (?,?,?,?,?,?,?)",
				new Object[] {user.getRoleId(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail(),user.getPassword(),user.getPhone()});
	}
	
	public void removeUser(User user) throws ClassNotFoundException, SQLException {
		save("delete from user where id = ?",new Object[] {user.getUserId()});
	}
	
	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		save("update user set role_id = ?,given_name = ?,family_name = ?,username = ?,email = ?,password = ?,phone = ? where id = ?",
				new Object[] {user.getRoleId(),user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail(),user.getPassword(),user.getPhone(),user.getUserId()});
	}
	
	public List<User> readAllUsers() throws ClassNotFoundException, SQLException {
		return read("select * from user",null);
	}
	
	@Override
	public List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<User> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new User(rs.getInt("id"), rs.getInt("role_id"), rs.getString("given_name"), rs.getString("family_name"), rs.getString("username"), rs.getString("email"), 
					rs.getString("password"), rs.getString("phone")));
		}
		return all;
	}
}
