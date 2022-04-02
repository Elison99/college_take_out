package org.example.college.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.college.dto.SetmealDto;
import org.example.college.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
