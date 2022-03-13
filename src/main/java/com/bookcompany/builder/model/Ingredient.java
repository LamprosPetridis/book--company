package com.bookcompany.builder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient extends Entity {
    private String name; //Name of ingredient
    private double cost; //Cost of unit
    private Integer calories; //Calories per unit
    private Character vegetarian; // 'v' for vegeterian, 'n' otherwise

}
