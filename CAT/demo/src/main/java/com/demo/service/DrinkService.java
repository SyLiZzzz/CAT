package com.demo.service;

import com.demo.bean.Comment;
import com.demo.bean.Drink;
import com.demo.bean.User;
import com.demo.vo.BaseModel;
import org.springframework.web.multipart.MultipartFile;

public interface DrinkService {
    BaseModel selectPageDrink(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectById(Integer drink_id,BaseModel baseModel)throws Exception;
    BaseModel selectByCoffee(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectByTea(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectScoreAsc(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectScoreDesc(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectPriceAsc(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel selectPriceDesc(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminSelectDrinkInfo(String drink_name, Integer curr_page,Integer page_size,BaseModel baseModel)throws Exception;
    BaseModel AdminSelectById(Integer drinkId,BaseModel baseModel)throws Exception;
    BaseModel upload (Drink drink, BaseModel baseModel)throws Exception;
    BaseModel AdminAddDrink(Drink drink, BaseModel baseModel)throws  Exception;
    BaseModel AdminDeleteDrink(Integer drinkId,Integer isDeleted,BaseModel baseModel) throws Exception;
    BaseModel AdminDeletedAllDrink(String ids,BaseModel baseModel)throws Exception;
    BaseModel AdminAddScore(Comment comment, BaseModel baseModel)throws  Exception;
    BaseModel ChangeScore(Integer commentId,String score,BaseModel baseModel) throws Exception;
    BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception;
}
