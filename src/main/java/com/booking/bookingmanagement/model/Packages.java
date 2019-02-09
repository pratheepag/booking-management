package com.booking.bookingmanagement.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="package")
public class Packages  implements Serializable  {
	
	private static final long serialVersionUID = 74458L; 
	
	@Id
	@GeneratedValue
	@Column(name="package_id")
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 200, message = "About Me must be between 3 and 200 characters")
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="price")
	private float price;

	
	@Column(name="image")
	private String  image;
	
	/* @OneToMany
	@JoinTable(name = "category", joinColumns = {@JoinColumn(name="category_id")}) */
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "package_category", 
             joinColumns = { @JoinColumn(name = "package_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "category_id") })
	Set<Category> categoryList = new HashSet<Category>();

	public String getDescription() {
		return description;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Long getId() {
		return id;
	}

	public String  getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImage(String  image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}
