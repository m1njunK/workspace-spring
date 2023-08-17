package com.bitc.user.service;

import org.springframework.stereotype.Service;

import com.bitc.user.mapper.UserMapper;
import com.bitc.user.vo.LoginDTO;
import com.bitc.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper mapper;
	
	@Override
	public void signUp(UserVO vo) throws Exception {
		mapper.signUp(vo);
	}

	@Override
	public UserVO signIn(LoginDTO dto) throws Exception {
		return mapper.signIn(dto);
	}

	@Override
	public UserVO getUserById(String uid) throws Exception {

		return null;
	}

}
