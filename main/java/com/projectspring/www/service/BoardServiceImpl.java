package com.projectspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectspring.www.domain.BoardDTO;
import com.projectspring.www.domain.BoardVO;
import com.projectspring.www.domain.FileVO;
import com.projectspring.www.domain.PagingVO;
import com.projectspring.www.repository.BoardDAO;
import com.projectspring.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;

	@Inject
	private FileDAO fdao;
	
//	@Override
//	public int register(BoardVO bvo) {
//		// TODO Auto-generated method stub
//		return bdao.register(bvo);
//	}
	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		bdao.ccnt();
		bdao.fcnt();
		return bdao.getList(pagingVO);
	}
	@Transactional
	@Override
	public BoardVO getDetail(long bno) {
		// TODO Auto-generated method stub
		bdao.readCount(bno,1);
		return bdao.getDetail(bno);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pagingVO);
	}
	
	@Transactional
	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		bdao.readCount(bvo.getBno(), -2);
		return bdao.modify(bvo);
	}

	@Override
	public int remove(long bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}
	
	@Transactional
	@Override
	public int register(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		bdao.readCount(boardDTO.getBvo().getBno(), -2);
		int isUp = bdao.register(boardDTO.getBvo());
		if (boardDTO.getFlist() == null) {
			isUp *=1;
			return isUp;
		}
		
		if (isUp > 0 && boardDTO.getFlist().size() > 0) {
			long bno = bdao.selectOneBno();
			
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(bno);
				isUp *= fdao.insertFile(fvo);
			}
		}
		return isUp;
	}
	
	@Transactional
	@Override
	public BoardDTO getDetailFile(long bno) {
		// TODO Auto-generated method stub
		bdao.readCount(bno,1);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bdao.getDetail(bno));
		bdto.setFlist(fdao.getFileList(bno));
		return bdto;
	}

	@Override
	public int fileRemove(String uuid) {
		// TODO Auto-generated method stub
		return fdao.fileRemove(uuid);
	}

	@Override
	public int modifyFile(BoardDTO bdto) {
		// TODO Auto-generated method stub
		bdao.readCount(bdto.getBvo().getBno(),-2);
		int isOk = bdao.modify(bdto.getBvo());
		if (bdto.getFlist() == null) {
			isOk *=1;
		}else {
			if (isOk > 0 && bdto.getFlist().size() > 0) {
				long bno = bdto.getBvo().getBno();
				
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}
}
