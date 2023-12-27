package com.projectspring.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectspring.www.security.MemberVO;
import com.projectspring.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member/**")
@Controller
@Slf4j
public class MemberController {

	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		
		int isOk = msv.register(mvo);
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		log.info("errMsg> "+request.getAttribute("errMsg"));
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request).getAttribute("errMsg");
		return "redirect:/member/login";
	}
	@GetMapping("/list")
	public void list(Model m) {
		List<MemberVO> list = msv.selectAllList();
		m.addAttribute("memList", list);
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("email")String email) {
		
		MemberVO mvo = msv.getDetail(email);
		m.addAttribute("mvo", mvo);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, RedirectAttributes re, HttpServletResponse res, HttpServletRequest req) {
		log.info("modify mvo >> mvo");
		if (mvo.getPwd().isEmpty()) {
			String NoPwd = msv.selectpwd(mvo);
			mvo.setPwd(NoPwd);
			int onlyNick = msv.modifyNick(mvo);
			re.addFlashAttribute("onlyNick", onlyNick);
			re.addAttribute("email", mvo.getEmail());
		}else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			int isOk = msv.modify(mvo);
			
			re.addFlashAttribute("isOk", isOk);
			re.addAttribute("email", mvo.getEmail());
		}
		logout(req, res);
		return "redirect:/member/detail";
	}
	
	@GetMapping("/remove")
	public String removeMember(@RequestParam("email")String email, Model m, RedirectAttributes re,
			HttpServletRequest req, HttpServletResponse res) {
		int isOk = msv.remove(email);
		re.addFlashAttribute("isOk", isOk);
		logout(req, res);
		return "index";
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, authentication);
	}
	
}
