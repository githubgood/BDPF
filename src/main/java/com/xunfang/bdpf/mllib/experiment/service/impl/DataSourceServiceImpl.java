package com.xunfang.bdpf.mllib.experiment.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.mllib.assembly.service.FlowBussiness;
import com.xunfang.bdpf.mllib.enums.FieldType;
import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.entity.TableChildExample;
import com.xunfang.bdpf.mllib.experiment.entity.TableExample;
import com.xunfang.bdpf.mllib.experiment.mapper.TableChildMapper;
import com.xunfang.bdpf.mllib.experiment.mapper.TableMapper;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.mllib.experiment.vo.TableChildVo;
import com.xunfang.bdpf.mllib.experiment.vo.TableVo;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.JDBCUtil;
import com.xunfang.utils.StringTools;

@Service
@Transactional
public class DataSourceServiceImpl implements DataSourceService {
	
	private static final Logger logger = Logger.getLogger(DataSourceServiceImpl.class);
	
	@Autowired
	TableChildMapper tableChildMapper;
	
	@Autowired
	TableMapper tableMapper;
	
	@Override
	public boolean createTable(TableVo vo) {
		logger.debug(" = = = = = DataSourceServiceImpl createTable in = = = = =");
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "";
		String columnStr = "";
		try {
			if (vo.getColumnName() == null || vo.getColumnName().length < 1) throw new RuntimeException("缺少建表参数列名");
			for (int i = 0; i < vo.getColumnName().length; i++) {
				String varchar = vo.getColumnType()[i].equals(FieldType.STRING.getName())?"varchar(30)":vo.getColumnType()[i];
				/*if(FieldType.STRING.getName().equals(vo.getColumnType()[i]))//string类型 --> varchar
					vo.getColumnType()[i] = "varchar(30)";
				if(FieldType.DOUBLE.getName().equals(vo.getColumnType()[i]))
					vo.getColumnType()[i] = "double(10,2)";
				columnStr += vo.getColumnName()[i] + " " +vo.getColumnType()[i]+",";*/
				columnStr += vo.getColumnName()[i] + " " +varchar+",";
			}
        	if(columnStr.split(",").length>0){
        		columnStr = columnStr.substring(0,columnStr.length()-1);
        	}
        	String s = "id int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), ";
        	columnStr = s.concat(columnStr);
        	sql = "create table "+vo.getName()+"("+columnStr+")";//创建表时id默认自动创建，并自增
			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			connection.commit();	
		} catch (Exception e) {
			logger.error(" = = = = = DataSourceServiceImpl createTable error = = = = ="+"【"+e.getMessage()+"】");
			/*try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.error(" = = = = = 创建表事务回滚失败 = = = = =");
				throw new RuntimeException("建表事务回滚失败"+"【"+e1.getMessage()+"】");
			}*/
			e.printStackTrace();
			throw new RuntimeException("创建表失败"+"【"+e.getMessage()+"】");
		}finally {
			JDBCUtil.close(connection,null,ps);
		}
		return true;
	}

	@Override
	public boolean isTableExist(String tableName) {
		logger.debug("= = = = = = DataSourceServiceImpl isTableExist in = = = = = =");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			connection = JDBCUtil.getConnection();
			sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='"+tableName+"'";//查找库中表是否存在
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			logger.error("数据库表查询失败");
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.close(connection, rs, ps);
		}
		
		
	}


	@Override
	public void deleteTable(String tableName) {
		logger.debug("= = = = = = DataSourceServiceImpl deleteTable in = = = = = =");
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "";
		try{
			connection = JDBCUtil.getConnection();
			sql = "DROP table "+tableName;
			ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			
		}catch(Exception e){
			logger.error("数据表删除失败");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(connection, null, ps);
		}
		
		
	}

	@Override
	public Feedback insertData(String[] text,TableVo vo) {
		logger.debug("= = = = = = DataSourceServiceImpl insertData in = = = = = =");
		Connection connection = null;
		PreparedStatement ps = null;
		String sql = "";
		String column = "";
	//	List<String> tList = new ArrayList<>();
		try {
			 /*Arrays.asList(text)
			 .stream()
			 .filter(e -> e.split(",").length == vo.getColumnName().length)
			 .forEach( d -> {
				 tList.add(d);
			 });
			if(CollectionUtils.isEmpty(tList)) return new Feedback(false,"文本数据列数与表字段列数不一致");*/
			 
			 
			for(int i =0 ;i<vo.getColumnName().length;i++){
				column += vo.getColumnName()[i]+",";
			}
			column = column.substring(0, column.length()-1);
			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false);
			
			for(int i = 0; i< text.length;i++){
				if (vo.getColumnName().length != text[i].split(",").length) 
					continue; //当数据列与字段列数目不匹配时候，此行将被数据过滤
					//throw new RuntimeException("字段列数【"+vo.getColumnName().length+"列】  "+" 数据文本第【"+(i+1)+"行】数据"+text[i].split(",").length+"列");
				
				sql = "INSERT INTO "+vo.getName()+"("+column+") VALUES ("+text[i]+");";
				ps = connection.prepareStatement(sql);
				try{
					ps.executeUpdate();
				}catch(Exception e){
					JDBCUtil.close(connection, null, ps);
					throw new RuntimeException("数据文本第【"+(i+1)+"行含有非法字符】");
				}
																
			}
			connection.commit();
			
		} catch (Exception e) {
			logger.error("文件数据插入失败");
			e.printStackTrace();
			return new Feedback(false,"数据插入失败【"+e.getMessage()+"】");
		}finally{
			JDBCUtil.close(connection, null, ps);
		}
		return new Feedback(true,"数据插入成功");
		
	}
	
	@Override
	public String[] readData(MultipartFile file) {
		logger.debug("= = = = = = DataSourceServiceImpl readData in = = = = = =");
		BufferedReader br = null;
	    List<String> list = new ArrayList<>();
	    String line = "";
	    String[] text = null;
		try {
			if (file == null) throw new RuntimeException("文件对象参数为空");
			if (!file.getOriginalFilename().endsWith("txt") && !file.getOriginalFilename().endsWith("csv")) 
			throw new RuntimeException("上传文件类型错误！");	
			
			br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			while (line != null){
				line = br.readLine();
				if (null != line) {
					list.add(line);
				}
			}
			text = new String[list.size()];
            for(int i=0;i<list.size();i++){
	              String s = list.get(i);
	              String[] splits = s.split(",");
	              String outstr = " ";//默认初始值给空格，目的其长度大于1，当文本数据中间有空行，可避免空指针
	              for (int j = 0; j < splits.length; j++) {
					String str = splits[j];
					if("".equals(str.trim()) || "?".equals(str.trim()))
						continue;
					outstr += "\""+str+"\",";
				  }
	              if(outstr.length()>0){
	           	   //strings[i] = "'"+(i+1)+"',"+outstr.substring(0,outstr.length()-1);
	           	   text[i] = outstr.substring(0,outstr.length()-1);
	              }
             }          		
		} catch (Exception e) {
			logger.error("文件读入失败！"+"【"+e.getMessage()+"】");
			e.printStackTrace();
			throw new RuntimeException("文件读入失败"+"【"+e.getMessage()+"】");
		}finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error("流关闭失败！");
				e.printStackTrace();
			}
		}
		return text;
	}

	@Override
	public List<TableChild> findTableChilds(TableVo vo) {
		logger.info("= = = = = = DataSourceServiceImpl findTableChilds in = = = = = =");
		if(StringUtils.isEmpty(vo.getName())|| StringUtils.isBlank(vo.getAccount())) throw new RuntimeException("缺少参数【表名和用户ID】");
		List<Table> tables = null;
		List<TableChild> tcList = new ArrayList<>();
		
		TableExample te = new TableExample();
		te.createCriteria().andNameEqualTo(vo.getName()).andAccountEqualTo(vo.getAccount());//应有唯一性约束条件
		
		TableChildExample tce = new TableChildExample();
		try{
			tables = tableMapper.selectByExample(te);
			if(CollectionUtils.isEmpty(tables)) return tcList;
			
			tce.createCriteria().andBdpfMllibCreateIdEqualTo(tables.get(0).getId());
			tcList = tableChildMapper.selectByExample(tce);
		
			return tcList;
		}catch(Exception e){
			logger.error("表信息查询失败");
			e.printStackTrace();
			throw new RuntimeException("表【"+vo.getName()+"】信息获取失败【"+e.getMessage()+"】");
			
		}
		
		
	}

	@Override
	public boolean checkTableName(TableVo vo) {
		logger.info("= = = = = = DataSourceServiceImpl checkTableName in = = = = = =");
		if(StringUtils.isBlank(vo.getName())|| StringUtils.isBlank(vo.getAccount())) throw new RuntimeException("缺少参数【表名和用户ID】");
		TableExample te = new TableExample();
		te.createCriteria().andNameEqualTo(vo.getName()).andAccountEqualTo(vo.getAccount());
		try{
			List<Table> tables = tableMapper.selectByExample(te);
			if(CollectionUtils.isEmpty(tables))
				return false;
			if(tables.size() > 1) throw new RuntimeException("数据错误，存在多条同名表记录");
			
			return true;
			
		}catch(Exception e){
			logger.error("表名校验失败");
			e.printStackTrace();
			throw new RuntimeException("表名校验异常【"+e.getMessage()+"】");
		}
		
	}

	@Override
	public boolean insertTableAndChild(TableVo vo) {
		logger.info("= = = = = = DataSourceServiceImpl insertTableAndChild in = = = = = =");
		if(vo == null || StringUtils.isBlank(vo.getName()) || vo.getColumnName().length < 1)
			throw new RuntimeException("表创建失败，缺少建表参数");
		Table table = new Table();
		TableChild tc = new TableChild();
		try{
			table.setAccount(vo.getAccount());
			table.setName(vo.getName());
			table.setTime(vo.getTime() == null ? 180 : vo.getTime());
			table.setPath(vo.getPath() == null ? " " : vo.getPath());
			table.setId(StringTools.getUUID());
			tableMapper.insertSelective(table);//插入表table
			
			for(int i=0;i<vo.getColumnName().length;i++){
				tc.setId(StringTools.getUUID());
				tc.setBdpfMllibCreateId(table.getId());
				tc.setName(vo.getColumnName()[i]);
				tc.setType(vo.getColumnType()[i]);
				tc.setLength(null);
				tc.setXh(i+1);
				tableChildMapper.insertSelective(tc);
			}
			
			return true;
		}catch(Exception e){
			logger.error("= = = = = = DataSourceServiceImpl insertTableAndChild error = = = = = =");
			e.printStackTrace();
			return false;
			//throw new RuntimeException("业务层创建表失败【"+e.getMessage()+"】");
		}
		
	}

	@Override
	public List<Table> findTables(TableVo vo) {
		logger.info("= = = = = = DataSourceServiceImpl findTables in = = = = = =");
		if(null == vo || StringUtils.isBlank(vo.getAccount())) throw new RuntimeException("缺少请求参数");
		List<Table> tables = null;
		TableExample te = new TableExample();
		te.createCriteria().andAccountEqualTo(vo.getAccount());
		te.or(te.createCriteria().andAccountIsNull());
		try{
			tables = tableMapper.selectByExample(te);
		}catch(Exception e){
			logger.error("= = = = = = DataSourceServiceImpl findTables error = = = = = =");
			e.printStackTrace();
			throw new RuntimeException("数据源列表查询失败【"+e.getMessage()+"】");
		}
		
		return tables;
	}

	@Override
	public List<TableChildVo> findTableData(TableVo vo,HttpSession session) {
		logger.info("= = = = = = DataSourceServiceImpl findTableData in = = = = = =");
		if(null == vo || StringUtils.isBlank(vo.getName()) || StringUtils.isBlank(vo.getAccount()))
			throw new RuntimeException("参数缺失【表名和用户id】");
		List<Table> tables = null;
		
		List<TableChild> tableChilds = new ArrayList<>();
		List<TableChildVo> tableChildVos = new ArrayList<>();
		TableExample tableExample = new TableExample();
		TableChildExample tableChildExample = new TableChildExample();
		try{
			tableExample.createCriteria().andNameEqualTo(vo.getName()).andAccountEqualTo(vo.getAccount());//条件，唯一性约束
			tableExample.or(tableExample.createCriteria().andAccountIsNull());
			tables = tableMapper.selectByExample(tableExample);
			if(CollectionUtils.isEmpty(tables)){
				return tableChildVos;
			}
			
			tableChildExample.createCriteria().andBdpfMllibCreateIdEqualTo(tables.get(0).getId());
			tableChilds = tableChildMapper.selectByExample(tableChildExample);
			
			String columnValues = "";//字段字符串
			//遍历所有字段，进行拼接
			for (TableChild tableChild : tableChilds) {
				columnValues += tableChild.getName()+",";
			}
			//如果字段不为空，则去掉最后一个逗号
			if(!"".equals(columnValues)){
				columnValues = columnValues.substring(0,columnValues.length()-1);
			}
			
			columnValues = "select "+columnValues+" from "+tables.get(0).getName();
			
			//获取当前表对应的数据
			List<LinkedHashMap<String, Object>> resultList = FlowBussiness.queryColumnValues(columnValues,tables.get(0).getName(), "100", session);
			//遍历后进行拼接组装成需要的数据
			for (TableChild tableChild : tableChilds) {
				TableChildVo tcv = new TableChildVo();
				tcv.setName(tableChild.getName());
				tcv.setType(tableChild.getType());
				String columnValue = "";
				for (LinkedHashMap<String, Object> linkedHashMap : resultList) {
					if(linkedHashMap.get(tableChild.getName())!=null){
						columnValue +=linkedHashMap.get(tableChild.getName())+",";
					}
				}
				//如果字段不为空，则去掉最后一个逗号
				if(!"".equals(columnValue)){
					columnValue = columnValue.substring(0,columnValue.length()-1);
				}
				tcv.setColumnValue(columnValue);
				tableChildVos.add(tcv);
			}
			
			return tableChildVos;
		}catch(Exception e){
			logger.error("= = = = = = DataSourceServiceImpl findTableData error = = = = = ="+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("表数据信息查询异常 "+e.getMessage());
		}
		
	}

	/**
	 * 
	  * @Title: queryTableChild
	  * @Description: 根据条件模糊查询
	  *  @return  List<TableChild> 返回类型
	  * @throws
	 */
	@Override
	public List<TableChild> queryTableChild(String name,String account) {
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("account", account);
		return tableChildMapper.queryTableChild(param);
	}

}
