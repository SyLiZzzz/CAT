package com.demo.baseController;

import com.demo.em.ErrorMessageEnum;
import com.demo.vo.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BaseController{
    /**
     * @title:<h3> 统一处理业务异常 <h3>
     * @author: Enzo
     * @date: 2018-11-13 14:50
     * @params [ex]
     * @return com.dc.base.exception.BusinessException
     **/
    @ExceptionHandler({BusinessException.class})
    public BusinessException businessException(BusinessException ex){
        return ex;
    }
    /**
     * @title:<h3> 系统异常 <h3>
     * @author: Enzo
     * @date: 2018-11-13 14:50
     * @params [ex]
     * @return com.dc.base.exception.BusinessException
     **/
    @ExceptionHandler({Exception.class})
    public BusinessException exception(Exception ex){
        BusinessException businessException = new BusinessException(ErrorMessageEnum.SYS_ERROR);
        return businessException;
    }
}
