package com.example.myapp.model;
import javax.persistence.Entity;

@Entity
public class Assignment extends Widget {
	private String title;
	private String description;
	private int points;
	
	public String getTitle() {
		return title;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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
	
	public void set(Assignment newAssign) {
		this.title = newAssign.title != null ? newAssign.title : this.title;
		this.description = newAssign.description != null ? newAssign.description : this.description;
		this.points = newAssign.points >=0 ? newAssign.points : this.points;

		
	}

	
}
