package com.xunfang.bdpf.datasite.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.datasite.item.entity.Item;
import com.xunfang.bdpf.datasite.item.mapper.ItemMapper;
import com.xunfang.bdpf.datasite.item.service.ItemService;
/**
 * 
 * @ClassName ItemServiceImpl
 * @Description: Item接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:20:44
 * @version V1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public int insertItem(Item record) {
		// TODO Auto-generated method stub
		return itemMapper.insertItem(record);
	}

	@Override
	public int deleteByAreaId(String id) {
		// TODO Auto-generated method stub
		return itemMapper.deleteByAreaId(id);
	}

	@Override
	public List<Item> selectByAreaId(String id) {
		// TODO Auto-generated method stub
		return itemMapper.selectByAreaId(id);
	}

	
}
