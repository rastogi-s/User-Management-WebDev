package com.example.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int orderOfWidget;
	
	private String text;

	private String className;

	private String style;

	private String width;

	private String height;
	
	private int size;
	
	private String href;
	
	private String image;
	
	private String src;
	
	private String listItems;
	
	private String listType;

	

	private String widgetType;

	
	@ManyToOne
	@JsonIgnore
	private Topic topic;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getWidgetType() {
		return widgetType;
	}



	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}




	public Integer getOrderOfWidget() {
		return orderOfWidget;
	}



	public void setOrderOfWidget(Integer orderOfWidget) {
		this.orderOfWidget = orderOfWidget;
	}



	public void setSize(Integer size) {
		this.size = size;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getClassName() {
		return className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public String getStyle() {
		return style;
	}



	public void setStyle(String style) {
		this.style = style;
	}



	public String getWidth() {
		return width;
	}



	public void setWidth(String width) {
		this.width = width;
	}



	public String getHeight() {
		return height;
	}



	public void setHeight(String height) {
		this.height = height;
	}



	public Integer getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public String getHref() {
		return href;
	}



	public void setHref(String href) {
		this.href = href;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getSrc() {
		return src;
	}



	public void setSrc(String src) {
		this.src = src;
	}



	public String getListItems() {
		return listItems;
	}



	public void setListItems(String listItems) {
		this.listItems = listItems;
	}



	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public String getListType() {
		return listType;
	}


	public void setListType(String listType) {
		this.listType = listType;
	}


	public void set(Widget widget) {
		
		this.name = widget.name != null ? widget.name : this.name;
		this.orderOfWidget = widget.orderOfWidget>0  ? widget.orderOfWidget : this.orderOfWidget;
		this.text = widget.text != null ? widget.text : this.text;
		this.className = widget.className != null ? widget.className : this.className;
		this.style = widget.style != null ? widget.style : this.style;
		this.width = widget.width != null ? widget.width : this.width;
		this.height = widget.height != null ? widget.height : this.height;
		this.size = widget.size > 0  ? widget.size : this.size;
		this.href = widget.href != null ? widget.href : this.href;
		this.src = widget.src != null ? widget.src : this.src;
		this.listItems = widget.listItems != null ? widget.listItems : this.listItems;
		this.listType = widget.listType != null ? widget.listType : this.listType;
		this.widgetType = widget.widgetType != null ? widget.widgetType : this.widgetType;
		
	}
	
}
