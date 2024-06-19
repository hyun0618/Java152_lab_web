package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.UserCreateDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    
	private final UserDao userDao;
	
	// 아이디 중복 체크 
	public boolean checkUserid(String userid) {
		log.debug("checkUserid(userid={})", userid);
		
		User user = userDao.selectByUserid(userid);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	// 회원가입
	public int create(UserCreateDto dto) { // insert의 성공/실패
		log.debug("create({})", dto);
		
		int result = userDao.insert(dto.toEntity());
		return result;
	}


}
