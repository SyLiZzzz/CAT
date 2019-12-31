package com.demo.service;


import com.demo.bean.Car;
import com.demo.vo.BaseModel;

public interface CarService {
    BaseModel DrinktoCar(Car car, BaseModel baseModel) throws Exception;
    BaseModel selectCarById(Integer userId, BaseModel baseModel) throws  Exception;
    BaseModel deletedById(Integer carId,BaseModel baseModel) throws Exception;
    BaseModel deleteCar(Integer userId,BaseModel baseModel) throws Exception;
}
