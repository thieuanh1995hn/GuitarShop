package com.spring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Collection;

public class CollectionMapper implements RowMapper<Collection> {

	@Override
	public Collection mapRow(ResultSet rs, int rowNum) throws SQLException {
		Collection collection = new Collection();
		collection.setCollection_id(rs.getInt("collection_id"));
		collection.setName(rs.getString("name"));
		return collection;
	}
}
