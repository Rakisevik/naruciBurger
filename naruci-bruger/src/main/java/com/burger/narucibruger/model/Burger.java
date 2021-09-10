package com.burger.narucibruger.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "burger")
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(mappedBy = "burger")
    private Set<BurgerIngredient> burgerIngredients;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    public Burger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BurgerIngredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setBurgerIngredients(Set<BurgerIngredient> burgerIngredients) {
        this.burgerIngredients = burgerIngredients;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
