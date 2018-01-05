package com.xunfang.bdpf.experiment.clas.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xunfang.bdpf.experiment.clas.entity.Clas;
import com.xunfang.bdpf.experiment.clas.entity.ClasExample;
import com.xunfang.bdpf.experiment.clas.mapper.ClasMapper;
import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.PropertiesUtil;
import com.xunfang.utils.Tools;

/**
 * @ClassName ClasServiceImpl
 * @Description: 班级管理Service接口实现类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月15日 上午9:58:48
 * @version V1.0
 */
@Service
@Transactional
public class ClasServiceImpl implements ClasService {

	@Autowired
	private ClasMapper clasMapper;

	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(ClasExample example) {
		return clasMapper.countByExample(example);
	}

	 /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(ClasExample example) {
		return clasMapper.deleteByExample(example);
	}

	 /**
 	 * 
 	  * @Title: deleteByPrimaryKey
 	  * @Description: 根据条件删除单条数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return clasMapper.deleteByPrimaryKey(id);
	}

	 /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(Clas record) {
		return clasMapper.insert(record);
	}

	/**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(Clas record) {
		return clasMapper.insertSelective(record);
	}

	/**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Course> 返回类型
	  * @throws
	 */
	@Override
	public List<Clas> selectByExample(ClasExample example) {
		return clasMapper.selectByExample(example);
	}

	  /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  Course 返回类型
   	  * @throws
   	 */
	@Override
	public Clas selectByPrimaryKey(String id) {
		return clasMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(Clas record, ClasExample example) {
		return clasMapper.updateByExampleSelective(record, example);
	}

	/**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(Clas record, ClasExample example) {
		return clasMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(Clas record) {
		return clasMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(Clas record) {
		return clasMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	  * @Title: getClasCount
	  * @Description: 查询班级总数
	  *  @param keywords String 班级名称
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long getClasCount(String keywords,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		return clasMapper.getClasCount(param);
	}

	/**
   	 * 
   	  * @Title: queryClasByPage
   	  * @Description: 班级管理查询列表，带分页
   	  *  @param keywords String 班级名称
   	  *  @param skip int 从第几条开始取数据
   	  *  @param limit int 每页显示多少条数据
   	  *  @return  List<Clas> 返回类型
   	  * @throws
   	 */
	@Override
	public List<Clas> queryClasByPage(String keywords, int skip, int limit,String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keywords", keywords);
		param.put("createUser", createUser);
		param.put("skip", skip);
		param.put("limit", limit);
		return clasMapper.queryClasByPage(param);
	}
	
	/**
	 * 
	  * @Title: save
	  * @Description: 班级管理保存
	  *  @param clas  Clas 班级管理model
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@Override
	public boolean save(Clas clas) {
		boolean flag = true;
		if (StringUtils.isBlank(clas.getId())) {
			clas.setId(Identities.uuid2());
			flag = clasMapper.insert(clas) > 0 ? true : false;
		} else {
			flag = clasMapper.updateByPrimaryKeySelective(clas) > 0 ? true : false;
		}
		return flag;
	}
	
	/**
	 * 
	  * @Title: queryClassUser
	  * @Description: TODO连表查询班级与学生
	  *  @return  List<Clas> 返回类型
	  * @throws
	 */
	@Override
	public List<Clas> queryClassUser(String createUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("createUser", createUser);
		return clasMapper.queryClassUser(param);
	}

	/***
	 * @Title: getExcelInfo
	 * @Description:
	 * @param is
	 * @param isExcel2003
	 * @return Feedback
	 */
	public Feedback getExcelInfo(InputStream is, boolean isExcel2003) {
		Feedback feedback = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			// 当excel是2003时
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时
				wb = new HSSFWorkbook(is);
			}
			// 读取Excel里面客户的信息
			feedback = readExcelClas(wb);
		} catch (IOException e) {
			e.printStackTrace();
			feedback = new Feedback(false, "导入EXCEL失败！");
		}
		return feedback;
	}

	/***
	 * @Title: readExcelClas
	 * @Description: 解析excel
	 * @param wb
	 * @return Feedback
	 */
	public Feedback readExcelClas(Workbook wb) {
		Feedback feedback = null;
		// 用于界面提示信息
		StringBuffer message = new StringBuffer();
		StringBuffer clasinfo = new StringBuffer();
		String startclas = "班级信息:第";
		clasinfo.append(startclas);
		String countStr = "";
		Sheet dd = wb.getSheet("班级信息");
		int totalRows_clas = dd.getPhysicalNumberOfRows();
		int totalCells_clas = 0;
		if (totalRows_clas >= 1 && dd.getRow(0) != null) {
			totalCells_clas = dd.getRow(0).getPhysicalNumberOfCells();
		}
		List<Clas> clasList = new ArrayList<Clas>();
		Map<String,String> clasMap = new HashMap<String,String>();
		Clas clas;
		for (int r = 1; r < totalRows_clas; r++) {
			Row row = dd.getRow(r);
			if (row == null){
				continue;
			}
			clas = new Clas();
			
			for (int c = 0; c < totalCells_clas; c++) {
				Cell cell = row.getCell(c);
					if (c == 0) {//班级代码
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
						}else{
							if(cell.getStringCellValue().equals("")){
								break;
							}else{
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
									clas.setCode(cell.getStringCellValue());
								}else{
									clasinfo.append(r+"行，"+(c+1)+"列的班级代码为空!");
								}
							}
						}
					} else if (c == 1) {//班级名称
						if(cell == null){
							clasinfo.append(r+"行，"+(c+1)+"列的班级名称为空!");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								clas.setName(cell.getStringCellValue());
							}else{
								clasinfo.append(r+"行，"+(c+1)+"列的班级名称为空!");
							}
						}
					} else if (c == 2) {//班级人数
						if(cell == null){
							clas.setNum(0);
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0){
								clas.setNum(Integer.parseInt(cell.getStringCellValue()));
							}else{
								clas.setNum(0);
							}
						}
					} else if (c == 3) {//班级描述
						if(cell == null){
							clas.setDescription("");
						}else{
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
								clas.setDescription(cell.getStringCellValue());
							} else {
								clas.setDescription("");
							}
						}
					}
			}
			if (clas.getCode() != null && clas.getCode().length() > 0 && clas.getName() != null && clas.getName().length() > 0  && clasMap.get(clas.getName())==null) {
				clasMap.put(clas.getName(), clas.getName());
				clasList.add(clas);
			}
		}
		
		countStr = "班级信息：导入总数为："+(totalRows_clas-1)+"，其中导入成功数为："+clasList.size();
		this.importClasInfo(clasList);
		
		String enddd = "未导入进去,请检查班级代号、班级名称!\n";
		clasinfo.append(enddd);
		// 长度没有发生变化不进行提示
		if (clasinfo.length() != startclas.length() + enddd.length()) {
			message.append(clasinfo+countStr);
		}

		feedback = new Feedback(true, message.toString());

		return feedback;
	}
	
	@Override
	public Feedback batchImport(String fileName, MultipartFile Mfile) {
		Feedback feedback = null;
		CommonsMultipartFile cf = (CommonsMultipartFile) Mfile;
		File file = new File(PropertiesUtil.getValue("DIR_FILE")+"/fileupload");

		if (!file.exists()){
			file.mkdirs();
		}

		File file1 = new File(PropertiesUtil.getValue("DIR_FILE")+"/fileupload/upload" + new Date().getTime() + ".xls");

		try {
			cf.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			boolean isExcel2003 = true;
			if (Tools.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			is = new FileInputStream(file1);
			feedback = getExcelInfo(is, isExcel2003);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			feedback = new Feedback(false, "导入EXCEL失败！");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return feedback;
	}
	
	@Override
	public boolean importClasInfo(List<Clas> clasList) {
		boolean b = false;
		if (clasList != null) {
			b = true;
		}
		ClasExample example = new ClasExample();
		List<Clas> claslist = this.selectByExample(example);
		Map<String,String> clasMap = new HashMap<String,String>();
		for (Clas clas : claslist) {
			clasMap.put(clas.getCode(), clas.getId());
		}
		// 迭代添加班级信息
		for (Clas clas : clasList) {
			if (clasMap.get(clas.getCode())==null) {
				clas.setId(Identities.uuid2());
				this.insert(clas);
			} else {
				clas.setId(clasMap.get(clas.getCode()));
				this.updateByPrimaryKey(clas);
			}
		}
		return b;
	}
	
}
