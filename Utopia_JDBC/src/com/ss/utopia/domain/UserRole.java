/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class UserRole {
	private Integer roleId;
	private String roleName;
	
	public UserRole(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
