package com.burger.narucibruger.repository;

import com.burger.narucibruger.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {
}
