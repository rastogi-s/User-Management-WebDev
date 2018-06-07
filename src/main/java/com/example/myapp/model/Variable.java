package com.example.myapp.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Variable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
		
	private String vars;
	
	@ManyToOne
	@JsonIgnore
	private FillInTheBlanksExamQuestion fillInTheBlanksExamQuestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVars() {
		return vars;
	}

	public void setVars(String vars) {
		this.vars = vars;
	}

	public FillInTheBlanksExamQuestion getFillInTheBlanksExamQuestion() {
		return fillInTheBlanksExamQuestion;
	}

	public void setFillInTheBlanksExamQuestion(FillInTheBlanksExamQuestion fillInTheBlanksExamQuestion) {
		this.fillInTheBlanksExamQuestion = fillInTheBlanksExamQuestion;
	}
	

	

}


