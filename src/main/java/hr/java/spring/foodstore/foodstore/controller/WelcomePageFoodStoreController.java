package hr.java.spring.foodstore.foodstore.controller;

import hr.java.spring.foodstore.foodstore.dto.FoodItemDTO;
import hr.java.spring.foodstore.foodstore.service.FoodService;
import hr.java.spring.foodstore.foodstore.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class WelcomePageFoodStoreController {

    private FoodService foodService;
    private TestService testService;
    public static Logger logger = LoggerFactory.getLogger(WelcomePageFoodStoreController.class);

    @GetMapping("/test-ssl")
    public String testSSLConnection() {
        return testService.makeSecureRequest();
    }

    @Tag(name = "get", description = "GET methods of food APIs")
    @Operation(summary = "Get all food items",
            description = "Get a list of all the foodItems from the database")
    @GetMapping("/all")
    public List<FoodItemDTO> getAllFoodItems() {
        MDC.put("prvi", "a");
        MDC.put("drugi", "b");
        logger.info("Get all food items");
        MDC.clear();
        return foodService.findAll();
    }

    @Operation(summary = "Get a food item",
            description = "Get a specific food item by providing it's ID")
    @Tag(name = "get", description = "GET methods of food APIs")
    @GetMapping("/foodItem/{name}")
    public List<FoodItemDTO> getFoodItemsByName(@Parameter(
            description = "ID of food to be retrieved",
            required = true)@PathVariable String name) {
        return foodService.findByName(name);
    }


    @Tag(name = "get", description = "GET methods of food APIs")
    @Operation(summary = "Get a food item",
            description = "Get a specific food from the database with the lowest number of calories")
    @GetMapping("/foodItem/minKcal")
    public FoodItemDTO getFoodItemWithMinKcal() {
        return foodService.findWithMinKcal().get();
    }


    @Tag(name = "post", description = "POST methods of food APIs")
    @Operation(summary = "Post a food item",
            description = "Post a food item by providing the api with name, category, kcal and selling price")
    @PostMapping("/foodItem")
    public ResponseEntity<FoodItemDTO> saveNewFoodItem(@Valid @RequestBody FoodItemDTO foodItemDTO) {
        Optional<FoodItemDTO> savedFoodItemDTO = foodService.saveOrUpdate(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFoodItemDTO.get());
    }


    @Tag(name = "put", description = "Put methods of food APIs")
    @Operation(summary = "Put a food item",
            description = "Update a food item by providing the api with the ID, name, category, kcal and selling price")
    @PutMapping("/foodItem/{id}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(@Valid @RequestBody FoodItemDTO foodItemDTO,
                                                      @Parameter(
                                                              description = "ID of food to be updated")@PathVariable Integer id)
    {
        if(!foodItemDTO.getId().equals(id)) {
            MDC.put("prvi", "a");
            logger.warn("Could not update food Item because id1 is not equal to id2");
            MDC.clear();
            return ResponseEntity.badRequest().build();
        }

        Optional<FoodItemDTO> updatedFoodItemDtoOptional = foodService.saveOrUpdate(foodItemDTO);

        if(updatedFoodItemDtoOptional.isEmpty()) {
            MDC.put("prvi", "a");
            logger.warn("Could not update food Item because food ItemDTO is null");
            MDC.clear();
            return ResponseEntity.noContent().build();
        }
        else {
            MDC.put("prvi", "b");
            logger.info("Update food Item because food ItemDTO is updated");
            MDC.clear();
            return ResponseEntity.status(HttpStatus.OK).body(updatedFoodItemDtoOptional.get());
        }
    }


    @Tag(name = "delete", description = "Delete methods of food APIs")
    @Operation(summary = "Delete a food item",
            description = "Delete a food item by providing the api with the ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("foodItems/{id}")
    public void delete(@Parameter(
            description = "ID of food to be deleted")@PathVariable Integer id){
        foodService.deleteFoodItem(id);
    }


}
