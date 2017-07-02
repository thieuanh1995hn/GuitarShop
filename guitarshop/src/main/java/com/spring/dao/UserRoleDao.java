package com.spring.dao;


public interface UserRoleDao {
	String QUERY_INSERT_USER_ROLE = "INSERT INTO user_role(user_id, role_id) VALUES(?, ?)";

	public boolean createUserRole(int user_id, int role_id);
}
