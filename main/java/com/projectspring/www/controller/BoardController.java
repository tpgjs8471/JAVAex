package com.projectspring.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectspring.www.domain.BoardDTO;
import com.projectspring.www.domain.BoardVO;
import com.projectspring.www.domain.FileVO;
import com.projectspring.www.domain.PagingVO;
import com.projectspring.www.handler.FileHandler;
import com.projectspring.www.handler.PagingHandler;
import com.projectspring.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Inject
	private BoardService bsv;

	@Inject
	private FileHandler fh;
	
	@GetMapping("/register")
	public String register() {
		log.info("start >>");
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name = "files", required = false)MultipartFile[] files) {
		log.info("BVO >> "+bvo);
		List<FileVO> flist = null;
		if (files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		int isUp = bsv.register(new BoardDTO(bvo,flist));
		re.addAttribute("isUp", isUp);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pagingVO) {
		List<BoardVO> list = bsv.getList(pagingVO);
		m.addAttribute("list", list);
		
		int totalCount = bsv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno")long bno) {
//		BoardVO bvo = bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
		m.addAttribute("bdto", bdto);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name = "files", required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		if (files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk = bsv.modifyFile(bdto);
		
		re.addFlashAttribute("isOk", isOk);
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")long bno, RedirectAttributes re) {
		int isDel = bsv.remove(bno);
		
		re.addFlashAttribute("isDel", isDel);
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value = "/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		int isdel = bsv.fileRemove(uuid);
		
		return isdel > 0 ? new ResponseEntity<String>("1" , HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
