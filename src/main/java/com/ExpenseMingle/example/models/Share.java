package com.ExpenseMingle.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "expense_users")
@IdClass(ShareId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Share {
	@Id
	private Long expense_id;
	@Id
	private Long user_id;
	
	private Double amount_paid;
	private Double share;
	private Double cat_value;
	
	@ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
	@JsonIgnore
    private Expense expense;
}
