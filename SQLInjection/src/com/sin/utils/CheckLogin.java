package com.sin.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckLogin {
//	static String sql = "select * from appversion ";
	public static boolean checkAccountAndPass(String account,String password) {
		Database db = new Database();
		String sql = "select * from user where account = " + account +" and password =" + password;
		System.out.println("sql:"+sql);
		ResultSet rs = db.executeQuery(sql);
		try {
			while(rs.next()){
				String acc = rs.getString("account"); 
				String name = rs.getString("name");
				System.out.println("account:"+acc+" name:"+name);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
