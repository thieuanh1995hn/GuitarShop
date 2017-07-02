package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Role;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rownumber) throws SQLException {
		Role role = new Role();
		role.setRole_id(rs.getInt("role_id"));
		role.setName(rs.getString("name"));		
		return role;
	}
}
