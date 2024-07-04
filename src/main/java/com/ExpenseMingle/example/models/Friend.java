package com.ExpenseMingle.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user_friends")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(FriendId.class)
public class Friend {
	@Id
	private Long user_id;
	@Id
	private Long friend_id;
}
