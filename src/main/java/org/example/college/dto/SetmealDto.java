package org.example.college.dto;

import lombok.Data;
import org.example.college.entity.Setmeal;
import org.example.college.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes; //套餐关联菜品集合

    private String categoryName; //分类名称
}
