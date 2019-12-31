package com.demo.dao;

import com.demo.bean.Comment;
import com.demo.bean.Drink;

import java.util.List;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

public interface DrinkMapper {
    //分页查询饮品
    List<Drink> selectPageInfo(@Param("drink_name")String drink_name);
    //根据id查询需要的信息
    Drink selectInfoById (@Param("drink_id")Integer drink_id);
    //查询咖啡分类
    List<Drink> selectInfoByCoffee(@Param("drink_name")String drink_name);
    //查询茶分类
    List<Drink> selectInfoByTea(@Param("drink_name")String drink_name);
    //评分升序
    List<Drink> selectScoreAsc(@Param("drink_name")String drink_name);
    //评分降序
    List<Drink> selectScoreDesc(@Param("drink_name")String drink_name);
    //价格升序
    List<Drink> selectPriceAsc(@Param("drink_name")String drink_name);
    //价格降序
    List<Drink> selectPriceDesc(@Param("drink_name")String drink_name);
    // 查询所有饮品信息
    List<Drink> AdminSelectDrinkInfo(@Param("drink_name")String drink_name);
    Drink AdminSelectInfoById(@Param("drinkId")Integer drink_id);
    Integer AdminDeleteDrink(@Param("drinkId") Integer drinkId,@Param("isDeleted")Integer isDeleted);
    Integer AdminDeletedAllDrink(@Param("ids") String[] ids);
    Integer AdminAddScore(Comment comment);
    Integer ChangeScore(@Param("commentId") Integer commentId,@Param("score")String score);
    // 图片上传
    Integer upload (Drink drink);
    // 新增
    Integer AdminAddDrink(Drink drink);
    Integer AdminUpdateDrink(Drink drink);
}