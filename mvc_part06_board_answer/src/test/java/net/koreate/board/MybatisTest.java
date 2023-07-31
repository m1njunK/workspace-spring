package net.koreate.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.board.util.Criteria;
import com.bitc.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"classpath:/context/root/root-context.xml"	
})
public class MybatisTest {
	
	@Autowired
	SqlSession sqlSession;
	
	/* @Test
	public void sessionTest() {
		BoardVO board = new BoardVO();
		board.setTitle("안녕하세요!");
		board.setContent("ㅈㄱㄴ");
		board.setWriter("김판길");
		int result = sqlSession.insert("boardMapper.create",board);
		System.out.println("삽입된 게시글 개수 : "+result);
	} */
	
	@Test
	public void readBoard() {
		BoardVO vo = sqlSession.selectOne("boardMapper.read",1021);
		System.out.println(vo);
		
		List<BoardVO> list = sqlSession.selectList("boardMapper.listCriteria",new Criteria());
	}
	
}
