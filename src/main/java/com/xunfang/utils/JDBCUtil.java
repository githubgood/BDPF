package com.xunfang.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * JDBC操作类
 * @author yaolianhua
 *
 */
public class JDBCUtil {

	private static final Logger logger = Logger.getLogger(JDBCUtil.class);
	
	public static  String URL;
	public static  String USERNAME;
	public static  String PASSWORD;
	public static  String DriverName;
	
	private JDBCUtil(){}//私有化构造器
	
	static{
		try {
			URL = PropertiesUtil.getValue("jdbc.url.develop");
			USERNAME = PropertiesUtil.getValue("jdbc.username");
			PASSWORD = PropertiesUtil.getValue("jdbc.password");
			DriverName = PropertiesUtil.getValue("jdbc.driver");
			Class.forName(DriverName);
		} catch (ClassNotFoundException e) {
			logger.error(" = = = = =JDBC 初始化数据失败！= = = = =");
			e.printStackTrace();
			throw new RuntimeException("JDBC 初始化数据失败！");
		}
	}
	
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			logger.error("= = = = = jdbc 连接获取失败 = = = =");
			e.printStackTrace();
			throw new RuntimeException("jdbc 连接获取失败");
		}
		return connection;
	}
	
	public static void close(Connection connection,ResultSet rs,PreparedStatement ps){
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.error(" = = = = jdbc 连接关闭异常 = = = = ");
			e.printStackTrace();
			throw new RuntimeException(" jdbc 连接关闭异常 ");
		}
	}
	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		System.out.println(connection);
	}
	
}
