package com.xunfang.bdpf.mllib.enums;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: FillMissingValuesEnum
 * @Description: 缺失值填充原值枚举类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public enum FillMissingValuesOriginEnum {

    NULL("Null(数值和string)",1),
    EMPTY("空字符(string)",2),
    NULL_EMPTY("Null和空字符(string)",3),
    CUSTOM("自定义(string)",4);


    private String name;
    private int index;

    public static  String getName(int index){
        for (FillMissingValuesOriginEnum e : FillMissingValuesOriginEnum.values()){
            if (e.getIndex() == index)
                return e.name;
        }
        return "";
    }

    FillMissingValuesOriginEnum(String name,int index){
        this.name=name;
        this.index=index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
