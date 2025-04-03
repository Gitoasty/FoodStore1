package hr.java.spring.foodstore.foodstore.controller;

import hr.java.spring.foodstore.foodstore.dto.FoodItemDTO;
import hr.java.spring.foodstore.foodstore.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private FoodService foodService;

    @GetMapping("/all")
    public List<FoodItemDTO> getFoodTest(){
        return foodService.findAll();
    }
}
