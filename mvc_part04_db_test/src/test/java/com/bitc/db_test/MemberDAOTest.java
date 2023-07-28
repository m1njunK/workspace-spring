package com.bitc.db_test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bitc.db_test.dao.MemberDAO;
import com.bitc.db_test.vo.MemberVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest{
	
	@Autowired
	@Qualifier("md")
	MemberDAO md;
	
	Integer num;

	@Before
	public void init() {
		System.out.println("memberDAO : " + md);
		num = md.readMax();
		if(num == null) {
			num = 1;
		}else {
			num = num + 1;
		}
	}

	@Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setUserid("id00"+num);
		member.setUserpw("pw00"+num);
		member.setUsername("김진우"+num+"호");
		int result = md.insertMember(member);
		System.out.println("insertMember result = " + result);
	}
	
	@Test
	public void testReadMember() {
		MemberVO member = md.readMember("id006");
		System.out.println(member);
	}
	
	@Test
	public void testReaddMember() {
		MemberVO memberWithPass = md.readMemberWithPass("id002", "pw002");
		System.out.println("memberWithPass : " + memberWithPass);
		System.out.println("============================================");
		List<MemberVO> memberList = md.readmemberList();
		for(MemberVO vo : memberList) {
			System.out.println(vo);
		}
		int max = md.readMax();
		System.out.println("maxNumber : " + max);
	}
}
