package com.projectspring.www.repository;

import java.util.List;

import com.projectspring.www.security.AuthVO;
import com.projectspring.www.security.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	int insertAuthInin(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	List<MemberVO> selectAllList();

	int updateLastLogin(String authEmail);

	MemberVO selectUser(String email);

	String selectPwd(MemberVO mvo);

	int modifyNick(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

	

	

}
