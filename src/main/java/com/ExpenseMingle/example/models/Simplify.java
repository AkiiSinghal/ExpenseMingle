package com.ExpenseMingle.example.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Simplify implements Serializable {
	private Long sender;
	private Long receiver;
	private Double amount;
}
