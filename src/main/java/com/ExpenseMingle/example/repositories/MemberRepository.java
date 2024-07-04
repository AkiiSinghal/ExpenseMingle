package com.ExpenseMingle.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ExpenseMingle.example.models.Group;
import com.ExpenseMingle.example.models.Member;
import com.ExpenseMingle.example.models.MemberId;
import com.ExpenseMingle.example.models.User;

public interface MemberRepository extends JpaRepository<Member, MemberId> {
	@Query("SELECT u from users as u where u.user_id IN (Select user_id FROM group_users WHERE group_id=?1)")
	List<User> findAllUserByGroupId(Long groupId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM group_users WHERE group_id=?1")
	void deleteAllMembersByGroupId(Long groupId);

	@Modifying(clearAutomatically = true)
	@Query("SELECT g FROM groups as g WHERE g.group_id IN (Select group_id FROM group_users WHERE user_id=?1)")
	List<Group> findAllGroupByUserId(Long userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM group_users WHERE user_id=?1")
	void deleteAllMembersByUserId(Long userId);
}
