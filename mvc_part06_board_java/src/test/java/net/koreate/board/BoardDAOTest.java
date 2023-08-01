package net.koreate.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.config.RootConfig;
import com.bitc.board.dao.BoardDAO;
import com.bitc.board.util.Criteria;

import lombok.RequiredArgsConstructor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	classes = {RootConfig.class}
)
public class BoardDAOTest {
	
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void mapperScanTest() throws Exception {
		System.out.println(dao);
		System.out.println(dao.getClass().getName());
		System.out.println(dao.read(1020));
		System.out.println(dao.listCriteria(new Criteria()));
	}
}
