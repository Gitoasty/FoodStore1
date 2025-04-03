package hr.java.spring.foodstore.foodstore.service;

import hr.java.spring.foodstore.foodstore.dto.FoodItemDTO;
import hr.java.spring.foodstore.foodstore.model.FoodItem;
import hr.java.spring.foodstore.foodstore.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private FoodRepository foodRepository;

    @Override
    public List<FoodItemDTO> findAll() {
        return foodRepository.findAll().stream()
                .map(this::convertFoodItemToFoodItemDTO)
                .toList();
    }

    @Override
    public List<FoodItemDTO> findByName(String name) {
        return foodRepository.findByName(name).stream()
                .map(this::convertFoodItemToFoodItemDTO)
                .toList();
    }

    @Override
    public Optional<FoodItemDTO> findWithMinKcal() {
        return Optional.of(
                convertFoodItemToFoodItemDTO(foodRepository.findWithMinKcal().get()));
    }

    @Override
    public Optional<FoodItemDTO> saveOrUpdate(FoodItemDTO foodItemDTO) {
        Optional<FoodItem> savedFoodItem = foodRepository.saveOrUpdate(convertFoodItemDtoToFoodItem(foodItemDTO));
        return convertFoodItemToFoodItemDTO(savedFoodItem);
    }

    @Override
    public void deleteFoodItem(Integer id) {
        foodRepository.deleteFoodItem(id);
    }

    private Optional<FoodItemDTO> convertFoodItemToFoodItemDTO(Optional<FoodItem> foodItemOptional) {

        if(foodItemOptional.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(new FoodItemDTO(
                    foodItemOptional.get().getId(),
                    foodItemOptional.get().getName(),
                    foodItemOptional.get().getFoodCategory(),
                    foodItemOptional.get().getKcal(),
                    foodItemOptional.get().getSellingPrice()));
        }
    }

    private FoodItem convertFoodItemDtoToFoodItem(FoodItemDTO foodItemDTO) {
        return new FoodItem(
                foodItemDTO.getId(),
                foodItemDTO.getName(),
                foodItemDTO.getFoodCategory(),
                foodItemDTO.getKcal(),
                foodItemDTO.getSellingPrice());
    }

    private FoodItemDTO convertFoodItemToFoodItemDTO(FoodItem foodItem) {
        return new FoodItemDTO(foodItem.getId(),
                foodItem.getName(),
                foodItem.getFoodCategory(),
                foodItem.getKcal(),
                foodItem.getSellingPrice());
    }
}
