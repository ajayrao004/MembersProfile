package com.member.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.model.Member;
import com.member.service.MemberService;

@RestController
@RequestMapping("api/members")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/registration")
	public ResponseEntity<List<Member>> register(@RequestBody List<Member> member)
	{
		return memberService.register(member);
		
	}

}
