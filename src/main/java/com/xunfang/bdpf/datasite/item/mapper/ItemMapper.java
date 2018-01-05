package com.xunfang.bdpf.datasite.item.mapper;

import com.xunfang.bdpf.datasite.item.entity.Item;
import com.xunfang.bdpf.datasite.item.entity.ItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
    long countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByAreaId(String id);

    int insertItem(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    List<Item> selectByAreaId(String id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}