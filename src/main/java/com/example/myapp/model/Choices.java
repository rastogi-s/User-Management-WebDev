package com.example.myapp.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Choices {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
		
	private String items;
	
	@ManyToOne
	@JsonIgnore
	private MultipleChoiceExamQuestion multipleChoiceExamQuestion;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public MultipleChoiceExamQuestion getMultipleChoiceExamQuestion() {
		return multipleChoiceExamQuestion;
	}

	public void setMultipleChoiceExamQuestion(MultipleChoiceExamQuestion multipleChoiceExamQuestion) {
		this.multipleChoiceExamQuestion = multipleChoiceExamQuestion;
	}

	
	

}


