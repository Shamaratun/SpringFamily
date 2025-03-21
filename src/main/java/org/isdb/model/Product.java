package org.isdb.model;



public class Product {
	private int id;
	private String name;
	private String model;
	private int quantity;
	private double price;
	public Product(int id, String name, String model, int quantity, double price) {
		this.id = id;
		this.name = name;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}