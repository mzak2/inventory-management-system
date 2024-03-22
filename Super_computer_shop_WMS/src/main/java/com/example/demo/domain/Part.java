package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;
import com.example.demo.validators.ValidNoMoreThanMax;
import com.example.demo.validators.ValidNoLessThanMin;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@ValidDeletePart
@ValidNoMoreThanMax
@ValidNoLessThanMin
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    //@Max(value = 201, message = "The inventory is over the maximum inventory limit!")
    int inv;

    /*Using the @Min methodology like above, we send a message when inventory levels are equal
    to zero, then we create our variables minInv and maxInv to represent our minimum
    and maximum levels of inventory for a given part*/
    @Min(value = 0, message = "The minimum inventory for this part must be above zero!")
    //@Max(value = 201, message = "The inventory is over the maximum inventory limit!")

    int minInv;
    @Min(value = 0, message = "The maximum inventory for this part must be above zero!")
    //@Max(value = 201, message = "The inventory is over the maximum inventory limit!")
    int maxInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = 0;
        this.maxInv = 200;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = 0;
        this.maxInv = 200;
    }

    /*Added Part constructor method for setting minInv and maxInv
     while also adding in the above constructors default values for
     the same variables to avoid conflicts*/
    public Part(long id, String name, double price, int inv, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = min;
        this.maxInv = max;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /*create a method to make sure our inventory levels never go above
    or below our min/max allowable inventory, if it does, it resets to
    the minInv and maxInv limits*/
    public boolean validateMinMax(){
        if((this.inv < this.minInv) || (this.inv > this.maxInv) ){
            return false;
        } else {
            return  true;
        }
    }

    /*create getters and setters methods for our minInv and maxInv variables
    to properly update them or return their values as needed*/
    public void setMinInv(int min){
        this.minInv = min;
    }
    public int getMinInv(){
        return this.minInv;
    }

    public void setMaxInv(int max){
        this.maxInv = max;
    }
    public int getMaxInv(){
        return this.maxInv;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
