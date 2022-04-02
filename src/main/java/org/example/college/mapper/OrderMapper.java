package org.example.college.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.college.entity.Orders;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
