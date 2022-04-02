package org.example.college.dto;

import lombok.Data;
import org.example.college.entity.Dish;
import org.example.college.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
