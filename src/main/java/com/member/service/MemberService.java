package com.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.member.dao.MemberDao;
import com.member.model.Member;

@Service
public class MemberService {
	  @Autowired
	  MemberDao memberDao;

	public ResponseEntity<List<Member>> register(List<Member> member) 
	{  
			try
			{
				return new ResponseEntity<>(memberDao.saveAll(member),HttpStatus.OK);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

}
