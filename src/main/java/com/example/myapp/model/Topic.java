package com.example.myapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String content;
	
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	@OneToMany(mappedBy="topic",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Widget> widget;

	public int getId() {
		return id;
	}

	public List<Widget> getWidget() {
		return widget;
	}

	public void setWidget(List<Widget> widget) {
		this.widget = widget;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public void set(Topic newTopic) {
		this.title = newTopic.title != null ? newTopic.title : this.title;
		this.lesson = newTopic.lesson != null ? newTopic.lesson : this.lesson;
		this.content = newTopic.content != null ? newTopic.content : this.content;
		this.widget = newTopic.widget != null ? newTopic.widget : this.widget;
		
		
	}
	
}
