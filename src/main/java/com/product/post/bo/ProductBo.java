/**
 * 
 */
package com.product.post.bo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yuvaraj
 *
 */
public class ProductBo {

	private Long id;

	@NotEmpty(message = "please check product name")
	private String name;

	@NotNull(message = "please check product price")
	private Double price;

	@NotEmpty(message = "please check product image")
	private String image;

	private String description;

	@NotNull(message = "please check product category")
	private Long category;

	private Float width;

	private Float height;

	private Float weight;

	@NotNull(message = "please check product quantity")
	private Float quantity;

	private Float shippingFee;

	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
