package com.xunfang.bdpf.mllib.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.utils.JDBCUtil;
import com.xunfang.utils.Tools;
/**
 * @ClassName InsertIntoDB
 * @Description: TODO(用一句话描述该文件做什么)
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月12日 上午9:56:20
 * @version V1.0
 */
public class InsertIntoDB {
        //读取文本操作
        public static String[] writeToDat(Table table) {
        	      String[] strings = null;
        	      List<String> list = new ArrayList<String>();
        		  String line;
        	      if(table.getPath()!=null&&!"".equals(table.getPath())){
        	    	  File file = new File(table.getPath());//根据上传路径生产文件
                      try {
                       BufferedReader bw = new BufferedReader(new FileReader(file));
                       //因为不知道有几行数据，所以先存入list集合中
                       while((line = bw.readLine()) != null){//行
                    	   list.add(line);
                       }
                       bw.close();
                      } catch (IOException e) {
                       e.printStackTrace();
                      }
                      //确定数组长度
                      strings = new String[list.size()];
                      for(int i=0;i<list.size();i++){
                       String s = (String) list.get(i);
                       String[] splits = s.split(",");
                       String outstr = "";
                       for (int j = 0; j < splits.length; j++) {
						String str = splits[j];
						if("?".equals(str)){
							str = "0";
						}
						outstr += "'"+str+"',";
					   }
                       if(outstr.length()>0){
                    	   strings[i] = outstr.substring(0,outstr.length()-1);
                       }
                      }
        	      }
        	      return strings;
            }
        //连接数据库
        public static Connection getConnection(String data,String user,String pwd){
                Connection conn = null;
                try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+ data +"?characterEncoding=UTF-8",user , pwd);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return conn;
        }
        //插入数据库,只能为一个字段
        public static boolean insertInto(String data,String tablename,String field,String user,String pwd,String[] str,String columns){
                try {
                        Connection conn = getConnection(data,user,pwd);
                        conn.setAutoCommit(false);
                        //创建表
                        String sql = "create table "+tablename+"("+columns+")";
                        PreparedStatement pstmt1 = conn.prepareStatement(sql);
                        pstmt1.executeUpdate();
                        conn.commit();
                        
                        //插入数据
                        for (int i = 0; i < str.length; i++) {
                        	sql = "INSERT INTO "+ tablename + "("+ field +") VALUES ("+str[i]+");";
                        	PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                        }
                        conn.commit();
                        return true;
                } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                }
                
        }
        public static List<Map<String,String>> query(String data,Table table,String field,String parms,String user,String pwd){
                try {
                        int i = 0;
                        Connection conn = getConnection(data,user,pwd);
                        String sql;
                        if("".equals(parms)){
                        	sql = "select "+ field +" from " + table.getName();
                        }else{
                        	sql = "select "+ parms +" from " + table.getName();
                        }
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery();
                        int count = rs.getMetaData().getColumnCount();
                        List<Map<String,String>> lists = new ArrayList<Map<String,String>>();
                        if(field!=null&&field.split(",").length==count){
                        	Map<String,String> map;
                        	while (rs.next()) {
                        		map = new HashMap<String,String>();
                        		for (int j = 1; j <= count; j++) {
                        			map.put(field.split(",")[j-1], rs.getString(j));
                        		}
                        		lists.add(map);
                        		i++;
                        	}
                        }
                        return lists;
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
        public static Map<String,String> queryMax(String data,Table table,String field,String user,String pwd){
            try {
                    int i = 0;
                    Connection conn = getConnection(data,user,pwd);
                    String sql;
                    if(field==null || field.split(",").length==0){
                    	return null;
                    }
                    String[] prams = field.split(",");
                    String ss = "";
                    for (int j = 0; j < prams.length; j++) {
						ss +="max(CAST("+prams[j]+" AS SIGNED)),";
					}
                    ss = ss.substring(0,ss.length()-1);
                    sql = "select "+ss+" from "+table.getName();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    Map<String,String> map = new HashMap<String,String>();
                    if(field!=null&&field.split(",").length==count){
                    	if (rs.next()) {
                    		for (int j = 1; j <= count; j++) {
                    			map.put(field.split(",")[j-1], rs.getString(j));
                    		}
                    		i++;
                    	}
                    }
                    return map;
            } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
            }
    }
        
        public static Map<String,String> queryMin(String data,Table table,String field,String user,String pwd){
            try {
                    int i = 0;
                    Connection conn = getConnection(data,user,pwd);
                    String sql;
                    if(field==null || field.split(",").length==0){
                    	return null;
                    }
                    String[] prams = field.split(",");
                    String ss = "";
                    for (int j = 0; j < prams.length; j++) {
						ss +="min(CAST("+prams[j]+" AS SIGNED)),";
					}
                    ss = ss.substring(0,ss.length()-1);
                    sql = "select "+ss+" from "+table.getName();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    Map<String,String> map = new HashMap<String,String>();
                    if(field!=null&&field.split(",").length==count){
                    	while (rs.next()) {
                    		for (int j = 1; j <= count; j++) {
                    			map.put(field.split(",")[j-1], rs.getString(j));
                    		}
                    		i++;
                    	}
                    }
                    return map;
            } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
            }
    }

    /**
     * 列平均值
     * @param tableName
     * @param columnStr
     * @return
     */
    public static LinkedHashMap<String,String> queryAvg(String tableName,String columnStr){

        Connection connection = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        try{

            String[] column = columnStr.split(",");
            String columnSql ="";
            for (int i = 0; i< column.length;i++){
                 columnSql += "AVG("+column[i]+"),";
            }
            columnSql = columnSql.substring(0,columnSql.length() -1);
            String sql = "SELECT "+columnSql+" FROM "+tableName+"";
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= columnCount;i++){
                    map.put(column[i-1],rs.getString(i));
                }
            }

            return map;

        }catch (Exception e){
            e.printStackTrace();
            return map;
        }finally {
            JDBCUtil.close(connection,rs,ps);
        }
    }

    /**
     * 列最小值(数值型)
     * @param tableName
     * @param columnStr
     * @return
     */
    public static LinkedHashMap<String,String> queryMin(String tableName,String columnStr){

        Connection connection = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        try{

            String[] column = columnStr.split(",");
            String columnSql ="";
            for (int i = 0; i< column.length;i++){
                columnSql += "MIN(CAST("+column[i]+" AS SIGNED)),";
            }
            columnSql = columnSql.substring(0,columnSql.length() -1);
            String sql = "SELECT "+columnSql+" FROM "+tableName+"";
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= columnCount;i++){
                    map.put(column[i-1],rs.getString(i));
                }
            }

            return map;

        }catch (Exception e){
            e.printStackTrace();
            return map;
        }finally {
            JDBCUtil.close(connection,rs,ps);
        }
    }
    /**
     * 表数据获取
     * @param tableName
     * @param columnStr
     * @return
     */
    public static List<LinkedHashMap<String,Object>> queryAll(String tableName,String columnStr){
        Connection connection = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        LinkedHashMap<String,Object> map ;
        List<LinkedHashMap<String,Object>> resultList = new ArrayList<>();
        try {
            connection =JDBCUtil.getConnection();
            String[] column = columnStr.split(",");
            String sql ;
            sql = "SELECT "+ columnStr +" FROM " +tableName+"";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsma = rs.getMetaData();
            int count = rsma.getColumnCount();
            //字段名称
            List<TableChild> columnNamelist = new ArrayList<TableChild>();
            for (int i = 1; i <= count; i++) {
                TableChild tableChild = new TableChild();
                tableChild.setName(rsma.getColumnName(i));
                if("VARCHAR".equals(rsma.getColumnTypeName(i))){
                    tableChild.setType("string");
                }else{
                    tableChild.setType("string");
                }
                columnNamelist.add(tableChild);
            }

            //进行循环，取到所有的值
            while(rs.next()){
                 map = new LinkedHashMap<>();
                //遍历所有字段并放入到resultMap中
                for (TableChild tableChilds : columnNamelist){
                    //字段类型判断
                    if("int".equals(tableChilds.getType())){//bigint类型
                        map.put(tableChilds.getName(), rs.getInt(tableChilds.getName()));
                    }else if("double".equals(tableChilds.getType())){//double类型
                        map.put(tableChilds.getName(), rs.getDouble(tableChilds.getName()));
                    }else if("decimal".equals(tableChilds.getType())){//decimal类型
                        map.put(tableChilds.getName(), rs.getBigDecimal(tableChilds.getName()));
                    }else if("string".equals(tableChilds.getType())){//string字符串
                        map.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
                    }else if("boolean".equals(tableChilds.getType())){//boolean类型
                        map.put(tableChilds.getName(), rs.getBoolean(tableChilds.getName()));
                    }else if("datetime".equals(tableChilds.getType())){//datetime 日期类型
                        map.put(tableChilds.getName(), rs.getTimestamp(tableChilds.getName()));
                    }else{//其它类型
                        map.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
                    }
                }
                //将当前resultMap放入resultList
                resultList.add(map);
            }

            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return resultList;
        }finally {
            JDBCUtil.close(connection,rs,ps);
        }
    }



        public static void main(String[] args) {
        	List<TableChild> tableChilds = new ArrayList<TableChild>();
        	String field = "";//字段名称
        	String columns = "";//创建表的字段以及类型
        	Table table = new Table();
        	table.setId(Tools.getPkId()+"");
        	table.setAccount("admin");
        	//TODO表名称判断，是否存在！
        	table.setName("heart_disease_prediction");
        	String path = "D:/test.txt";//上传文件路径
        	if(path.endsWith("txt")||path.endsWith("csv")){
        		table.setPath("D:/test.txt");
        	}else{
        		//TODO页面增加格式验证，只支持txt和csv格式
        		//TODO增加文件大小限制，最大20M
        	    System.out.println("请检查上传的文件类型！");
        	}
        	table.setTime(180);
        	String[] names = new String[]{"age","sex","cp","trestbps","chol","fbs","restecg","thalach","exang","oldpeak","slop","ca","thal","status"};
        	String sql = " ,id int NOT NULL auto_increment,PRIMARY KEY (id)";
        	String[] strings =  writeToDat(table);
        	for (int i = 0; i < names.length; i++) {
        		TableChild tableChild = new TableChild();
        		tableChild.setId(Tools.getPkId()+"");
        		tableChild.setBdpfMllibCreateId(table.getId());
        		tableChild.setName(names[i]);
        		tableChild.setType("varchar");
        		tableChild.setLength(10);
        		tableChilds.add(tableChild);
			}
        	//拼接字段
        	for (TableChild tableChildstr : tableChilds) {
        		field += tableChildstr.getName()+",";
        		if(tableChildstr.getLength()==null||"".equals(tableChildstr.getLength())){
        			columns +=tableChildstr.getName()+" "+tableChildstr.getType()+",";
        		}else{
        			columns +=tableChildstr.getName()+" "+tableChildstr.getType()+"("+tableChildstr.getLength()+")"+",";
        		}
			}
        	if(field.split(",").length>0){
        		field = field.substring(0,field.length()-1);
        	}
        	if(columns.split(",").length>0){
        		columns = columns.substring(0,columns.length()-1);
        	}
        	if(columns.split(",").length!=strings[0].split(",").length){
        		System.out.println("请保持列数一致!");
        	}else{
        		columns +=sql;
        		insertInto("BDPF", table.getName(), field, "root", "root", strings, columns);
        	}
        	
        	List<Map<String,String>>  result = query("BDPF", table, field, "","root", "root");
//        	for (int i = 0; i < result.size(); i++) {
//				System.out.println("第"+i+"行"+result.get(i));
//			}
        	
		}

}

