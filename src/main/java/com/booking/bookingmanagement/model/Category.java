package com.booking.bookingmanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="category")
public class Category implements Serializable {

	private static final long serialVersionUID = 8958L;

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@Size(min = 3, max = 200, message = "About Me must be between 3 and 200 characters")
	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "package_id")
	private Packages packages;
	
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
