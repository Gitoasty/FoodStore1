package hr.java.spring.foodstore.foodstore.dto;

import hr.java.spring.foodstore.foodstore.model.FoodCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
    private Integer id;
    private String name;
    private FoodCategory foodCategory;
    private Integer kcal;

    @NotNull(message = "The selling price for the food item must be entered")
    private BigDecimal sellingPrice;
}
