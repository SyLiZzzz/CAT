package com.demo.controller;

import com.demo.bean.Address;
import com.demo.bean.User;
import com.demo.service.AddressService;
import com.demo.vo.BaseModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    AddressService service;

    @RequestMapping(value = "/selectAllById",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectAll(@Param("userId")Integer userId, BaseModel baseModel) throws  Exception{
        return service.selectAllById(userId, baseModel);
    }

    @RequestMapping(value = "/insertAddress",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel insertNewAddress(Address address,BaseModel baseModel)throws Exception{
        address.setCreateTime(new Date());
        address.setIsDeleted(0);
        address.setVersion(0);
        return service.insertNewAddress(address, baseModel);
    }
    @RequestMapping(value = "/deleteAddById",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel deletedUser(Integer addressId, BaseModel baseModel) throws Exception{
        return service.deletedById(addressId, baseModel);
    }

    @RequestMapping(value = "/AdminSelectAll", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectAll(String name, int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectAll(name, curr_page, page_size, baseModel);
    }

    @RequestMapping(value = "/AdminAddAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception {
        address.setCreateTime(new Date());
        address.setVersion(0);
        address.setIsDeleted(0);
        return service.AdminAddAddress(address, baseModel);
    }

    @RequestMapping(value = "/AdminSelectAddressById", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws Exception {
        return service.AdminSelectAddressById(addressId, baseModel);
    }

    @RequestMapping(value = "/AdminUpdateAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception {
        address.setUpdateTime(new Date());
        address.setIsDeleted(0);
        return service.AdminUpdateAddress(address, baseModel);
    }

    @RequestMapping(value = "/AdminDeleteAddress", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeleteAddress(Integer addressId, Integer isDeleted, BaseModel baseModel) throws Exception {
        Address address = new Address();
        if (isDeleted.equals(0)) {
            address.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            address.setIsDeleted(0);
        }
        return service.AdminDeleteAddress(addressId, isDeleted, baseModel);
    }

    @RequestMapping(value = "/deleteAddress/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeletedAllAddress(@PathVariable(value = "ids") String ids, BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllAddress(ids, baseModel);
    }
}
