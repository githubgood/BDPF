package com.xunfang.bdpf.mllib.enums;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: FillMissingValuesReplaceEnum
 * @Description: 缺失值填充替换值枚举类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public enum FillMissingValuesReplaceEnum {

    MIN("Min(数值型)",1),
    MAX("Max(数值型)",2),
    MEAN("Mean(数值型)",3),
    CUSTOM("自定义(数值型和string)",4);


    private String name;
    private int index;

    public static  String getName(int index){
        for (FillMissingValuesReplaceEnum e : FillMissingValuesReplaceEnum.values()){
            if (e.getIndex() == index)
                return e.name;
        }
        return "";
    }

    FillMissingValuesReplaceEnum(String name, int index){
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
