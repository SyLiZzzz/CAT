package com.demo.service;

import com.demo.bean.User;
import com.demo.vo.BaseModel;

public interface UserService {
    BaseModel addUser(User user, BaseModel baseModel) throws Exception;
    BaseModel loginByAccount(String account, String password, BaseModel baseModel) throws  Exception;
    BaseModel selectById(Integer userId, BaseModel baseModel) throws  Exception;
    BaseModel updateUser(User user, BaseModel baseModel) throws Exception;
    BaseModel loginAdmin(String phone, String password, BaseModel baseModel) throws  Exception;
    BaseModel selectPageUser(String account, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception;
    BaseModel AdminSelectById(Integer userId, BaseModel baseModel) throws  Exception;
    BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception;
    BaseModel AdminDeleteUser(Integer userId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllUser(String ids,BaseModel baseModel)throws Exception;
    BaseModel loginByPhone(String phone,BaseModel baseModel) throws  Exception;
}
