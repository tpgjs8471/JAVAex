package com.projectspring.www.repository;

import java.util.List;

import com.projectspring.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int fileRemove(String uuid);

	List<FileVO> selectListAllFiles();

}
