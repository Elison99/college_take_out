package org.example.college.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.college.dto.DishDto;
import org.example.college.entity.Dish;
import org.springframework.stereotype.Service;


public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    //根据id查询对应飞菜品信息和对应的口味系信息
    public DishDto getByIdWithFlavor(Long id);

    //跟新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);
}
