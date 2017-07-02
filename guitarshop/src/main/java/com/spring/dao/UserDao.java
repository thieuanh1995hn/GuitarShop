package com.spring.dao;

import com.spring.model.User;

public interface UserDao {
	public void save(User user) ;
	public boolean createNewUser(User user);
	public User getUserByUserName(String username);
	public User getUserById(int user_id);
	public Integer countAllUsers();
}
