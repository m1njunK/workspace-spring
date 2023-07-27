package com.bitc.db_test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.db_test.dao.MemberDAO;
import com.bitc.db_test.vo.MemberVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest{
	
	@Autowired
	MemberDAO md;

	@Before
	public void init() {
		System.out.println("memberDAO : " + md);
	}
	
//	@Test
//	public void testInsertMember() {
//		MemberVO member = new MemberVO();
//		member.setUserid("id001");
//		member.setUserpw("pw001");
//		member.setUsername("김진우");
//		int result = md.insertMember(member);
//		System.out.println("insertMember result = " + result);
//	}
	
	@Test
	public void testReadMember() {
		MemberVO member = md.readMember("id001");
		System.out.println(member);
	}
}
