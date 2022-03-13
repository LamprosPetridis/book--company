package com.bookcompany.builder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe extends Entity {
    private String name; //Name of recipe
    private Integer time; // Preparation time in minutes
    private List<Ingredient> ingredientList = new ArrayList<>();



}
