package com.demo.service.impl;

import com.demo.bean.Address;
import com.demo.bean.User;
import com.demo.dao.AddressMapper;
import com.demo.service.AddressService;
import com.demo.vo.BaseModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper mapper;
    @Override
    public BaseModel selectAllById(Integer userId,BaseModel baseModel) throws Exception {
        List<Address> list =mapper.selectAllById(userId);
        if (list == null) {
            baseModel.setResultCode(1019);
            baseModel.setMessage("查询地址失败");
        } else {
            baseModel.setResultCode(0);
            baseModel.setMessage("查询地址成功");
            baseModel.setData(list);
        }
        return baseModel;
    }

    @Override
    public BaseModel insertNewAddress(Address address, BaseModel baseModel) throws Exception {
        int reuslt =mapper.insertNewAddress(address);
        if (reuslt>0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("新增地址成功");
        }else{
            baseModel.setResultCode(1020);
            baseModel.setMessage("新增地址失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel deletedById(Integer addressId, BaseModel baseModel) throws Exception {
        int result = mapper.deletedById(addressId);
        if(result <= 0){
            baseModel.setResultCode(1018);
            baseModel.setMessage("删除失败");
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("删除成功");
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectAll(String name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Address> list=mapper.AdminSelectAll(name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception {
        int result = mapper.AdminAddAddress(address);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("新增地址成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增地址失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws Exception {
        Address address =mapper.AdminSelectAddressById(addressId);
        if (address == null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("查询失败");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("查询成功");
            baseModel.setData(address);
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception {
        int result = mapper.AdminUpdateAddress(address);
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
    public BaseModel AdminDeleteAddress(Integer addressId, Integer isDeleted, BaseModel baseModel) throws Exception {
        int result = mapper.AdminDeleteAddress(addressId, isDeleted);
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
    public BaseModel AdminDeletedAllAddress(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=mapper.AdminDeletedAllAddress(idArry);
        if(result<=0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除"+result+"个地址失败。");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("删除"+result+"个地址成功！");
        }
        return baseModel;
    }
}
