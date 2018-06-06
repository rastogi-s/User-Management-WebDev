package com.example.myapp.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class MultipleChoiceExamQuestion extends BaseExamQuestion {

	@Column(name = "CHOICE", nullable = false)
	private String choice;

	@OneToMany(mappedBy = "multipleChoiceExamQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Choices> choices;

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public List<Choices> getChoices() {
		return choices;
	}

	public void setChoices(List<Choices> choices) {
		this.choices = choices;
	}

}
