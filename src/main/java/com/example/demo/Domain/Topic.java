package com.example.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Topic {
	public Topic(String name, String description, String id) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setId(id);
	}
	public Topic() {
		
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
	@NotBlank
	@Size(min = 5, max = 30)
	private String name;
	@NotBlank
	@Email
	private String description;
	@Id
	private String id;
}
