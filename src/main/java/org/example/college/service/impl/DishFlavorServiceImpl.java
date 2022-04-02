package org.example.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.college.entity.DishFlavor;
import org.example.college.mapper.DishFlavorMapper;
import org.example.college.service.DishFlavorService;
import org.example.college.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
