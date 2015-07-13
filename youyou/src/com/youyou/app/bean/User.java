package com.youyou.app.bean;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String enabled;
	private String accountExpired;
	private String password;
	private String accountLocked;
	private String lastUpdateDate;
	private String id;
	private String username;
	private String nickName;
	private String credentialsExpired;
	private String gender;
	private String createDate;
	private String mobile;
	private String roleId;
	private String roleName;
	private String credits;
	private List<Role> roles;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(String accountExpired) {
		this.accountExpired = accountExpired;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(String credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}


}
