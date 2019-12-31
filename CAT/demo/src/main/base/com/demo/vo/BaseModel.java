package com.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

//返回统一格式的数据给前端
public class BaseModel {
//    状态码
    private Integer resultCode;
//    返回的消息
    private String message;
//    返回的最终数据
    private Object data;
//    文件上传
    private MultipartFile[] tempMFile;
//    文件地址属性
    private String filesArray;

    public MultipartFile[] getTempMFile() {
        return tempMFile;
    }

    public void setTempMFile(MultipartFile[] tempMFile) {
        this.tempMFile = tempMFile;
    }

    public String getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(String filesArray) {
        this.filesArray = filesArray;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseModel{");
        sb.append("resultCode=").append(resultCode);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append(", tempMFile=").append(tempMFile == null ? "null" : Arrays.asList(tempMFile).toString());
        sb.append(", filesArray='").append(filesArray).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
