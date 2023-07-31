package net.koreate.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"classpath:/context/root/root-context.xml"	
})
public class MapperTest {
	
	@Autowired
	BoardDAO dao;
	
	@Test
	public void testMapper() throws Exception {
		System.out.println(dao);
		List<BoardVO> list = dao.listAll();
		System.out.println(list);
	}
	
}
