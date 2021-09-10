package com.burger.narucibruger.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "alt_address")
    @Nullable
    private String altAddress;

    @Column(name = "alt_phone")
    @Nullable
    private String altPhone;

    @OneToMany(mappedBy = "order")
    private Set<Burger> burgers;

    public Order() {
    }

    public Set<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(Set<Burger> burgers) {
        this.burgers = burgers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAltAddress() {
        return altAddress;
    }

    public void setAltAddress(String altAddress) {
        this.altAddress = altAddress;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

}
