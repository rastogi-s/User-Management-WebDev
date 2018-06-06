package com.example.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
}
