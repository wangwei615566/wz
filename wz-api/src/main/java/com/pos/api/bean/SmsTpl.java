package com.pos.api.bean;


import java.io.Serializable;


 public class SmsTpl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 
    */
    private String type;

    /**
    * 
    */
    private String typeName;

    /**
    * 
    */
    private String tpl;

    /**
    * 模板编号
    */
    private String number;

    private String state;

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
    return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getType(){
    return type;
    }

    /**
    * 设置
    * 
    * @param type 要设置的
    */
    public void setType(String type){
    this.type = type;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getTypeName(){
    return typeName;
    }

    /**
    * 设置
    * 
    * @param typeName 要设置的
    */
    public void setTypeName(String typeName){
    this.typeName = typeName;
    }

    /**
    * 获取
    *
    * @return 
    */
    public String getTpl(){
    return tpl;
    }

    /**
    * 设置
    * 
    * @param tpl 要设置的
    */
    public void setTpl(String tpl){
    this.tpl = tpl;
    }

    /**
    * 获取模板编号
    *
    * @return 模板编号
    */
    public String getNumber(){
    return number;
    }

    /**
    * 设置模板编号
    * 
    * @param number 要设置的模板编号
    */
    public void setNumber(String number){
    this.number = number;
    }

}