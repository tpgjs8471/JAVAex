package com.projectspring.www.service;

import java.util.List;

import com.projectspring.www.security.MemberVO;

public interface MemberService {

	boolean updataLastLogin(String authEmail);

	int register(MemberVO mvo);

	List<MemberVO> selectAllList();

	MemberVO getDetail(String email);

	String selectpwd(MemberVO mvo);

	int modifyNick(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

}
