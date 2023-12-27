package com.projectspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectspring.www.domain.CommentVO;
import com.projectspring.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	int selectOneBnoTotalCount(long bno);

	List<CommentVO> selectListPaging(@Param("bno")long bno, @Param("pgvo")PagingVO pgvo);

	int delete(int cno);

	int update(CommentVO cvo);

}
