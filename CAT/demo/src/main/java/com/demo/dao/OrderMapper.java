package com.demo.dao;

import com.demo.bean.Car;
import com.demo.bean.Order;

import java.util.List;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

public interface OrderMapper {
    Integer insertOrder(Order order);
    List<Order> selectOrById(@Param("userId")Integer userId);
    Integer deletedOrderById(@Param("orderId") Integer orderId);
    //查询所有信息
    List<Order> AdminSelectAll(@Param("user_name")String userName);
    Integer AdminChangeState(@Param("order_id") Integer order_id,@Param("state")Integer state);
    Integer AdminChangDeleted(@Param("order_id") Integer order_id,@Param("is_deleted")Integer is_deleted);
    Integer AdmindeleteAll(@Param("ids") String[] ids);
    Integer ChangeStateAll(@Param("ids") String[] ids);
}