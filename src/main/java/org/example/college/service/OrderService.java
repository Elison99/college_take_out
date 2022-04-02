package org.example.college.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.college.entity.Orders;



public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
