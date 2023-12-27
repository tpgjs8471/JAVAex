package com.projectspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.projectspring.www.repository.MemberDAO;
import com.projectspring.www.security.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO mdao;

	@Override
	public boolean updataLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public int register(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOk = mdao.register(mvo);
		return mdao.insertAuthInin(mvo.getEmail());
	}

	@Override
	public List<MemberVO> selectAllList() {
		// TODO Auto-generated method stub
		return mdao.selectAllList();
	}

	@Override
	public MemberVO getDetail(String email) {
		// TODO Auto-generated method stub
		return mdao.selectUser(email);
	}

	@Override
	public String selectpwd(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.selectPwd(mvo);
	}

	@Override
	public int modifyNick(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modifyNick(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modify(mvo);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		return mdao.remove(email);
	}
}
