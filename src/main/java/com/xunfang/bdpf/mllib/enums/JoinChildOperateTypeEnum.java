package com.xunfang.bdpf.mllib.enums;

/**
 * @author ylh
 * @version V1.0
 * @ClassName: JoinChildOperateTypeEnum
 * @Description: join子表操作枚举类
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/10/20
 */
public enum JoinChildOperateTypeEnum {

    COLUMN_CONDITION("关联条件",1),
    COLUMN_OUT_LEFT("左表输出字段",2),
    COLUMN_OUT_RIGHT("右表输出字段",3);



    private String name;
    private int index;

    public static  String getName(int index){
        for (JoinChildOperateTypeEnum e : JoinChildOperateTypeEnum.values()){
            if (e.getIndex() == index)
                return e.name;
        }
        return "";
    }

    JoinChildOperateTypeEnum(String name, int index){
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
