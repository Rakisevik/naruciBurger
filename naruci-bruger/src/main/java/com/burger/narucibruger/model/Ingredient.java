package com.burger.narucibruger.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "price")
    private double price;


    @OneToMany(mappedBy = "ingredient")
    private Set<BurgerIngredient> burgerIngredients;

    public Ingredient() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Set<BurgerIngredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setBurgerIngredients(Set<BurgerIngredient> burgerIngredients) {
        this.burgerIngredients = burgerIngredients;
    }
}
