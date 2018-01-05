//package com.xunfang.bdpf.test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.mllib.classification.LogisticRegressionModel;
//import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
//import org.apache.spark.mllib.linalg.Vector;
//import org.apache.spark.mllib.linalg.Vectors;
//import org.apache.spark.mllib.regression.LabeledPoint;
//import org.junit.Test;
//
//import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssembly;
//import com.xunfang.bdpf.mllib.binaryclassification.service.LogisticRegressionBinaryClassificationService;
//import com.xunfang.bdpf.mllib.experiment.entity.Table;
//import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
//import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;
//import com.xunfang.bdpf.mllib.util.InsertIntoDB;
//import com.xunfang.bdpf.system.user.service.UserService;
//import com.xunfang.utils.Tools;
//
//import scala.Tuple2;
//
///**
// * @ClassName TestUnit
// * @Description: 测试类
// * Copyright: Copyright (c) 2017
// * Company:深圳市讯方技术股份有限公司
// *
// * @author jm
// * @date 2017年5月27日 上午9:56:15
// * @version V1.0
// */
//public class TestUnit extends BaseTest{
//
//	@Resource
//	//注意，这里要用接口，因为用到了spring的AOP
//	private UserService userService;
//	private static String data = "BDPF";
//	private static String user = "root";
//	private static String pwd = "root";
//
//    @Test
//    public void getUser(Table table,List<TableChild> tableChilds){
//    	String field = "";//字段名称
//    	//拼接字段
//    	for (TableChild tableChildstr : tableChilds) {
//    		field += tableChildstr.getName()+",";
//		}
//    	if(field.split(",").length>0){
//    		field = field.substring(0,field.length()-1);
//    	}
//    	//1.数据源
//    	List<Map<String,String>> databases = InsertIntoDB.query(data, table, field,"", user, pwd);
//
////    	System.out.print("1.原始数据如下:");
////    	System.out.println();
////    	for (Map<String, String> string : databases) {
////    		System.out.println(string);
////		}
//
////    	String parms = "age,(case sex when 'male' then 1 else 0 end) as sex,(case cp when 'angina' then 0  when 'notang' then 1 else 2 end) as cp,"
////    			+ "trestbps,chol,(case fbs when 'true' then 1 else 0 end) as fbs,(case restecg when 'norm' then 0  when 'abn' then 1 else 2 end) as restecg,thalach,"
////    			+ "(case exang when 'true' then 1 else 0 end) as exang,oldpeak,(case slop when 'up' then 0  when 'flat' then 1 else 2 end) as slop,ca,"
////    			+ "(case thal when 'norm' then 0  when 'fix' then 1 else 2 end) as thal,(case status  when 'sick' then 1 else 0 end) as ifHealth";
////    	//2.SQL脚本
////    	List<Map<String,String>> sqldatas = InsertIntoDB.query(data, table, field, parms,user, pwd);
////    	System.out.print("2.经过SQL脚本后的数据如下:");
////    	System.out.println();
////    	for (Map<String, String> string : sqldatas) {
////			System.out.println(string);
////		}
//    	//3.类型转换
//    	List<ConvertAssembly> convertAssemblies = new ArrayList<ConvertAssembly>();
//    	String[] strings = field.split(",");
//    	for (int i = 0; i < strings.length; i++) {
//    		ConvertAssembly convertAssembly = new ConvertAssembly();
//    		convertAssembly.setName(strings[i]);
//    		convertAssembly.setDataType("bigint");
//    		convertAssembly.setConvertType(1);//string转为double
//    		convertAssemblies.add(convertAssembly);
//		}
//    	for (Map<String, String> string : databases) {
//			for (int i = 0; i < convertAssemblies.size(); i++) {
//				ConvertAssembly convertAssemblys = convertAssemblies.get(i);
//				if(string.get(convertAssemblys.getName())!=null){//匹配选中的字段
//					//进行数据类型转换
//					if(convertAssemblys.getConvertType()==0){//string转为int
//						string.replace(convertAssemblys.getName(),
//								string.get(convertAssemblys.getName()),
//								Tools.stringToInt(string.get(convertAssemblys.getName()))+"");
//						convertAssemblys.setDataType("BIGINT");
//					}else if(convertAssemblys.getConvertType()==1){//string转为double
//						string.replace(convertAssemblys.getName(),
//								string.get(convertAssemblys.getName()),
//								Double.parseDouble(string.get(convertAssemblys.getName()))+"");
//						convertAssemblys.setDataType("DOUBLE");
//					}else{//转为string
//						string.replace(convertAssemblys.getName(),
//								string.get(convertAssemblys.getName()),
//								string.get(convertAssemblys.getName())+"");
//						convertAssemblys.setDataType("STRING");
//					}
//				}
//			}
//		}
////    	System.out.print("3.经过类型转换后的数据如下：");
////    	System.out.println();
////    	System.out.println(sqldatas);
//
//    	//4.过滤式特征选择
//
//    	//5.归一化
//    	Map<String,String> maxMap = InsertIntoDB.queryMax(data, table, field,user, pwd);
//    	Map<String,String> minMap = InsertIntoDB.queryMin(data, table, field,user, pwd);
//    	for (Map<String, String> string : databases) {
//			for (int i = 0; i < convertAssemblies.size(); i++) {
//				ConvertAssembly convertAssemblys = convertAssemblies.get(i);
//				if(string.get(convertAssemblys.getName())!=null){//匹配选中的字段
//					//进行数据归一化
//					string.replace(convertAssemblys.getName(), string.get(convertAssemblys.getName()),Tools.normalization(string.get(convertAssemblys.getName()),maxMap.get(convertAssemblys.getName()),minMap.get(convertAssemblys.getName()))+"");
//				}
//			}
//		}
//    	System.out.print("5.经过类型转换后的数据如下：");
//    	System.out.println();
//    	System.out.println(databases);
//
//    	//6.拆分
//    	List<LabeledPoint> labeledPoints = new ArrayList<LabeledPoint>();
//    	for (Map<String, String> string : databases) {
//    		double last = 0;
//    		double[] value = new double[convertAssemblies.size()-1];
//    		for (int i = 0; i < convertAssemblies.size(); i++) {
//				ConvertAssembly convertAssemblys = convertAssemblies.get(i);
//				if(string.get(convertAssemblys.getName())!=null){//匹配选中的字段
//					if(i==convertAssemblies.size()-1){
//						last = Double.parseDouble(string.get(convertAssemblys.getName()));
//					}else{
//						value[i] = Double.parseDouble(string.get(convertAssemblys.getName()));
//					}
//				}
//			}
//    		Vector  vector = Vectors.dense(value);
//    		LabeledPoint labeledPoint = new LabeledPoint(last, vector);
//    		labeledPoints.add(labeledPoint);
//		}
//
//    	JavaSparkContext sc = AlgorithmFactory.initJavaSparkEnv("LogisticRegressionBinaryClassification",4);
//    	JavaRDD<LabeledPoint> DataSet = sc.parallelize(labeledPoints);
//
//    	Map<String, JavaRDD<LabeledPoint>> mapData = LogisticRegressionBinaryClassificationService.slitData(DataSet,0.7,0.3);
//
//    	List lists = mapData.get("trainingDataSet").collect();
//    	for (int i = 0; i < lists.size(); i++) {
//			System.out.println(lists.get(i));
//		}
//    	List lists2 = mapData.get("testDataSet").collect();
//    	for (int i = 0; i < lists2.size(); i++) {
//			System.out.println(lists2.get(i));
//		}
//    	//7.逻辑回归二分类
//    	LogisticRegressionModel LRmodel = LogisticRegressionBinaryClassificationService.buildModelandTraining(mapData);
//
//    	//8.预测
//    	JavaRDD<Tuple2<Object, Object>> predictionAndLabels=LogisticRegressionBinaryClassificationService
//    			.getPredictionAndLabels(mapData, LRmodel);
//
//
//
//
//        BinaryClassificationMetrics metrics = LogisticRegressionBinaryClassificationService
//        		.getMetrics(predictionAndLabels);
//
//
//		//getPrecision(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getPrecision(metrics).collect());
//
//		//getRecall(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getRecall(metrics).collect());
//
//		//getF1score(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getF1score(metrics).collect());
//
//		//getF2Score(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getF2Score(metrics).collect());
//
//		//getPRC(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getPRC(metrics).collect());
//
//		//getROC(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getROC(metrics).collect());
//
//		//getAUPRC(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getAUPRC(metrics));
//
//		//getAUROC(metrics);
//		System.out.println(LogisticRegressionBinaryClassificationService.getAUROC(metrics));
//
//    	//9.混淆矩阵
//
//    	//10.二分类评估
//
//    }
//
//    public static void main(String[] args) {
//    	Table table = new Table();
//    	table.setId(Tools.getPkId()+"");
//    	table.setAccount("admin");
//    	//TODO表名称判断，是否存在！
//    	table.setName("heart_disease_prediction");
//    	String path = "D:/test.txt";//上传文件路径
//    	if(path.endsWith("txt")||path.endsWith("csv")){
//    		table.setPath("D:/test.txt");
//    	}else{
//    		//TODO页面增加格式验证，只支持txt和csv格式
//    		//TODO增加文件大小限制，最大20M
//    	    System.out.println("请检查上传的文件类型！");
//    	}
//    	table.setTime(180);
//    	List<TableChild> tableChilds = new ArrayList<TableChild>();
//    	String[] names = new String[]{"age","sex","cp","trestbps","chol","fbs","restecg","thalach","exang","oldpeak","slop","ca","thal","status"};
//    	for (int i = 0; i < names.length; i++) {
//    		TableChild tableChild = new TableChild();
//    		tableChild.setId(Tools.getPkId()+"");
//    		tableChild.setBdpfMllibCreateId(table.getId());
//    		tableChild.setName(names[i]);
//    		tableChild.setType("varchar");
//    		tableChild.setLength(10);
//    		tableChilds.add(tableChild);
//		}
//
//    	new TestUnit().getUser(table, tableChilds);
//
//	}
//
//}