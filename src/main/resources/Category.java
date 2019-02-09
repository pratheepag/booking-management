package com.learning.learningmanagement.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class Category implements Serializable {

	private static final long serialVersionUID = 8958L;

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@Size(min = 3, max = 200, message = "About Me must be between 3 and 200 characters")
	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
