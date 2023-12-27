package com.projectspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectspring.www.domain.BoardVO;
import com.projectspring.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int getTotalCount(PagingVO pagingVO);

	int modify(BoardVO bvo);

	int remove(long bno);

	long selectOneBno();

	void readCount(@Param("bno")long bno,@Param("cnt")int cnt);

	void ccnt();

	void fcnt();

}
