package com.ExpenseMingle.example.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareId implements Serializable {
	private Long user_id;
	private Long expense_id;
}
