package com.bitc.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bitc.di.dao.TestDAO;

import lombok.Setter;

@Service("ts")
public class TestServiceImpl implements TestService {
	@Setter(onMethod_= {@Autowired, @Qualifier("testDAOImpl")})
	private TestDAO dao;
	
	// setter의 매개변수 타입과 일치하는 타입의 bean 존재하면
	// setter를 호출 하면서 Bean 전달
	/*
	 * @Autowired public void setDao(TestDAO dao) { this.dao = dao; }
	 */
	
	@Override
	public void testService(String message) {
		System.out.println(message + " : test service");
		System.out.println("dao : " + dao);
	}

}
