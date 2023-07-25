package com.bitc.lombok;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LombokTest {
	
	@Before
	public void before() {
		System.out.println("test 전");
	}

	@Test
	public void test() {
		System.out.println("Test 실행 method");
		UserVO vo = new UserVO("id001","pw001");
		System.out.println(vo);
		
		vo.setUno(1);
		vo.setUname("팽수");
		vo.setRegdate(new Date());
		List<String> friendsList = new ArrayList<String>();
		friendsList.add("팽수1");
		friendsList.add("팽수2");
		friendsList.add("팽수3");
		vo.setFriendList(friendsList);
		System.out.println(vo);
		
		UserVO user = UserVO.builder()
				.uno(2)
				.uid("id002")
				.upw("pw002")
				.uname("팽수")
				// .friendList(friendsList)
				.list("김판길")
				.list("이인")
				.list("박준우")
				.regdate(new Date())
				.build();
		System.out.println(user);
		
	}
	
	@After
	public void after() {
		System.out.println("test 작업 완료");
	}
}
