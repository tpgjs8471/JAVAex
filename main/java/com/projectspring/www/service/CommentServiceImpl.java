package com.projectspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.projectspring.www.domain.CommentVO;
import com.projectspring.www.domain.PagingVO;
import com.projectspring.www.handler.PagingHandler;
import com.projectspring.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

	@Inject
	private CommentDAO cdao;

	@Override
	public int insert(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.insert(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		
		List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo,totalCount,list);
		return ph;
	}

	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

	@Override
	public int update(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}
}
