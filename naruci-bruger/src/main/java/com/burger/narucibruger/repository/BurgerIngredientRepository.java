package com.burger.narucibruger.repository;

import com.burger.narucibruger.model.Burger;
import com.burger.narucibruger.model.BurgerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerIngredientRepository extends JpaRepository<BurgerIngredient, Long> {
}
