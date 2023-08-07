package com.bitc.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.comment.dao.CommentDAO;
import com.bitc.comment.vo.CommentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:/spring/root-context.xml"}
)
public class MyBatisTest {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testSqlSessionFactory(){
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println(session.getConnection());
	}
	
	@Autowired
	CommentDAO dao;
	
	@Test
	public void test2InsertComment()throws Exception{
		CommentVO vo = new CommentVO();
		vo.setBno(3);
		vo.setCommentText("test- comment");
		vo.setCommentAuth("최기근");
		int result = dao.insert(vo);
		System.out.println("result : "  + result);
	}
	
	@Test
	public void test3List() throws Exception{
		List<CommentVO> list = dao.commentList(3);
		System.out.println(list);
	}
	
}
