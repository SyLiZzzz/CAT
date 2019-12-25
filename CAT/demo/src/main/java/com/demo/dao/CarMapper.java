package com.demo.dao;

import com.demo.bean.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
        Integer addtoCar(Car car);
        List<Car> selectCarById(@Param("userId")Integer userId);
        Integer deletedById(@Param("carId") Integer carId);
        Integer deleteCar(@Param("userId")Integer userId);
}