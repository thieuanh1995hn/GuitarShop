package com.spring.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.spring.dao.UserRoleDao;
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	@Autowired
	private JdbcTemplate template;
	@Override
	public boolean createUserRole(int user_id, int role_id) {
		int affectedRow = template.update(QUERY_INSERT_USER_ROLE, user_id, role_id);
		return (affectedRow == 1);
	}

}
