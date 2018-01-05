package com.xunfang.bdpf.mllib.enums;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: JoinTypeEnum
 * @Description: join标连接类型枚举类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public enum JoinTypeEnum {

    LEFT_JOIN("左连接",1),
    INNER_JOIN("内连接",2),
    RIGHT_JOIN("右连接",3),
    FULL_JOIN("全连接",4);


    private String name;
    private int index;

    public static  String getName(int index){
        for (JoinTypeEnum e : JoinTypeEnum.values()){
            if (e.getIndex() == index)
                return e.name;
        }
        return "";
    }

    JoinTypeEnum(String name, int index){
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
