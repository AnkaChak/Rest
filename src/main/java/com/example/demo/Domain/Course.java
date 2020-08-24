package com.example.demo.Domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Course {
	public Course(String name, String description, String id, String topicId) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setId(id);
		this.topic = new Topic(topicId, "", "");
	}
	public Course() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	@NotBlank
	@Size(min = 5, max = 30)
	@Email
	private String name;
	
	private String description;
	@Id
	private String id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Topic topic;
}
