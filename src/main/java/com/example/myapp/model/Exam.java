package com.example.myapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget {
	private String title;
	private String description;
	
	@OneToMany(mappedBy="exam",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private List<BaseExamQuestion> baseExamQuestions;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BaseExamQuestion> getQuestions() {
		return baseExamQuestions;
	}

	public void setQuestions(List<BaseExamQuestion> baseExamQuestions) {
		this.baseExamQuestions = baseExamQuestions;
	}
}
