package com.parsetext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestJDBC {
	public static void main(String args[]) {
		
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		String order_id = "114-2831560-0159447";
		String sql = "SELECT * FROM TrackingInfo WHERE order_id = ?" ;
		try {
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setString(1, order_id);
			ResultSet rs = psmt.executeQuery();
			//System.out.println(rs.getString("number"));
			while (rs.next()) {
			String carrier = rs.getString("carrier");
			String numb = rs.getString("number");
			
			System.out.println(carrier);
			System.out.println(numb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
