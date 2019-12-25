package com.demo.service.impl;

import com.demo.bean.Order;
import com.demo.bean.User;
import com.demo.dao.OrderMapper;
import com.demo.service.OrderService;
import com.demo.vo.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper mapper;
    @Override
    public BaseModel insertOrder(Order order, BaseModel baseModel) throws Exception {
        int result =mapper.insertOrder(order);
        if (result >0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("支付成功");
        }else{
            baseModel.setResultCode(1021);
            baseModel.setMessage("支付失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel selectOrById(Integer userId, BaseModel baseModel) throws Exception {
        List list =mapper.selectOrById(userId);
        if (list ==null) {
            baseModel.setResultCode(1017);
            baseModel.setMessage("订单信息查询失败");
        }else {
            baseModel.setResultCode(0);
            baseModel.setMessage("订单信息查询成功");
            baseModel.setData(list);
        }
        return baseModel;
    }

    @Override
    public BaseModel deletedOrderById(Integer orderId, BaseModel baseModel) throws Exception {
        int result =mapper.deletedOrderById(orderId);
        if (result >0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("删除成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectAll(String user_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Order> list=mapper.AdminSelectAll(user_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminChangeState(Integer order_id, Integer state, BaseModel baseModel) throws Exception {
        int result = mapper.AdminChangeState(order_id, state);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("改变成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("改变失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminChangDeleted(Integer order_id, Integer is_deleted, BaseModel baseModel) throws Exception {
        int result = mapper.AdminChangDeleted(order_id, is_deleted);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("改变成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("改变失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdmindeleteAll(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=mapper.AdmindeleteAll(idArry);
        if(result<=0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除"+result+"个订单失败。");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("删除"+result+"个订单成功！");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminChangeStateAll(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=mapper.ChangeStateAll(idArry);
        if(result<=0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("改变"+result+"个订单失败。");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("改变"+result+"个订单成功！");
        }
        return baseModel;
    }
}
