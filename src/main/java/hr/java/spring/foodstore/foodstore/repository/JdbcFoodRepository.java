package hr.java.spring.foodstore.foodstore.repository;

import hr.java.spring.foodstore.foodstore.model.FoodItem;

import java.util.List;
import java.util.Optional;

public class JdbcFoodRepository implements FoodRepository {
    @Override
    public List<FoodItem> findAll() {
        return null;
    }

    @Override
    public List<FoodItem> findByName(String name) {
        return null;
    }

    @Override
    public Optional<FoodItem> findWithMinKcal() {
        return Optional.empty();
    }

    @Override
    public Optional<FoodItem> saveOrUpdate(FoodItem foodItem) {
        return Optional.empty();
    }

    @Override
    public void deleteFoodItem(Integer id) {

    }
}
