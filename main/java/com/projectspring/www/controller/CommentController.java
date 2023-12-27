package com.projectspring.www.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.www.domain.CommentVO;
import com.projectspring.www.domain.PagingVO;
import com.projectspring.www.handler.PagingHandler;
import com.projectspring.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment/**")
@Slf4j
public class CommentController {

	@Inject
	private CommentService csv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		int isOk = csv.insert(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/list/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> spread(@PathVariable("bno")long bno, 
			@PathVariable("page")int page){
		log.info(">> comment List bno >>"+bno+ "/ "+page);
		PagingVO pgvo = new PagingVO(page, 5);
		return new ResponseEntity<PagingHandler>(csv.getList(bno,pgvo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/del/{cno}/{writer}")
	public ResponseEntity<String> delete(@PathVariable("cno")int cno,
			@PathVariable("writer")String writer, Principal principal){
		
		int isOk = 0;
		if (writer.equals(principal.getName())) {
			isOk=csv.delete(cno);
		}
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
	@PutMapping(value = "/mod/{cno}", consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updata(@PathVariable("cno")long cno, @RequestBody CommentVO cvo,
			Principal principal){
		int isOk = 0;
		String userEmail = principal.getName();
		
		if (cvo.getWriter().equals(userEmail)) {
			isOk = csv.update(cvo);
		}
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("2",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
