package com.sin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	/**
	 * connect the database
	 * 
	 * @return connection
	 */
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/test?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8",
							"root", "root");
		} catch (Exception e) {
			System.out.println("DBUtils.getConnection-exception");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * execute sql to get ResultSet
	 * 
	 * @param sql
	 * @return ResusltSet which contains the data from db
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery-exception");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * update the db
	 * 
	 * @param sql
	 * @return 
	 */
	public int executeUpdate(String sql) {
		int count = 0;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("DBUtils.executeUpdate-exception");
			e.printStackTrace();
		}
		return count;
	}

	/**
	 *  close 
	 */
	public void close() {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}