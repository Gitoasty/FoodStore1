package hr.java.spring.foodstore.foodstore.repository;

import hr.java.spring.foodstore.foodstore.model.FoodItem;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    List<FoodItem> findAll();
    List<FoodItem> findByName(String name);
    Optional<FoodItem> findWithMinKcal();
    Optional<FoodItem> saveOrUpdate(FoodItem foodItem);
    void deleteFoodItem(Integer id);
}
