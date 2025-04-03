package hr.java.spring.foodstore.foodstore.model;

import hr.java.spring.foodstore.foodstore.dto.FoodItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FoodItem {
    private Integer id;
    private String name;
    private FoodCategory foodCategory;
    private Integer kcal;
    private BigDecimal sellingPrice;
    private BigDecimal initialPrice;

    public FoodItem(Integer id, String name, FoodCategory foodCategory, Integer kcal, BigDecimal sellingPrice) {
        this.id = id;
        this.name = name;
        this.foodCategory = foodCategory;
        this.kcal = kcal;
        this.sellingPrice = sellingPrice;
        this.initialPrice = null;
    }
}

