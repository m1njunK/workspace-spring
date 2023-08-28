package com.bitc.sec.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bitc.sec.dao.MemberDAO;
import com.bitc.sec.vo.ValidationMemberVO;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ValidationMemberVO vo = null;
		try {
			vo = dao.getMemberByID(username);
			if (vo != null) {
				vo.setAuthList(dao.getAuthList(username));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return 한 사용자 정보를 이용하여 비밀번호를 비교
		return vo == null ? null : new CustomUser(vo);
	}

}
