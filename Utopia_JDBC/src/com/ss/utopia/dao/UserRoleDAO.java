/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.UserRole;

/**
 * @author lukej
 *
 */
public class UserRoleDAO extends TemplateDAO<UserRole>{

	public UserRoleDAO(Connection conn) {
		super(conn);
	}
	
	public void addUserRole(UserRole ur) throws ClassNotFoundException, SQLException {
		save("insert into user_role (name) values (?)",new Object[] {ur.getRoleName()});
	}
	
	public void removeUserRole(UserRole ur) throws ClassNotFoundException, SQLException {
		save("delete from user_role where id = ?",new Object[] {ur.getRoleId()});
	}
	
	public void updateUserRole(UserRole ur) throws ClassNotFoundException, SQLException {
		save("update userRole set name = ? where id = ?",new Object[] {ur.getRoleName(), ur.getRoleId()});
	}
	
	public List<UserRole> readAllUserRoles() throws ClassNotFoundException, SQLException {
		return read("select * from user_role",null);
	}
	
	@Override
	public List<UserRole> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<UserRole> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new UserRole(rs.getInt("id"), rs.getString("name")));
		}
		return all;
	}
}
