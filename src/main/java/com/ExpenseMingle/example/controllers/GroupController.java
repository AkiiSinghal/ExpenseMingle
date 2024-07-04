package com.ExpenseMingle.example.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseMingle.example.models.Group;
import com.ExpenseMingle.example.models.Member;
import com.ExpenseMingle.example.models.Simplify;
import com.ExpenseMingle.example.models.User;
import com.ExpenseMingle.example.repositories.GroupRepository;
import com.ExpenseMingle.example.repositories.MemberRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/groups")
public class GroupController {
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private MemberRepository memberRepository;
	
//	To be removed
	@GetMapping
	public List<Group> getList() {
		return groupRepository.findAll();
	}

//	To be removed
	@GetMapping("{gid}")
	public Group get(@PathVariable Long gid) {
		return groupRepository.getReferenceById(gid);
	}
	
	@PostMapping
	public Group post(@RequestBody final Group group) {
		return groupRepository.saveAndFlush(group);
	}
	
	@DeleteMapping("{gid}")
	public void delete(@PathVariable Long gid) {
		memberRepository.deleteAllMembersByGroupId(gid);
		memberRepository.flush();
		groupRepository.deleteById(gid);
	}
	
	@GetMapping("{gid}/simplify")
	public List<Simplify> simplify(@PathVariable Long gid) {
		List<Object[]> l = groupRepository.simplifyExpenseByGroupId(gid);
		List<Simplify> res = new ArrayList<Simplify>();
		List<Long> exp1_uid = new ArrayList<Long>();
		List<Double> exp1_share = new ArrayList<Double>();
		List<Long> exp2_uid = new ArrayList<Long>();
		List<Double> exp2_share = new ArrayList<Double>();
		Integer i=0, j=0, m, n;
		Long user_id;
		Double share;
		
		for(Object[] obj: l) {
			user_id = (Long) obj[0];
			share = (Double) obj[1];
			if(share < 0)
				break;
			exp1_uid.add(user_id);
			exp1_share.add(share);
		}
		Collections.reverse(l);
		for(Object[] obj: l) {
			user_id = (Long) obj[0];
			share = (Double) obj[1];
			if(share >= 0)
				break;
			exp2_uid.add(user_id);
			exp2_share.add(-share);
		}
		m = exp1_uid.size();
		n = exp2_uid.size();
		while(i < m && j < n) {
			if(exp1_share.get(i) == 0)
				break;
			if(exp1_share.get(i) < exp2_share.get(j)) {
				Simplify s = new Simplify(exp1_uid.get(i), exp2_uid.get(j), exp1_share.get(i));
				res.add(s);
				exp2_share.set(j, exp2_share.get(j) - exp1_share.get(i));
				i++;
			}
			else if(exp1_share.get(i) == exp2_share.get(j)) {
				Simplify s = new Simplify(exp1_uid.get(i), exp2_uid.get(j), exp1_share.get(i));
				res.add(s);
				i++;
				j++;
			}
			else {
				Simplify s = new Simplify(exp1_uid.get(i), exp2_uid.get(j), exp2_share.get(j));
				res.add(s);
				exp1_share.set(i, exp1_share.get(i) - exp2_share.get(j));
				j++;
			}
		}
		return res;
	}
	
	@PutMapping("{gid}")
	public Group put(@PathVariable Long gid, @RequestBody Group group) {
		Group excistingGroup = groupRepository.getReferenceById(gid);
		BeanUtils.copyProperties(group, excistingGroup, "group_id");
		return groupRepository.saveAndFlush(excistingGroup);
	}
	
	// Members
	
	@GetMapping("{gid}/members")
	private List<User> getUserList(@PathVariable Long gid) {
		return memberRepository.findAllUserByGroupId(gid);
	}
	
	@PostMapping("{gid}/members/{uid}")
	private void createGroupMember(@PathVariable Long gid, @PathVariable Long uid) {
		Member member = new Member(gid, uid);
		memberRepository.saveAndFlush(member);
	}
	
	@DeleteMapping("{gid}/members/{uid}")
	private void deleteGroupMember(@PathVariable Long gid, @PathVariable Long uid) {
		Member member = new Member(gid, uid);
		memberRepository.delete(member);
	}
}
