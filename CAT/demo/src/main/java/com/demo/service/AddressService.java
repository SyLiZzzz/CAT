package com.demo.service;

import com.demo.bean.Address;
import com.demo.bean.User;
import com.demo.vo.BaseModel;

public interface AddressService {
    BaseModel selectAllById(Integer userId,BaseModel baseModel)throws Exception;
    BaseModel insertNewAddress(Address address,BaseModel baseModel)throws  Exception;
    BaseModel deletedById(Integer addressId,BaseModel baseModel) throws Exception;
    BaseModel AdminSelectAll(String name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminAddAddress(Address address, BaseModel baseModel) throws Exception;
    BaseModel AdminSelectAddressById(Integer addressId, BaseModel baseModel) throws  Exception;
    BaseModel AdminUpdateAddress(Address address, BaseModel baseModel) throws Exception;
    BaseModel AdminDeleteAddress(Integer addressId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllAddress(String ids,BaseModel baseModel)throws Exception;
}
