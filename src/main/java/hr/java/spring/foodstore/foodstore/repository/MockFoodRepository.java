package hr.java.spring.foodstore.foodstore.repository;

import hr.java.spring.foodstore.foodstore.model.FoodCategory;
import hr.java.spring.foodstore.foodstore.model.FoodItem;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class MockFoodRepository implements FoodRepository {

    private static List<FoodItem> foodItems = new ArrayList<>();

    static {
        FoodItem firstFoodItem = new FoodItem(1, "Apple", FoodCategory.FRUIT, 100,
                new BigDecimal(1),
                BigDecimal.valueOf(0.5));
        FoodItem secondFoodItem = new FoodItem(2, "Burger", FoodCategory.MEAT, 1000,
                BigDecimal.valueOf(1.1),
                BigDecimal.valueOf(0.55));
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
                .min(Comparator.comparing(FoodItem::getKcal));
    }

    @Override
    public Optional<FoodItem> saveOrUpdate(FoodItem foodItem) {

        Optional<FoodItem> existingFoodItem = foodItems.stream()
                .filter(fi -> fi.getId().equals(foodItem.getId())).findFirst();

        if(existingFoodItem.isPresent()) {
            FoodItem updatedFoodItem = existingFoodItem.get();
            updatedFoodItem.setName(foodItem.getName());
            updatedFoodItem.setKcal(foodItem.getKcal());
            updatedFoodItem.setSellingPrice(foodItem.getSellingPrice());
            updatedFoodItem.setInitialPrice(foodItem.getInitialPrice());
            foodItems.remove(existingFoodItem.get());
            foodItems.add(updatedFoodItem);

            foodItems.sort(Comparator.comparing(FoodItem::getId));

            return Optional.of(updatedFoodItem);
        } else {
            FoodItem newFoodItem = new FoodItem();
            newFoodItem.setId(foodItem.getId());
            newFoodItem.setName(foodItem.getName());
            newFoodItem.setFoodCategory(foodItem.getFoodCategory());
            newFoodItem.setKcal(foodItem.getKcal());
            newFoodItem.setSellingPrice(foodItem.getSellingPrice());
            foodItems.add(newFoodItem);

            foodItems.sort(Comparator.comparing(FoodItem::getId));

            return Optional.of(newFoodItem);
        }
    }

    @Override
    public void deleteFoodItem(Integer id) {
        foodItems.removeIf(f1 -> f1.getId().equals(id));
    }

    private Integer generateNewFoodItemId() {
        Optional<FoodItem> lastPrimaryKeyValueOptional = foodItems.stream()
                .max(Comparator.comparing(FoodItem::getId));

        if(lastPrimaryKeyValueOptional.isPresent()) {
            FoodItem foodItem = lastPrimaryKeyValueOptional.get();
            return foodItem.getId() + 1;
        }
        else {
            return 1;
        }
    }
}
