package com.ExpenseMingle.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "group_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(MemberId.class)
public class Member {
	@Id
	private Long group_id;
	@Id
	private Long user_id;
}
