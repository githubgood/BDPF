package com.xunfang.bdpf.datasite.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunfang.bdpf.datasite.site.entity.Site;

public interface SiteMapper {
	
	List<Site> selectSite();

	List<Site> selectSiteById(@Param("id")String id);
	
	int insertSite(Site site);
	
	int deleteBySiteId(String id);
	
	int updateSite(Site site);
}