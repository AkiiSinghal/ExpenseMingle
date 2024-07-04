package com.ExpenseMingle.example.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendId implements Serializable {
	private Long user_id;
	private Long friend_id;
}
