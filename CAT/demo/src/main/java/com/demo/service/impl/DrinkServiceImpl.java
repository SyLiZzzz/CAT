package com.demo.service.impl;

import com.demo.bean.Comment;
import com.demo.bean.Drink;
import com.demo.dao.DrinkMapper;
import com.demo.em.ErrorMessageEnum;
import com.demo.service.DrinkService;
import com.demo.vo.BaseModel;
import com.demo.vo.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    DrinkMapper mapper;
    @Autowired
    DrinkService service;
    @Override
    public BaseModel selectPageDrink(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectPageInfo(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel selectById(Integer drink_id, BaseModel baseModel) throws Exception {
        Drink info =mapper.selectInfoById(drink_id);
        if (info ==null) {
            throw  new BusinessException(ErrorMessageEnum.SELECT_DRIKN_BY_ID);
        }
        baseModel.setResultCode(0);
        baseModel.setMessage("饮品信息查询成功");
        baseModel.setData(info);
        return baseModel;
    }

    @Override
    public BaseModel selectByCoffee(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectInfoByCoffee(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询咖啡分类成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel selectByTea(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectInfoByTea(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询茶分类成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel selectScoreAsc(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectScoreAsc(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("评分升序查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel selectScoreDesc(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectScoreDesc(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("评分降序查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }
    @Override
    public BaseModel selectPriceAsc(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectPriceAsc(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("价格升序查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel selectPriceDesc(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.selectPriceDesc(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("价格降序查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectDrinkInfo(String drink_name, Integer curr_page, Integer page_size, BaseModel baseModel) throws Exception {
        PageHelper.startPage(curr_page,page_size);
        List<Drink> list=mapper.AdminSelectDrinkInfo(drink_name);
        PageInfo pageInfo=new PageInfo(list,5);
        baseModel.setResultCode(0);
        baseModel.setMessage("查询成功");
        baseModel.setData(pageInfo);
        return baseModel;
    }

    @Override
    public BaseModel AdminSelectById(Integer drinkId, BaseModel baseModel) throws Exception {
        Drink info =mapper.AdminSelectInfoById(drinkId);
        if (info ==null) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("饮品信息查询失败");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("饮品信息查询成功");
            baseModel.setData(info);
        }
        return baseModel;
    }

    @Override
    public BaseModel upload(Drink drink, BaseModel baseModel) throws Exception {
        Integer result =mapper.upload(drink);
        if (result > 0) {
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增图片失败");
        } else {
            baseModel.setResultCode(0);
            baseModel.setMessage("新增图片成功");
        }
        return  baseModel;
    }

    @Override
    public BaseModel AdminAddDrink(Drink drink, BaseModel baseModel) throws Exception {
        int result = mapper.AdminAddDrink(drink);
        if (result > 0) {
            Comment com =new Comment();
            com.setCreateTime(new Date());
            com.setIsDeleted(0);
            com.setVersion(0);
            com.setScore(5);
            service.AdminAddScore(com,baseModel);
            baseModel.setResultCode(0);
            baseModel.setMessage("新增饮品成功");
        } else {
            baseModel.setResultCode(-1);
            baseModel.setMessage("新增饮品失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminDeleteDrink(Integer drinkId, Integer isDeleted, BaseModel baseModel) throws Exception {
        int result = mapper.AdminDeleteDrink(drinkId, isDeleted);
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
    public BaseModel AdminDeletedAllDrink(String ids, BaseModel baseModel) throws Exception {
        String[] idArry=ids.split(",");
        int result=mapper.AdminDeletedAllDrink(idArry);
        if(result<=0){
            baseModel.setResultCode(-1);
            baseModel.setMessage("删除"+result+"个饮品失败。");
        }else{
            baseModel.setResultCode(0);
            baseModel.setMessage("删除"+result+"个饮品成功！");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminAddScore(Comment comment, BaseModel baseModel) throws Exception {
        int result = mapper.AdminAddScore(comment);
        if (result > 0) {
            baseModel.setResultCode(0);
        } else {
            baseModel.setResultCode(-1);
        }
        return baseModel;
    }

    @Override
    public BaseModel ChangeScore(Integer commentId, String score, BaseModel baseModel) throws Exception {
        int result = mapper.ChangeScore(commentId, score);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改评分成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改评分失败");
        }
        return baseModel;
    }

    @Override
    public BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception {
        int result = mapper.AdminUpdateDrink(drink);
        if (result > 0) {
            baseModel.setResultCode(0);
            baseModel.setMessage("修改信息成功");
        }else{
            baseModel.setResultCode(-1);
            baseModel.setMessage("修改信息失败");
        }
        return baseModel;
    }
}
