package hr.java.spring.foodstore.foodstore.repository;

import hr.java.spring.foodstore.foodstore.model.FoodCategory;
import hr.java.spring.foodstore.foodstore.model.FoodItem;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MockFoodRepository implements FoodRepository {

    private static List<FoodItem> foodItems = new ArrayList<>();

    static {
        FoodItem firstFoodItem = new FoodItem(1, "Apple", FoodCategory.FRUIT, 100,
                new BigDecimal(1),
                new BigDecimal(0.5));
        FoodItem secondFoodItem = new FoodItem(2, "Burger", FoodCategory.MEAT, 1000,
                new BigDecimal(1.1),
                new BigDecimal(0.55));
        foodItems.add(firstFoodItem);
        foodItems.add(secondFoodItem);
    }
    @Override
    public List<FoodItem> findAll() {
        return foodItems;
    }

    @Override
    public List<FoodItem> findByName(String name) {
        return findAll().stream()
                .filter(fi -> fi.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<FoodItem> findWithMinKcal() {
        return findAll().stream()
                .min((fi1, fi2) -> fi1.getKcal().compareTo(fi2.getKcal()));
    }

    @Override
    public Optional<FoodItem> saveOrUpdate(FoodItem foodItem) {

        Optional<FoodItem> existingFoodItem = foodItems.stream()
                .filter(fi -> fi.getId().equals(foodItem.getId())).findFirst();

        if(!existingFoodItem.isEmpty()) {
            FoodItem updatedFoodItem = existingFoodItem.get();
            updatedFoodItem.setName(foodItem.getName());
            updatedFoodItem.setKcal(foodItem.getKcal());
            updatedFoodItem.setSellingPrice(foodItem.getSellingPrice());
            updatedFoodItem.setInitialPrice(foodItem.getInitialPrice());
            foodItems.remove(existingFoodItem.get());
            foodItems.add(updatedFoodItem);

            return Optional.of(updatedFoodItem);
        }
        else {
            FoodItem foodItemToUpdate = new FoodItem();
            foodItemToUpdate.setId(foodItem.getId());
            foodItemToUpdate.setName(foodItem.getName());
            foodItemToUpdate.setFoodCategory(foodItem.getFoodCategory());
            foodItemToUpdate.setKcal(foodItem.getKcal());
            foodItemToUpdate.setSellingPrice(foodItem.getSellingPrice());
            foodItems.add(foodItemToUpdate);

            return Optional.of(foodItemToUpdate);
        }
    }

    @Override
    public void deleteFoodItem(Integer id) {
        foodItems = foodItems.stream()
                        .filter(f1 -> !f1.getId().equals(id)).toList();
    }

    private Integer generateNewFoodItemId() {
        Optional<FoodItem> lastPrimaryKeyValueOptional = foodItems.stream()
                .max((fi1, fi2) -> fi1.getId().compareTo(fi2.getId()));

        if(lastPrimaryKeyValueOptional.isPresent()) {
            FoodItem foodItem = lastPrimaryKeyValueOptional.get();
            return foodItem.getId() + 1;
        }
        else {
            return 1;
        }
    }
}
