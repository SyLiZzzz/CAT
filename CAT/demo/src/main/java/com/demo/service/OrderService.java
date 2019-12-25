package com.demo.service;

import com.demo.bean.Order;
import com.demo.vo.BaseModel;

public interface OrderService {
    BaseModel insertOrder(Order order, BaseModel baseModel) throws Exception;
    BaseModel selectOrById(Integer userId, BaseModel baseModel) throws  Exception;
    BaseModel deletedOrderById(Integer orderId,BaseModel baseModel) throws Exception;
    BaseModel AdminSelectAll(String user_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminChangeState(Integer order_id,Integer state,BaseModel baseModel) throws Exception;
    BaseModel AdminChangDeleted(Integer order_id,Integer is_deleted,BaseModel baseModel) throws Exception;
    BaseModel AdmindeleteAll(String ids,BaseModel baseModel)throws Exception;
    BaseModel AdminChangeStateAll(String ids,BaseModel baseModel)throws Exception;
}
