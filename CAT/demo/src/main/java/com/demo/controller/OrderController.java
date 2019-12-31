package com.demo.controller;

import com.demo.bean.Order;
import com.demo.service.OrderService;
import com.demo.vo.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    OrderService service;

    @RequestMapping(value = "/insertOrder",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel insertOrder(Order order, BaseModel baseModel) throws Exception{
        order.setCreateTime(new Date());
        order.setState(0);
        order.setVersion(0);
        order.setIsDeleted(0);
        return service.insertOrder(order,baseModel);
    }

    @RequestMapping(value = "/selectOrById",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectOrById(Integer userId, BaseModel baseModel) throws Exception{
        return service.selectOrById(userId, baseModel);
    }

    @RequestMapping(value = "/deleteOrderById",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteOrderById(Integer orderId, BaseModel baseModel) throws Exception{
        return service.deletedOrderById(orderId, baseModel);
    }

    @RequestMapping(value = "/AdminSelectAll",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectAll(String user_name, int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectAll(user_name, curr_page, page_size, baseModel);
    }

    @RequestMapping(value = "/AdminChangeState", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeleteUser(Integer order_id, Integer state, BaseModel baseModel) throws Exception {
        Order order = new Order();
        if (state.equals(0)) {
            order.setState(1);
        }
        if (state.equals(1)) {
            order.setState(0);
        }
        return service.AdminChangeState(order_id,state,baseModel);
    }

    @RequestMapping(value = "/AdminChangDeleted", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminChangDeleted(Integer order_id, Integer is_deleted, BaseModel baseModel) throws Exception {
        Order order = new Order();
        if (is_deleted.equals(0)) {
            order.setIsDeleted(1);
        }
        if (is_deleted.equals(1)) {
            order.setIsDeleted(0);
        }
        return service.AdminChangDeleted(order_id, is_deleted, baseModel);
    }

    @RequestMapping(value = "/AdmindeleteAll/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteAll(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdmindeleteAll(ids, baseModel);
    }

    @RequestMapping(value = "/ChangeAllState/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel ChangeAllState(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdminChangeStateAll(ids, baseModel);
    }
}
