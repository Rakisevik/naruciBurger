package com.burger.narucibruger.web;

import com.burger.narucibruger.dto.IngredientResponseDto;
import com.burger.narucibruger.dto.OrderRequestDto;
import com.burger.narucibruger.model.Ingredient;
import com.burger.narucibruger.repository.IngredientRepository;
import com.burger.narucibruger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@CrossOrigin(origins = "*")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/ingredients")
    public ResponseEntity getAllIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientResponseDto> response = new ArrayList<>();
        for(Ingredient ingredient:ingredients) {
            response.add(new IngredientResponseDto(ingredient.getId(),ingredient.getIngredient(),ingredient.getPrice()));
        }
        return ok(response);
    }

    @PostMapping("/order")
    public ResponseEntity createOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody OrderRequestDto orderRequestDto) {
        try {
            orderService.save(orderRequestDto, userDetails.getUsername());
            return ok("Narudžbina uspešno kreirana");
        }catch (Exception e){
            return ok("Greška");
        }


    }
}


