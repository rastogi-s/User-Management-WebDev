package com.example.myapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
	
	
	@OneToMany(mappedBy = "fillInTheBlanksExamQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Variable> variables;

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	

}
