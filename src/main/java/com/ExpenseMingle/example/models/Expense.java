package com.ExpenseMingle.example.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expense_id;
	private String expense_description;
	private String expense_amount;
	private String expense_cat;
	private Long group_id;
	
	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
	private List<Share> shares;
	
	@ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable=false, updatable=false, nullable = false)
	@JsonIgnore
    private Group group;
}
