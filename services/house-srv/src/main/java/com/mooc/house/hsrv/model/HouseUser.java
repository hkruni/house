package com.mooc.house.hsrv.model;

import java.util.Date;

/**
 * 房屋-用户关系表
 * @author x1 carbon
 *
 */
public class HouseUser {
  private Long id;
  private Long houseId;//房产ID
  private Long userId;//用户ID
  private Date createTime;//创建时间
  private Integer type;//类型 ：1-售卖，2-收藏
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getHouseId() {
    return houseId;
  }
  public void setHouseId(Long houseId) {
    this.houseId = houseId;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
 
  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }
  
  

}
