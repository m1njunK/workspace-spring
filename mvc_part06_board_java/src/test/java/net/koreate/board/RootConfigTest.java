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

import com.bitc.board.config.RootConfig;
import com.bitc.board.vo.BoardVO;
import com.zaxxer.hikari.HikariConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = {RootConfig.class}
)
public class RootConfigTest {
	
	@Autowired
	HikariConfig hc;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SqlSessionFactory sqlSessoinFactory;
	
	@Test
	public void sqlSessoinFactory() {
		SqlSession session = sqlSessoinFactory.openSession();
		BoardVO vo = session.selectOne("com.bitc.board.dao.BoardDAO.read",1020);
		System.out.println(vo);
	}
	
	@Test
	public void hikariTest() throws SQLException {
		System.out.println(hc);
		Connection conn = ds.getConnection();
		System.out.println(conn);
		conn.close();
	}
	
}
