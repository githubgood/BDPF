package com.xunfang.bdpf.datasite.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.bdpf.datasite.site.entity.Site;
import com.xunfang.bdpf.datasite.site.mapper.SiteMapper;
import com.xunfang.bdpf.datasite.site.service.SiteService;
/**
 * 
 * @ClassName SiteServiceImpl
 * @Description: Site接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author lizhu
 * @date 2017年6月6日 上午11:20:44
 * @version V1.0
 */
@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteMapper siteMapper;
	/**
	 * 
	  * @Title: selectSite
	  * @Description: 查询site数据
	  * @param @param 
	  * @param @return  
	  * @return List<Site>    返回类型
	  * @throws
	 */
	@Override
	public List<Site> selectSite() {
		return siteMapper.selectSite();
	}
	@Override
	public int insertSite(Site site) {
		// TODO Auto-generated method stub
		return siteMapper.insertSite(site);
	}
	@Override
	public int deleteBySiteId(String id) {
		// TODO Auto-generated method stub
		return siteMapper.deleteBySiteId(id);
	}
	@Override
	public List<Site> selectSiteById(String id) {
		// TODO Auto-generated method stub
		return siteMapper.selectSiteById(id);
	}
	@Override
	public int updateSite(Site site) {
		// TODO Auto-generated method stub
		return siteMapper.updateSite(site);
	}

	
}
