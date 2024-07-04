package com.ExpenseMingle.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExpenseMingle.example.models.Share;
import com.ExpenseMingle.example.models.ShareId;

public interface ShareRepository extends JpaRepository<Share, ShareId> {

}
