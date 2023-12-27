package com.projectspring.www.service;

import com.projectspring.www.domain.CommentVO;
import com.projectspring.www.domain.PagingVO;
import com.projectspring.www.handler.PagingHandler;

public interface CommentService {

	int insert(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int delete(int cno);

	int update(CommentVO cvo);

}
