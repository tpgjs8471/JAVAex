package com.projectspring.www.service;

import java.util.List;

import com.projectspring.www.domain.BoardDTO;
import com.projectspring.www.domain.BoardVO;
import com.projectspring.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int getTotalCount(PagingVO pagingVO);

	int modify(BoardVO bvo);

	int remove(long bno);

	BoardDTO getDetailFile(long bno);

	int fileRemove(String uuid);

	int modifyFile(BoardDTO bdto);

}
