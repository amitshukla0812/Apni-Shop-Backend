package com.apnishop.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    private String id;

    private String user;
    private String product;

    private String name;
    private String brand;

    @ElementCollection
    @CollectionTable(name = "wishlist_colors", joinColumns = @JoinColumn(name = "wishlist_id"))
    @Column(name = "color")
    private List<String> color;

    @ElementCollection
    @CollectionTable(name = "wishlist_sizes", joinColumns = @JoinColumn(name = "wishlist_id"))
    @Column(name = "size")
    private List<String> size;

    private Integer stockQuantity;
    private Double price;
    private String pic;

    // ---- Explicit getters/setters (no Lombok needed) ----

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
