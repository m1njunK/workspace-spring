package com.bitc.db_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.integration.c3p0.MysqlConnectionTester;

public class MySQLConnectorTest {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/digital_spring";
	String user = "digital";
	String pass = "12345";


	public void testConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			System.out.println("DriverClass를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("연결 요청 정보 오류 : " + e.getMessage());
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
