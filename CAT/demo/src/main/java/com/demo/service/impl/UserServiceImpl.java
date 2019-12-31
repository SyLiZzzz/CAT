package com.demo.service.impl;

import com.demo.bean.Drink;
import com.demo.bean.User;
import com.demo.dao.UserMapper;
import com.demo.em.ErrorMessageEnum;
import com.demo.service.UserService;
import com.demo.vo.BaseModel;
import com.demo.vo.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public BaseModel addUser(User user, BaseModel baseModel) throws Exception {
        int result = mapper.addUser(user);
        if (result <= 0) {
            throw new BusinessException(ErrorMessageEnum.ADD_USER_ERROR);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("恭喜你，注册成功！赶快登陆吧！ε=(´ο｀*)))");
        return baseModel;
    }

    @Override
    public BaseModel loginByAccount(String account, String password, BaseModel baseModel) throws Exception {
        User user =mapper.loginByAccount(account,password);
        if (user == null) {
            baseModel.setResultCode(1003);
            baseModel.setMessage("账号格式不正确，或者密码错误!");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("恭喜你，登陆成功！赶快下单吧！ε=(´ο｀*)))");
            baseModel.setData(user);
        }
        return baseModel;
    }

    @Override
    public BaseModel selectById(Integer userId, BaseModel baseModel) throws Exception {
        User user =mapper.selectUserById(userId);
        if (user == null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("查询失败");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("查询成功");
            baseModel.setData(user);
        }
        return baseModel;
    }

    @Override
    public BaseModel updateUser(User user, BaseModel baseModel) throws Exception {
        int result = mapper.updateUser(user);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改个人信息成功,请重新登录");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改个人信息失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel loginAdmin(String phone, String password, BaseModel baseModel) throws Exception {
        User user =mapper.loginAdmin(phone, password);
        if (user == null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("账号格式不正确，或者密码错误!");
        } else {
            baseModel.setResultCode(0);
            baseModel.setMessage("登录成功,欢迎您："+user.getAccount());
            baseModel.setData(user);
        }
        return baseModel;
    }

    @Override
    public BaseModel selectPageUser(String account, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<User> list=mapper.selectPageInfo(account);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception {
        int result = mapper.AdminAddUser(user);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("新增用户成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增用户失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectById(Integer userId, BaseModel baseModel) throws Exception {
        User user =mapper.AdminSelectUserById(userId);
        if (user == null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("查询失败");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("查询成功");
            baseModel.setData(user);
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception {
        int result = mapper.AdminUpdateUser(user);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改信息成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改信息失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminDeleteUser(Integer userId, Integer isDeleted,BaseModel baseModel) throws Exception {
        int result = mapper.AdminDeleteUser(userId, isDeleted);
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
    public BaseModel AdminDeletedAllUser(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=mapper.AdminDeletedAllUser(idArry);
        if(result<=0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除"+result+"个用户失败。");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("删除"+result+"个用户成功！");
        }
        return baseModel;
    }

    @Override
    public BaseModel loginByPhone(String phone, BaseModel baseModel) throws Exception {
        User user =mapper.loginByPhone(phone);
        if (user == null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("账号格式不正确，或者验证码错误!");
        } else {
            baseModel.setResultCode(0);
            baseModel.setMessage("登录成功,欢迎您："+user.getAccount());
            baseModel.setData(user);
        }
        return baseModel;
    }

    @Override
    public BaseModel loginByAP(String account, String phone, BaseModel baseModel) throws Exception {
        User user =mapper.loginByAP(account,phone);
        if (user !=null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("已有相同的信息");
        } else {
            baseModel.setResultCode(0);
            baseModel.setMessage("信息通过");
            baseModel.setData(user);
        }
        return baseModel;
    }
}
