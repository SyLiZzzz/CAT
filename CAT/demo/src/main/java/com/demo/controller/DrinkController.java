package com.demo.controller;

import com.demo.bean.Comment;
import com.demo.bean.Drink;
import com.demo.bean.User;
import com.demo.service.DrinkService;
import com.demo.vo.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/Drink")
public class DrinkController {
    @Autowired
    DrinkService service;
    @RequestMapping(value = "/selectPageDrink",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPageDrink(String drink_name,int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.selectPageDrink(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectCoffee", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectByCoffee(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectByCoffee(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectTea", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectByTea(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectByTea(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectScoreAsc", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectScoreAsc(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectScoreAsc(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectScoreDesc", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectScoreDesc(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectScoreDesc(drink_name,curr_page,page_size,baseModel);
    }
    @RequestMapping(value = "/selectPriceAsc", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPriceAsc(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectPriceAsc(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectPriceDesc", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectPriceDesc(String drink_name,int curr_page, int page_size, BaseModel baseModel)throws Exception {
        return service.selectPriceDesc(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel selectInfoById(Integer drink_id, BaseModel baseModel)throws Exception {
        return service.selectById(drink_id, baseModel);
    }

    @RequestMapping(value = "/AdminSelectDrink",method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectDrinkInfo(String drink_name,int curr_page, int page_size, BaseModel baseModel) throws Exception {
        return service.AdminSelectDrinkInfo(drink_name,curr_page,page_size,baseModel);
    }

    @RequestMapping(value = "/AdminSelectById", method = RequestMethod.GET)
    @ResponseBody
    public BaseModel AdminSelectById(Integer drinkId, BaseModel baseModel)throws Exception {
        return service.AdminSelectById(drinkId, baseModel);
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel upload(@RequestParam("file") MultipartFile file, Drink drink, HttpServletRequest request, BaseModel baseModel) throws Exception {
       // String path = request.getSession().getServletContext().getRealPath("/upload");
        String path = "D:/tool/Vue/实训项目/Project/static/upload";
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFilename = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFilename);

        //创建新名字
        String newFileName = UUID.randomUUID()+originalFilename;
        //在指定路径下创建一个文件
        File targetFile = new File(path, newFileName);
        file.transferTo(targetFile);
        drink.setDrinkPicture(newFileName);
        return service.upload(drink, baseModel);
    }

    @RequestMapping(value = "/AdminAddDrink",method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminAddDrink(Drink drink, BaseModel baseModel) throws Exception {
        drink.setCreateTimesd(new Date());
        drink.setIsDeleted(0);
        drink.setVersion(0);
        return service.AdminAddDrink(drink, baseModel);
    }

    @RequestMapping(value = "/AdminDeleteDrink", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeleteDrink(Integer drinkId, Integer isDeleted, BaseModel baseModel) throws Exception {
        Drink drink = new Drink();
        if (isDeleted.equals(0)) {
            drink.setIsDeleted(1);
        }
        if (isDeleted.equals(1)) {
            drink.setIsDeleted(0);
        }
        return service.AdminDeleteDrink(drinkId, isDeleted, baseModel);
    }

    @RequestMapping(value = "/deleteDrink/{ids}", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminDeletedAllDrink(@PathVariable(value = "ids") String ids,BaseModel baseModel) throws Exception {
        return service.AdminDeletedAllDrink(ids, baseModel);
    }

    @RequestMapping(value = "/ChangeScore", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel ChangeScore(Integer commentId, String score, BaseModel baseModel) throws Exception {
        return service.ChangeScore(commentId, score, baseModel);
    }

    @RequestMapping(value = "/AdminUpdateDrink", method = RequestMethod.POST)
    @ResponseBody
    public BaseModel AdminUpdateDrink(Drink drink, BaseModel baseModel) throws Exception {
        drink.setUpdateTime(new Date());
        drink.setIsDeleted(0);
        return service.AdminUpdateDrink(drink, baseModel);
    }
}
