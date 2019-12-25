package com.demo.bean;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.math.BigDecimal;
import java.util.Date;

public class Drink {
    private Integer drinkId;

    private String drinkName;

    private String drinkPicture;

    private BigDecimal price;

    private String explain;

    private Integer parentId;

    private Date createTimesd;

    private Date updateTime;

    private Integer isDeleted;

    private Integer version;

    private Integer score;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Drink{");
        sb.append("drinkId=").append(drinkId);
        sb.append(", drinkName='").append(drinkName).append('\'');
        sb.append(", drinkPicture='").append(drinkPicture).append('\'');
        sb.append(", price=").append(price);
        sb.append(", explain='").append(explain).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", createTimesd=").append(createTimesd);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", version=").append(version);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkPicture() {
        return drinkPicture;
    }

    public void setDrinkPicture(String drinkPicture) {
        this.drinkPicture = drinkPicture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTimesd() {
        return createTimesd;
    }

    public void setCreateTimesd(Date createTimesd) {
        this.createTimesd = createTimesd;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}