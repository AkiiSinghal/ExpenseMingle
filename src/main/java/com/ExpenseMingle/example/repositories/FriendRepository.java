package com.ExpenseMingle.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ExpenseMingle.example.models.Friend;
import com.ExpenseMingle.example.models.FriendId;
import com.ExpenseMingle.example.models.User;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
	@Query("SELECT u from users as u where u.user_id IN (Select friend_id FROM user_friends Where user_id=?1)")
	List<User> findAllFriendByUserId(Long userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("DELETE FROM user_friends WHERE user_id=?1 or friend_id=?1")
	void deleteAllFriends(Long userId);
}
