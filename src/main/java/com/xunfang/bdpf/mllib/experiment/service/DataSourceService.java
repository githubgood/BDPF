package com.xunfang.bdpf.mllib.experiment.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.vo.TableChildVo;
import com.xunfang.bdpf.mllib.experiment.vo.TableVo;
import com.xunfang.utils.Feedback;

/**
 * 
 * @ClassName: DataSourceService
 * @Description: 数据源业务接口方法
 * @author: yaolianhua
 * @date: 2017年10月9日 下午4:11:47
 */
public interface DataSourceService {
	/**
	 * 数据源文件数据读取
	 * @param file
	 * @return
	 */
	String[] readData(MultipartFile file);
	/**
	 * 数据源新建文件数据表
	 * @param vo
	 * @return
	 */
	boolean createTable(TableVo vo);
	/**
	 * 文件数据表数据插入
	 * @param text
	 * @param vo
	 * @return
	 */
	Feedback insertData(String[] text,TableVo vo);
	/**
	 * 文件数据表删除
	 * @param tableName
	 */
	void deleteTable(String tableName);
	/**
	 * 文件数据表是否存在
	 * @param tableName
	 * @return
	 */
	boolean isTableExist(String tableName);
	/**
	 * 判断表名是否存在
	 * @param vo
	 * @return
	 */
	boolean checkTableName(TableVo vo);
	/**
	 * 查询表字段信息(子表)
	 * @param vo
	 * @return
	 */
	List<TableChild> findTableChilds(TableVo vo);
	/**
	 * 保存表字段(子表)信息
	 * @param vo
	 * @return
	 */
	boolean insertTableAndChild(TableVo vo);
	/**
	 * 查找表名信息
	 * @param vo
	 * @return
	 */
	List<Table> findTables(TableVo vo);
	/**
	 * 查找表信息(字段。类型，列值)
	 * @param vo
	 * @return
	 */
	List<TableChildVo> findTableData(TableVo vo,HttpSession session);
	
	/**
	 * 
	  * @Title: queryTableChild
	  * @Description: 根据条件模糊查询
	  *  @return  List<TableChild> 返回类型
	  * @throws
	 */
    List<TableChild> queryTableChild(String name,String account);
}
