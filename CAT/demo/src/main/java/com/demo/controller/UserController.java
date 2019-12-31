package com.demo.controller;

import com.demo.bean.User;
import com.demo.em.ErrorMessageEnum;
import com.demo.service.UserService;
import com.demo.vo.BaseModel;
import com.demo.vo.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/loginByAP", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel loginByAP(String account, String phone, BaseModel baseModel) throws Exception {
        return service.loginByAP(account, phone, baseModel);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel addUser(User user, BaseModel baseModel) throws Exception {
        user.setCreateTime(new Date());
        user.setVersion(0);
        user.setIsDeleted(0);
        user.setUserRoleId(2);
        return service.addUser(user, baseModel);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel login(String account, String password, BaseModel baseModel, HttpServletRequest request) throws Exception {
        baseModel = service.loginByAccount(account, password, baseModel);
        return baseModel;
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectById(Integer userId, BaseModel baseModel) throws Exception {
        return service.selectById(userId, baseModel);
    }

    @RequestMapping(value = "/updateuUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel updateuUser(User user, BaseModel baseModel) throws Exception {
        user.setUpdateTime(new Date());
        return service.updateUser(user, baseModel);
    }

    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel loginAdmin(String phone, String password, BaseModel baseModel) throws Exception {
        return service.loginAdmin(phone, password, baseModel);
    }

    @RequestMapping(value = "/selectPageUser", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPageUser(String account, int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.selectPageUser(account, curr_page, page_size, baseModel);
    }

    @RequestMapping(value = "/AdminAddUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminAddUser(User user, BaseModel baseModel) throws Exception {
        user.setCreateTime(new Date());
        user.setVersion(0);
        user.setIsDeleted(0);
        return service.AdminAddUser(user, baseModel);
    }

    @RequestMapping(value = "/AdminSelectById", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectUserById(Integer userId, BaseModel baseModel) throws Exception {
        return service.AdminSelectById(userId, baseModel);
    }

    @RequestMapping(value = "/AdminUpdateUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminUpdateUser(User user, BaseModel baseModel) throws Exception {
        user.setUpdateTime(new Date());
        user.setIsDeleted(0);
        return service.AdminUpdateUser(user, baseModel);
    }

    @RequestMapping(value = "/AdminDeleteUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeleteUser(Integer userId, Integer isDeleted, BaseModel baseModel) throws Exception {
        User user = new User();
        if (isDeleted.equals(0)) {
            user.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            user.setIsDeleted(0);
        }
        return service.AdminDeleteUser(userId, isDeleted, baseModel);
    }

    @RequestMapping(value = "/deleteUser/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deleteAllUser(@PathVariable(value = "ids") String ids,BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllUser(ids, baseModel);
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel login2(String phone, BaseModel baseModel) throws Exception {
        baseModel = service.loginByPhone(phone,baseModel);
        return baseModel;
    }
}
