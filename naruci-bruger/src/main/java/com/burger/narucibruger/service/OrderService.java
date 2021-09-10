package com.burger.narucibruger.service;

import com.burger.narucibruger.dto.BurgerDto;
import com.burger.narucibruger.dto.IngredientDto;
import com.burger.narucibruger.dto.OrderRequestDto;
import com.burger.narucibruger.model.Burger;
import com.burger.narucibruger.model.BurgerIngredient;
import com.burger.narucibruger.model.Ingredient;
import com.burger.narucibruger.model.Order;
import com.burger.narucibruger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @Autowired
    private BurgerIngredientRepository burgerIngredientRepository;



    public void save(OrderRequestDto orderRequestDto, String username) {
        Order order = new Order();
        order.setAltAddress(orderRequestDto.getAltAddress());
        order.setAltPhone(orderRequestDto.getAltPhone());
        order.setQuantity(1);
        Set<Burger> burgers = new HashSet<>();
        order.setUser(userRepository.findByUsername(username).get());
        orderRepository.save(order);
        for(BurgerDto burgerDto: orderRequestDto.getBurgers()) {
            Burger burger = new Burger();
            Set<BurgerIngredient> ingredients = new HashSet<>();
            for(IngredientDto ingredientDto: burgerDto.getIngredients()) {
                ingredients.add(new BurgerIngredient(burger,ingredientRepository.getOne((long)ingredientDto.getId()),ingredientDto.getQuantity()));
            }
            burger.setBurgerIngredients(ingredients);
            burgers.add(burger);
            burger.setOrder(order);
            burgerRepository.save(burger);
            burgerIngredientRepository.saveAll(ingredients);
        }





    }

}
