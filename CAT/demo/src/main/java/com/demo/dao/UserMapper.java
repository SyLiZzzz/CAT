package com.demo.dao;

import com.demo.bean.Drink;
import com.demo.bean.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    Integer addUser(User user);
    User loginByAccount(@Param("account") String account,@Param("password") String password);
    User selectUserById(@Param("userId")Integer userId);
    Integer updateUser(User user);
    User loginAdmin(@Param("phone")String phone,@Param("password")String password);
    List<User> selectPageInfo(@Param("account")String account);
    Integer AdminAddUser(User user);
    User AdminSelectUserById(@Param("userId")Integer userId);
    Integer AdminUpdateUser(User user);
    Integer AdminDeleteUser(@Param("userId") Integer userId,@Param("isDeleted")Integer isDeleted);
    Integer AdminDeletedAllUser(@Param("ids") String[] ids);
    User loginByPhone(@Param("phone") String phone);
}