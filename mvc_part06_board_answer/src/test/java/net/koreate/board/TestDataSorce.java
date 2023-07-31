package net.koreate.board;

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
@ContextConfiguration(locations= {
	"classpath:/context/root/root-context.xml"	
})
public class TestDataSorce {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testSession() {
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("session : " + session);
	}
	
	@Test
	public void dataSource() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	} // end conn
	
	
	
	
}







