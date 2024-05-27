package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

public enum PostDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PostDao.class);
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();
	
	public List<Post> select() {
		log.debug("select()");
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
