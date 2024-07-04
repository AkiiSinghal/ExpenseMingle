package com.ExpenseMingle.example.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberId implements Serializable {
	private Long group_id;
	private Long user_id;
}
