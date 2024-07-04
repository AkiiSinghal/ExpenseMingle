package com.ExpenseMingle.example.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseMingle.example.models.Friend;
import com.ExpenseMingle.example.models.Group;
import com.ExpenseMingle.example.models.User;
import com.ExpenseMingle.example.repositories.FriendRepository;
import com.ExpenseMingle.example.repositories.MemberRepository;
import com.ExpenseMingle.example.repositories.UserRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping
	public List<User> getList(){
		return userRepository.findAll();
	}
	
	@GetMapping("{uid}")
	public User get(@PathVariable Long uid){
		return userRepository.getReferenceById(uid);
	}
	
	@PostMapping
	public User create(@RequestBody final User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}
	
	@PutMapping("{uid}")
	public User update(@PathVariable Long uid, @RequestBody User user) {
		User excitingUser = userRepository.getReferenceById(uid);
		BeanUtils.copyProperties(user, excitingUser, "user_id");
		return userRepository.saveAndFlush(excitingUser);
	}
	
	@DeleteMapping("{uid}")
	public void delete(@PathVariable Long uid) {
		friendRepository.deleteAllFriends(uid);
		memberRepository.deleteAllMembersByUserId(uid);
		memberRepository.flush();
		userRepository.deleteById(uid);
	}
	
	//Friendship
	
	@GetMapping("{uid}/friends")
	public List<User> getList(@PathVariable Long uid) {
		return friendRepository.findAllFriendByUserId(uid);
	}
	
	@PostMapping("{uid}/friends/{fid}")
	public void create(@PathVariable Long uid, @PathVariable Long fid) {
		Friend frienship = new Friend(uid, fid);
		friendRepository.saveAndFlush(frienship);
		frienship.setUser_id(fid);
		frienship.setFriend_id(uid);
		friendRepository.saveAndFlush(frienship);
	}
	
	@DeleteMapping("{uid}/friends/{fid}")
	public void delete(@PathVariable Long uid, @PathVariable Long fid) {
		Friend frienship = new Friend(uid, fid);
		friendRepository.delete(frienship);
		frienship.setUser_id(fid);
		frienship.setFriend_id(uid);
		friendRepository.delete(frienship);
	}
	
	//Group
	
	@GetMapping("{uid}/groups")
	public List<Group> getGroupList(@PathVariable Long uid) {
		return memberRepository.findAllGroupByUserId(uid);
	}
	
}
