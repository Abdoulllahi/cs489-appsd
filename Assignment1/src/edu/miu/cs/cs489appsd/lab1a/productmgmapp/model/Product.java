package edu.miu.cs.cs489appsd.lab1a.productmgmapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product {

    private Long id;
    private String name;
    private LocalDate dateSupplied;
    private int quantity;
    private Double unitPrice;

    public Product() {
        super();
    }

    public Product(Long id, String name, String dateSupplied, int quantity, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.dateSupplied = LocalDate.parse(dateSupplied, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product(Long id, String name, int quantity, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.dateSupplied = LocalDate.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return
                "{ \"productId\": " + this.id + ", \"name\": " + this.name + ", \"dateSupplied\": " + this.dateSupplied
                        + ", \"quantityInStock\": " + this.quantity + ", \"unitPrice\": " + this.unitPrice + " }";
    }
}
