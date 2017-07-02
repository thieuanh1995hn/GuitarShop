package com.spring.dao.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.spring.dao.UserDao;
import com.spring.dao.UserRoleDao;
import com.spring.model.User;
import com.spring.model.mapper.RoleMapper;
import com.spring.model.mapper.UserMapper;



@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate template;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRoleDao userroleDao;
	
	
	public JdbcTemplate getTemplate() {
		return this.template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public User getUserByUserName(String username) {
		String query = "SELECT * FROM QLNhacCu.Users WHERE username = "+"'" + username+"'";
		List<User> users = template.query(query, new UserMapper());
		if (!users.isEmpty()) {
			User user = users.get(0);
			String query2 = "SELECT r.role_id, r.name FROM user_role user INNER JOIN roles r ON user.role_id = r.role_id WHERE user.user_id = ?";
			user.setRoles(template.query(query2, new RoleMapper(),user.getUser_id()));
			return user;
		} else {
			return null;
		}
	}
	
	@Override
	public User getUserById(int user_id) {
		String query = "SELECT * FROM QLNhacCu.Users WHERE user_id = "+ user_id;
		List<User> users = template.query(query, new UserMapper());
		if (!users.isEmpty()) {
			User user = users.get(0);
			String query2 = "SELECT r.role_id, r.name FROM user_role user INNER JOIN roles r ON user.role_id = r.role_id WHERE user.user_id = ?";
			user.setRoles(template.query(query2, new RoleMapper(),user.getUser_id()));
			return user;
		} else {
			return null;
		}
	}

	public boolean createNewUser(User user) {
		String QUERY_ADD_NEW_USER = "INSERT INTO USERS(username, password,firstname,lastname,email,phonenumber,postalcode,address) VALUES(?,?,?,?,?,?,?,?)";
		int affectedrows = template.update(QUERY_ADD_NEW_USER, user.getUsername(), user.getPassword(),user.getFirstname(),user.getLastname(),user.getEmail(),user.getPhonenumber(),user.getPostalcode(),user.getAddress());
		return (affectedrows == 1);
	}

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		createNewUser(user);
		User createdUser = getUserByUserName(user.getUsername());
		userroleDao.createUserRole(createdUser.getUser_id(), 1);	
	}

	@Override
	public Integer countAllUsers() {
		String QUERY_SELECT_ALL_USER = "SELECT COUNT(user_id) FROM QLNhacCu.Users";
		Integer count = template.queryForObject(QUERY_SELECT_ALL_USER,Integer.class);
		return count;
	}
	
}
