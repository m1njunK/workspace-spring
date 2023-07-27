package com.bitc.db_test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bitc.db_test.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Repository("md")
@RequiredArgsConstructor
public class MemberMyBatisDAOImpl implements MemberDAO {

	private final SqlSession session;
	
	// MemberMapper == memberMapper.xml
	
	@Override
	public int insertMember(MemberVO member) {
		int result = session.insert("MemberMapper.insertMember",member);
		System.out.println("dao : " + result);
		return result;
	}

	@Override
	public MemberVO readMember(String userid) {
		MemberVO member = session.selectOne("MemberMapper.read",userid);
		System.out.println(member);
		return member;
	}

	@Override
	public MemberVO readMemberWithPass(String userid, String userpw) {
		Map<String,String> map = new HashMap<>();
		map.put("id", userid);
		map.put("pw", userpw);
		MemberVO vo = session.selectOne("MemberMapper.readWithPass",map);
		return vo;
	}

	@Override
	public List<MemberVO> readmemberList() {
		return session.selectList("MemberMapper.memberList");
	}

	@Override
	public Integer readMax() {
		Integer result = session.selectOne("MemberMapper.max");
		return result;
	}

}
