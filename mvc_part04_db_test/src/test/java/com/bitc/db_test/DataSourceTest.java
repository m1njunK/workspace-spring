package com.bitc.db_test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( 
		locations = {
				"file:src/main/webapp/WEB-INF/spring/root-context.xml"
				//"classpath:test-context.xml"
		}
		
)
public class DataSourceTest {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testFactoru() {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println(session);
	}
	
	@Test
	public void testConn() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			System.out.println("dataBase 연결 실패...");
			e.printStackTrace();
		} finally {
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
