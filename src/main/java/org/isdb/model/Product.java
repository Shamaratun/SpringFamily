package org.isdb.model;

import java.time.LocalDate;

public class Product {
	private int id;
	private String name;
	private String email;
	private String description;
	private int quantity;
	private String address;
	private LocalDate purchaseDate;
	private LocalDate sellDate;
	private double price;

	public Product(int id, String name, String email, String description, int quantity, String address,
			LocalDate purchaseDate, LocalDate sellDate, double price) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.description = description;
		this.quantity = quantity;
		this.address = address;
		this.purchaseDate = purchaseDate;
		this.sellDate = sellDate;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getSellDate() {
		return sellDate;
	}

	public void setSellDate(LocalDate sellDate) {
		this.sellDate = sellDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
