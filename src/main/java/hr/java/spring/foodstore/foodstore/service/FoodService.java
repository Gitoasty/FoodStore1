package hr.java.spring.foodstore.foodstore.service;

import hr.java.spring.foodstore.foodstore.dto.FoodItemDTO;

import java.util.List;
import java.util.Optional;

public interface FoodService {
    List<FoodItemDTO> findAll();
    List<FoodItemDTO> findByName(String name);
    Optional<FoodItemDTO> findWithMinKcal();
    Optional<FoodItemDTO> saveOrUpdate(FoodItemDTO foodItemDTO);
    void deleteFoodItem(Integer id);
}
