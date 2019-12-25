package com.demo.controller;

import com.demo.bean.Car;
import com.demo.bean.User;
import com.demo.em.ErrorMessageEnum;
import com.demo.service.CarService;
import com.demo.vo.BaseModel;
import com.demo.vo.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/Car")
public class CarController {
    @Autowired
    CarService service;

    @RequestMapping(value = "/addtoCar",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel DrinktoCar(Car car, BaseModel baseModel) throws Exception{
        car.setCreateTime(new Date());
        car.setVersion(0);
        car.setIsDeleted(0);
        return service.DrinktoCar(car,baseModel);
    }

    @RequestMapping(value = "/selctCarById",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel SelectCarById(Integer userId,BaseModel baseModel) throws  Exception{
        return service.selectCarById(userId, baseModel);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deletedUser(Integer carId, BaseModel baseModel) throws Exception{
        return service.deletedById(carId,baseModel);
    }

    @RequestMapping(value = "/deleteCar",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteCar(Integer userId, BaseModel baseModel) throws Exception{
        return service.deleteCar(userId,baseModel);
    }
}
