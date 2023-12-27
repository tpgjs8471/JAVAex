package com.projectspring.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.projectspring.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Slf4j
public class FileHandler {

	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR,today);
		
		if (!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			log.info("orginalFileName"+originalFileName);
			
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			log.info("uuid>>  "+uuid);
			fvo.setUuid(uuid.toString());
			
			String fullFileName = uuid.toString()+"_"+fileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				
				if (isImageFile(storeFile)) {
					fvo.setFileType(1);
					
					File thumbNail = new File(folders,uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;
	}
		private boolean isImageFile(File storeFile) throws IOException {
			String mimeType = new Tika().detect(storeFile);
			return mimeType.startsWith("image")? true : false;
		}
	
	
}
