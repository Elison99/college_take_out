package org.example.college.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.college.entity.Category;

public interface CategoryService extends IService<Category> {
    //根据id删除
    public void remove(Long id);
}
