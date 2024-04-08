package com.example.ecommver2.model;


//
//@Entity
//@Table(name = "products")
public class Product {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String category;
	private double price;
	private String description;
	public Product(int id2, String name2, double price2, String category2, String description2) {
		this.id = id2;
		this.name = name2;
		this.price = price2;
		this.category = category2;
		this.description = description2;
	}

	public Product() {

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}




