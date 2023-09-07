package com.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.model.Member;
@Repository
public interface MemberDao extends JpaRepository<Member, Integer>{

}
