package com.demo.service.impl;

import com.demo.bean.Car;
import com.demo.dao.CarMapper;
import com.demo.em.ErrorMessageEnum;
import com.demo.service.CarService;
import com.demo.vo.BaseModel;
import com.demo.vo.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarMapper mapper;

    @Override
    public BaseModel DrinktoCar(Car car, BaseModel baseModel) throws Exception {
        int result = mapper.addtoCar(car);
        if (result <=0) {
            baseModel.setResultCode(1016);
            baseModel.setMessage("添入购物车失败");
        }else {
            baseModel.setResultCode(0);
            baseModel.setMessage("添入购物车成功");
        }
        return  baseModel;
    }

    @Override
    public BaseModel selectCarById(Integer userId, BaseModel baseModel) throws Exception {
        List list =mapper.selectCarById(userId);
        if (list ==null) {
            baseModel.setResultCode(1017);
            baseModel.setMessage("购物车查询失败");
        }else {
            baseModel.setResultCode(0);
            baseModel.setMessage("购物车查询成功");
            baseModel.setData(list);
        }
        return baseModel;
    }

    @Override
    public BaseModel deletedById(Integer carId, BaseModel baseModel) throws Exception {
        int result = mapper.deletedById(carId);
        if(result <= 0){
            baseModel.setResultCode(1018);
            baseModel.setMessage("删除失败");
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("删除成功");
        return baseModel;
    }

    @Override
    public BaseModel deleteCar(Integer userId, BaseModel baseModel) throws Exception {
        int result = mapper.deleteCar(userId);
        if(result <= 0){
            baseModel.setResultCode(1018);
            baseModel.setMessage("删除失败");
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("删除成功");
        return baseModel;
    }
}
