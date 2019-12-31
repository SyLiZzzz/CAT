package com.demo.dao;

import com.demo.bean.Address;

import java.util.List;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Param;

public interface AddressMapper {
    List<Address> selectAllById(@Param("userId")Integer userId);
    Integer insertNewAddress(Address address);
    Integer deletedById(@Param("addressId") Integer addressId);
    List<Address> AdminSelectAll (@Param("name")String name);
    Integer AdminAddAddress(Address address);
    Address AdminSelectAddressById(@Param("addressId")Integer addressId);
    Integer AdminUpdateAddress(Address address);
    Integer AdminDeleteAddress(@Param("addressId") Integer addressId,@Param("isDeleted")Integer isDeleted);
    Integer AdminDeletedAllAddress(@Param("ids") String[] ids);
}