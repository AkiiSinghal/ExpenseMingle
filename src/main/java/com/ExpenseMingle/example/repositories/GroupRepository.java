package com.ExpenseMingle.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ExpenseMingle.example.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
	@Query("Select user_id, SUM(share) - SUM(amount_paid) FROM expense_users where expense_id in (Select expense_id from expenses where group_id=?1) group by user_id order by SUM(share) - SUM(amount_paid) desc")
	List<Object[]> simplifyExpenseByGroupId(Long userId);
}
