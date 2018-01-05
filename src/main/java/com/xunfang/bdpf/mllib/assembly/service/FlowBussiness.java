package com.xunfang.bdpf.mllib.assembly.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.xunfang.utils.PropertiesUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.feature.Word2Vec;
import org.apache.spark.ml.feature.Word2VecModel;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.clustering.DistributedLDAModel;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.feature.IDFModel;
import org.apache.spark.mllib.fpm.FPGrowthModel;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructType;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SqlAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;
import com.xunfang.bdpf.mllib.assembly.vo.Classified;
import com.xunfang.bdpf.mllib.binaryclassification.service.DecisionTreeClassificationService;
import com.xunfang.bdpf.mllib.binaryclassification.service.GradientBoostingTreesClassificationService;
import com.xunfang.bdpf.mllib.binaryclassification.service.LogisticRegressionBinaryClassificationService;
import com.xunfang.bdpf.mllib.binaryclassification.service.SVMClassifierService;
import com.xunfang.bdpf.mllib.clustering.service.KMeansService;
import com.xunfang.bdpf.mllib.clustering.service.LDA_Service;
import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.fpm.service.FPGrowthService;
import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;
import com.xunfang.bdpf.mllib.multiclassification.service.LogisticRegressionMultiClassificationService;
import com.xunfang.bdpf.mllib.multiclassification.service.NaiveBayesClassificationService;
import com.xunfang.bdpf.mllib.multiclassification.service.RandomForestClassificationService;
import com.xunfang.bdpf.mllib.recommendation.service.CollaborativeFilteringService;
import com.xunfang.bdpf.mllib.regression.service.DecisionTreeRegressionService;
import com.xunfang.bdpf.mllib.regression.service.GradientBoostedTreesRegressionService;
import com.xunfang.bdpf.mllib.regression.service.LinearRegressionService;
import com.xunfang.bdpf.mllib.regression.service.RandomForestRegressionService;
import com.xunfang.bdpf.mllib.textAnalysis.service.Word2VecService;
import com.xunfang.bdpf.mllib.textAnalysis.service.tfIDF;
import com.xunfang.bdpf.mllib.util.InsertIntoDB;
import com.xunfang.utils.JDBCUtil;
import com.xunfang.utils.Tools;

import scala.Tuple2;

/**
 * 
 * @ClassName FlowBussiness
 * @Description:流程业务逻辑处理
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月23日 上午9:24:47
 * @version V1.0
 */
public class FlowBussiness {
	/**
	 * 
	  * @Title: readDb
	  * @Description: 读数据库 3
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String readDb(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//数据库表名
		String name = (String)parms.get("name");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = queryColumnValues(tableChildList, name, "");
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultList);
		}else{
			session.setAttribute(id, resultList);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: randomSampling
	  * @Description: 随机采样 5
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String randomSampling(Map<String,Object> parms){
        //当前组件的主键ID
        String id = (String)parms.get("id");
        //当前组件的父组件ID
        String parentId = (String)parms.get("parentId");
        //缓存session
        HttpSession session = (HttpSession) parms.get("session");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChildList==null){//如果当前组件没有字段选择，则取父组件的字段选择信息
			tableChildList = (List<TableChild>)session.getAttribute("table"+parentId);
		}
        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

        //随机采样条件
        RandomSamplingAssembly randomSamplingAssembly = (RandomSamplingAssembly)session.getAttribute("randomSamplingAssembly");
        if(randomSamplingAssembly==null){
        	return id;
        }
        //最新的结果集
        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        //根据随机采样参数取样
        double samplRatio = (randomSamplingAssembly.getSampleRatio() == null || randomSamplingAssembly.getSampleRatio() == 0) ? 1 : randomSamplingAssembly.getSampleRatio();
        int resultListnewSize =(int)(samplRatio * resultList.size());//新结果集长度
        Random random = new Random();

        for (int i = 0; i < resultListnewSize; i++){
            int j = random.nextInt(resultList.size());
            resultListnew.add(resultList.get(j));
            resultList.remove(j);
        }
        //resultListnew = resultList.subList(0,10);


		List<TableChild> tableChilds_5 = new ArrayList<>();
		tableChilds_5.addAll(tableChildList);
		session.setAttribute("tableChilds_5",tableChilds_5);
        //将当前缓存值放入到session中
        if(session.getAttribute(id)!=null){
            session.removeAttribute(id);
            session.setAttribute(id, resultListnew);
        }else{
            session.setAttribute(id, resultListnew);
        }
        return id;
	}
	
	/**
	 * 
	  * @Title: filtermapping
	  * @Description: 过滤与映射 6
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String filterMapping(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//过滤与映射条件
		FilterMappingAssembly filterMappingAssembly = (FilterMappingAssembly)session.getAttribute("filterMappingAssembly");
		
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChildList==null){//如果当前组件没有字段选择，则取父组件的字段选择信息
			tableChildList = (List<TableChild>)session.getAttribute("table"+parentId);
		}
		
		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		
		//根据选择的字段和过滤条件进行遍历
		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < tableChildList.size(); i++){
				TableChild tableChild = tableChildList.get(i);
				if(string.get(tableChild.getName())!=null){//匹配选中的字段
					if(filterMappingAssembly.getFiltrationCondition()!=null&&tableChild.getName().equals(filterMappingAssembly.getFiltrationCondition().split("=")[0])
							      &&string.get(tableChild.getName()).equals(filterMappingAssembly.getFiltrationCondition().split("=")[1].replaceAll("'", ""))){//根据输入的过滤条件进行判断
						map.putAll(string);
					}
				}
			}
			if(map.size()>0){
				resultListnew.add(map);
			}
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: join
	  * @Description: 连接 9
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String join(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//数据库表名
		String name = (String)parms.get("name");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		String[] parentIds = parentId.split(",");
		
		String leftName = (String) session.getAttribute("leftTableName");
		String rightName = (String) session.getAttribute("rightTableName");
		
		String parentId_1 ="",parentId_2 = "";

		if (parentIds.length > 1) {
			 parentId_1 = parentIds[0];
			 parentId_2 = parentIds[1];
		}
		//父组件相关信息
		Assembly assemblie_1 = (Assembly)parms.get(parentId_1);
		Assembly assemblie_2 = (Assembly)parms.get(parentId_2);

		//通过表名对应的结果集
		List<LinkedHashMap<String, Object>> resultList_1 = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie_1.getId());
		List<LinkedHashMap<String, Object>> resultList_2 = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie_2.getId());

		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		//join条件
		JoinAssembly joinAssembly = (JoinAssembly) session.getAttribute("joinAssembly");
		String condition = joinAssembly.getJoinCondition();
		String[] conditions = condition.split(",");
		String columnCondition ="";
		for (int i =0 ;i < conditions.length ;i++){
			columnCondition += conditions[i]+",";
		}
		columnCondition = columnCondition.substring(0,columnCondition.length()-1);
		//join字段信息
		String columnStr = "";
		List<JoinChildAssembly> joinChildAssemblies = (List<JoinChildAssembly>)session.getAttribute("joinChildAssemblies");
		for (JoinChildAssembly e : joinChildAssemblies){
			columnStr += e.getColumnIn()+",";
		}
		columnStr = columnStr.substring(0,columnStr.length()-1);
		String sql = columnCondition == null || "".equals(columnCondition)
				? "SELECT "+columnStr+" FROM "+leftName+","+rightName+""
				:"SELECT "+columnStr+" FROM "+leftName+","+rightName+" WHERE "+columnCondition+"" ;
		resultListnew = queryResult(sql);

		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: typeConversion
	  * @Description: 类型转换 11
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String typeConversion(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//类型转换字段信息
		List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChilds==null){
			tableChilds = (List<TableChild>)session.getAttribute("table"+parentId);
		}
		
		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		
    	for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < tableChilds.size(); i++){
				TableChild tableChild = tableChilds.get(i);
				if(string.get(tableChild.getName())!=null){//匹配选中的字段
					map.put(tableChild.getName(), string.get(tableChild.getName()));
					if(Tools.isNumeric(string.get(tableChild.getName())+"")){
						//进行数据类型转换
						if(tableChild.getXh()==0){//string转为double
							map.replace(tableChild.getName(), map.get(tableChild.getName()), Double.parseDouble(map.get(tableChild.getName())+""));
						}else if(tableChild.getXh()==1){//string转为int
							map.replace(tableChild.getName(), map.get(tableChild.getName()), Tools.stringToInt(map.get(tableChild.getName())+""));
						}else{//转为string
							map.replace(tableChild.getName(), map.get(tableChild.getName()), map.get(tableChild.getName())+"");
						}
					}
				}
			}
			resultListnew.add(map);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: addSerialNum
	  * @Description: 增加序列号 12
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String addSerialNum(Map<String,Object> parms){
        //当前组件的主键ID
        String id = (String)parms.get("id");
        //当前组件的父组件ID
        String parentId = (String)parms.get("parentId");
        //缓存session
        HttpSession session = (HttpSession) parms.get("session");
        //所有的列
  		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
  		if(tableChildList==null){
  			tableChildList = (List<TableChild>)session.getAttribute("table"+parentId);
  		}
        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名和列查询对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        AddSerialNumAssembly o = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
        List<TableChild> tableChilds_2 = new ArrayList<>();
        tableChilds_2.addAll(tableChildList);
        TableChild tc = new TableChild();
        tc.setId(id);
        tc.setBdpfMllibCreateId(tableChildList.get(0).getBdpfMllibCreateId());
        tc.setName(o.getSerialNum() == null || o.getSerialNum() == "" ? "append_id" : o.getSerialNum());
        tableChilds_2.add(tableChildList.size(),tc);
        session.removeAttribute("table"+id);
		session.setAttribute("table"+id, tableChilds_2);
        int i = 0;
        for (LinkedHashMap<String, Object> e : resultList) {
        	LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        	for (TableChild t : tableChildList){
        		if (e.containsKey(t.getName()))
					map.put(t.getName(), e.get(t.getName()));
			}
			map.put(o.getSerialNum(),i++);
            resultListnew.add(map);
        }

        //将当前缓存值放入到session中
        if(session.getAttribute(id)!=null){
            session.removeAttribute(id);
            session.setAttribute(id, resultListnew);
        }else{
            session.setAttribute(id, resultListnew);
        }
        return id;
	}
	
	/**
	 * 
	  * @Title: split
	  * @Description: 拆分 13
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String split(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		List<LabeledPoint> labeledPoints = new ArrayList<LabeledPoint>();

		List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChilds==null){
			tableChilds = (List<TableChild>)session.getAttribute("table"+parentId);
		}
		for (LinkedHashMap<String, Object> string : resultList) {
    		double last = 0;
    		double[] value = new double[tableChilds.size()-1];
    		for (int i = 0; i < tableChilds.size(); i++) {
    			TableChild tableChild = tableChilds.get(i);
				if(string.get(tableChild.getName())!=null){//匹配选中的字段
					if(!Tools.isNumeric(string.get(tableChild.getName())+"")){
						if(i==tableChilds.size()-1){
							last = Double.parseDouble(string.get(tableChild.getName())+"");
						}else{
							value[i] = Double.parseDouble(string.get(tableChild.getName())+"");
						}
					}
				}
			}
    		Vector vector = Vectors.dense(value);
    		LabeledPoint labeledPoint = new LabeledPoint(last, vector);
    		labeledPoints.add(labeledPoint);
		}
		
    	JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	
    	JavaRDD<LabeledPoint> DataSet = sc.parallelize(labeledPoints);
    	
    	SplitAssembly splitAssemblie = (SplitAssembly)session.getAttribute("splitAssemblie");
    	
    	double a = splitAssemblie.getSegmentationRatio();
    	double b = Tools.bigDecimal("1",a+"");
    	
    	Map<String, JavaRDD<LabeledPoint>> mapData = LogisticRegressionBinaryClassificationService.slitData(DataSet,a,b);
		//训练数据集
    	List<LabeledPoint> trainingList = mapData.get("trainingDataSet").collect();
    	session.removeAttribute("trainingList");
    	session.setAttribute("trainingList", trainingList);
    	//测试数据集
    	List<LabeledPoint> testList = mapData.get("testDataSet").collect();
    	session.removeAttribute("testList");
    	session.setAttribute("testList", testList);
    	
		//将当前缓存值放入到session中
    	if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, mapData);
		}else{
			session.setAttribute(id, mapData);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: fillMissingValues
	  * @Description: 缺失值填充 14
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String fillMissingValues(Map<String,Object> parms){
        //当前组件的主键ID
        String id = (String)parms.get("id");
        //当前组件的父组件ID
        String parentId = (String)parms.get("parentId");
        //缓存session
        HttpSession session = (HttpSession) parms.get("session");

        //所有的列
        List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);

        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名和列查询对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        for (LinkedHashMap<String, Object> e : resultList) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (int i = 0; i < e.size(); i++) {
                TableChild o = tableChildList.get(i);
                if(e.containsKey(o.getName())){
                    if ("".equals(e.get(o.getName())) || null == e.get(o.getName())){
                        Object obj  = replaceValue(resultList,o.getName());
                        map.put(o.getName(),obj);
                        continue;
                    }
                    map.put(o.getName(),e.get(o.getName()));
                }

            }
            resultListnew.add(map);
        }


		List<TableChild> tableChilds_14 = new ArrayList<>();
		tableChilds_14.addAll(tableChildList);
		session.setAttribute("tableChilds_14",tableChilds_14);
        //将当前缓存值放入到session中
        if(session.getAttribute(id)!=null){
            session.removeAttribute(id);
            session.setAttribute(id, resultListnew);
        }else{
            session.setAttribute(id, resultListnew);
        }
        return id;
	}
	
	/**
	 * 
	  * @Title: normalization
	  * @Description: 归一化 15
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String normalization(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//数据库表名
		String tableName = (String)session.getAttribute("tableName");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChildList==null){
			tableChildList = (List<TableChild>)session.getAttribute("table"+parentId);
		}
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		Table table = new Table();
		table.setName(tableName);
		
		String columnValues = "";//字段字符串
		//遍历所有字段，进行拼接
		for (TableChild tableChilds : tableChildList) {
			columnValues += tableChilds.getName()+",";
		}
		//如果字段不为空，则去掉最后一个逗号
		if(!"".equals(columnValues)){
			columnValues = columnValues.substring(0,columnValues.length()-1);
		}
		
		Map<String,String> maxMap = InsertIntoDB.queryMax("BDPF",table,columnValues,PropertiesUtil.getValue("jdbc.username"), PropertiesUtil.getValue("jdbc.password"));
    	Map<String,String> minMap = InsertIntoDB.queryMin("BDPF",table,columnValues,PropertiesUtil.getValue("jdbc.username"),PropertiesUtil.getValue("jdbc.password"));
		
    	List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
    	
		for (LinkedHashMap<String, Object> string : resultList) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < tableChildList.size(); i++) {
				TableChild convertAssemblys = tableChildList.get(i);
				if(string.get(convertAssemblys.getName())!=null){//匹配选中的字段
					map.put(convertAssemblys.getName(), string.get(convertAssemblys.getName()));
					if(!Tools.isNumeric(string.get(convertAssemblys.getName())+"")){
						//进行数据归一化
						map.replace(convertAssemblys.getName(), map.get(convertAssemblys.getName()),Tools.normalization(map.get(convertAssemblys.getName())+"",maxMap.get(convertAssemblys.getName())+"",minMap.get(convertAssemblys.getName())));
					}
				}
			}
			resultListnew.add(map);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: standardization
	  * @Description: 标准化 16
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String standardization(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//数据库表名
		String tableName = (String)session.getAttribute("tableName");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChildList==null){
			tableChildList = (List<TableChild>)session.getAttribute("table"+parentId);
		}
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);

		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

		Table table = new Table();
		table.setName(tableName);

		String columnValues = "";//字段字符串
		//遍历所有字段，进行拼接
		for (TableChild tableChilds : tableChildList) {
			columnValues += tableChilds.getName()+",";
		}
		//如果字段不为空，则去掉最后一个逗号
		if(!"".equals(columnValues)){
			columnValues = columnValues.substring(0,columnValues.length()-1);
		}

		LinkedHashMap<String,String> avgMap = InsertIntoDB.queryAvg(tableName,columnValues);
		//List<LinkedHashMap<String,Object>> mapList = InsertIntoDB.queryAll(tableName,columnValues);

		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

		List<StandardizationChildAssembly> standardizationChildAssemblies = (List<StandardizationChildAssembly>)session.getAttribute("standardizationChildAssemblies");
		for (LinkedHashMap<String, Object> e : resultList) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			for (TableChild t : tableChildList ) {
				if(e.containsKey(t.getName())){
					e.replace(t.getName(),e.get(t.getName()),standardDiviation(resultList,avgMap,t.getName(),e.get(t.getName()).toString()));
					//map.putAll(e);
					map.put(t.getName(),e.get(t.getName()));
				}

			}
			resultListnew.add(map);
		}


		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 标准化
	 * @param mapList
	 * @param map
	 * @param columName
	 * @param value
	 * @return
	 */
	public static  String standardDiviation(List<LinkedHashMap<String,Object>> mapList,LinkedHashMap<String,String> map,String columName,String value){
		int count = mapList.size();
		BigDecimal avg = new BigDecimal(map.get(columName));
		BigDecimal sum = new BigDecimal("0");
		for (LinkedHashMap<String,Object> e : mapList){
			BigDecimal b = new BigDecimal(e.get(columName).toString());
			sum = sum.add(b.subtract(avg).multiply(b.subtract(avg)));
		}
		BigDecimal fc = sum.divide(new BigDecimal(count),6);//方差
		double bzc = Math.sqrt(fc.doubleValue());//标准差

		BigDecimal currentValue = new BigDecimal(value);
		String bzh = currentValue.subtract(avg).divide(new BigDecimal(bzc),6).toString();//标准化
		return bzh;
	}
	
	/**
	 * 
	  * @Title: union
	  * @Description: 合并 17
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String union(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//数据库表名
		String name = (String)parms.get("name");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		String[] parentIds = parentId.split(",");
		String parentId_1 ="",parentId_2 = "";

		if (parentIds.length > 1) {
			parentId_1 = parentIds[0];
			parentId_2 = parentIds[1];
		}
		//父组件相关信息
		Assembly assemblie_1 = (Assembly)parms.get(parentId_1);
		Assembly assemblie_2 = (Assembly)parms.get(parentId_2);

		//通过表名对应的结果集
		List<LinkedHashMap<String, Object>> resultList_1 = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie_1.getId());
		List<LinkedHashMap<String, Object>> resultList_2 = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie_2.getId());

		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		//union条件
		UnionAssembly unionAssembly = (UnionAssembly) session.getAttribute("unionAssembly");

		String whereConditionLeft = unionAssembly.getWhereConditionLeft();
		String whereConditionRight = unionAssembly.getWhereConditionRight();

		//union字段信息
		String columnStrLeft = "";
		String columnStrRight ="";
		List<UnionChildAssembly> unionChildAssemblies = (List<UnionChildAssembly>)session.getAttribute("unionChildAssemblies");
		for (UnionChildAssembly e : unionChildAssemblies){
			if (e.getOperationType() == 0)
				columnStrLeft += e.getColumnIn()+",";
			if (e.getOperationType() == 1)
				columnStrRight += e.getColumnIn()+",";
		}
		columnStrLeft = columnStrLeft.substring(0,columnStrLeft.length()-1);
		columnStrRight = columnStrRight.substring(0,columnStrRight.length()-1);
		String sqlLeft = (whereConditionLeft == null || "".equals(whereConditionLeft))
				? "SELECT "+columnStrLeft+" FROM "+assemblie_1.getName()+" "
				: "SELECT "+columnStrLeft+" FROM "+assemblie_1.getName()+" WHERE "+whereConditionLeft+" ";
		String sqlRight = (whereConditionRight == null || "".equals(whereConditionRight))
				? "SELECT "+columnStrRight+" FROM "+assemblie_2.getName()+" "
				: "SELECT "+columnStrRight+" FROM "+assemblie_2.getName()+" WHERE "+whereConditionRight+" ";

		String sql = sqlLeft +" UNION " + sqlRight;
		resultListnew = queryResult(sql);

		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: featureSizeTransform
	  * @Description: 特征尺寸变换 20
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String featureSizeTransform(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		if(parentId.length()>32){
			parentId = parentId.substring(33,parentId.length());
		}
		//数据库表名
		String name = (String)parms.get("name");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
        //所有的列
        List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);

        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名和列查询对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
        if(null == resultList){
        	return id;
        }
        
        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        //特征尺寸变换主表信息
		List<FeatureengineeringAssembly> featureengineeringAssemblys  = (List<FeatureengineeringAssembly>)session.getAttribute("featureengineeringAssemblys");
		if(null == featureengineeringAssemblys){
        	return id;
        }
		
		List<String>  columnNames =new ArrayList<String>();
		for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
			columnNames.add(featureengineeringAssembly.getName());
		}

		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < featureengineeringAssemblys.size(); i++){
				FeatureengineeringAssembly FeatureengineeringAssembly = featureengineeringAssemblys.get(i);
				if(FeatureengineeringAssembly != null && FeatureengineeringAssembly.getProportionalityCoefficient()!=null && string.get(FeatureengineeringAssembly.getName())!=null){//匹配选中的字段
					double num = Double.parseDouble(string.get(FeatureengineeringAssembly.getName()).toString()) * Double.parseDouble(FeatureengineeringAssembly.getProportionalityCoefficient());
					map.put(FeatureengineeringAssembly.getName(), num);
				}
			}
			resultListnew.add(map);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute("columnNames20")!=null){
			session.removeAttribute("columnNames20");
			session.setAttribute("columnNames20", columnNames);
		}else{
			session.setAttribute("columnNames20", columnNames);
		}
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: binaryzation
	  * @Description: 二值化 21
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String binaryzation(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		if(parentId.length()>32){
			parentId = parentId.substring(33,parentId.length());
		}
		//数据库表名
		String name = (String)parms.get("name");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
        //所有的列
        List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);

        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名和列查询对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
        if(null == resultList){
        	return id;
        }
        
        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        //二值化 主表信息
		List<FeatureengineeringAssembly> featureengineeringAssemblys  = (List<FeatureengineeringAssembly>)session.getAttribute("featureengineeringAssemblys");
		if(null == featureengineeringAssemblys){
        	return id;
        }
		
		List<String>  columnNames =new ArrayList<String>();
		for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
			columnNames.add(featureengineeringAssembly.getName());
		}
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();

		for (String columnName : columnNames) {
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		LinkedHashMap<String, Double> columnAvgs = new LinkedHashMap<String, Double>();
		for (String columnName : columnNames) {
			for (LinkedHashMap<String, List<Object>> str : datas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					double sum = 0;
					double avg = 0;
					for (int i = 0; i< str.get(columnName).size(); i++) {
						sum = sum + Double.parseDouble(str.get(columnName).get(i).toString());
					}
					avg = sum/str.get(columnName).size();
					columnAvgs.put(columnName, avg);
				}
			}	
		}
		
		
		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < featureengineeringAssemblys.size(); i++){
				FeatureengineeringAssembly FeatureengineeringAssembly = featureengineeringAssemblys.get(i);
				if(FeatureengineeringAssembly != null && string.get(FeatureengineeringAssembly.getName())!=null){//匹配选中的字段
					double num = Double.parseDouble(string.get(FeatureengineeringAssembly.getName()).toString());
					if(num < columnAvgs.get(FeatureengineeringAssembly.getName())){					
						map.put(FeatureengineeringAssembly.getName(), 0);
					}else{
						map.put(FeatureengineeringAssembly.getName(), 1);
					}
				}
			}
			resultListnew.add(map);
		}
		
	
		//将当前缓存值放入到session中
		if(session.getAttribute("columnNames21")!=null){
			session.removeAttribute("columnNames21");
			session.setAttribute("columnNames21", columnNames);
		}else{
			session.setAttribute("columnNames21", columnNames);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: oneHotEncoding
	  * @Description: one-hot编码 23
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String oneHotEncoding(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		if(parentId.length()>32){
			parentId = parentId.substring(33,parentId.length());
		}
		//数据库表名
		String name = (String)parms.get("name");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
        //所有的列
        List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);

        //父组件相关信息
        Assembly assemblie = (Assembly)parms.get(parentId);

        //通过表名和列查询对应的结果集
        List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
        if(null == resultList){
        	return id;
        }
        
        List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

        //二值化 主表信息
		List<FeatureengineeringAssembly> featureengineeringAssemblys  = (List<FeatureengineeringAssembly>)session.getAttribute("featureengineeringAssemblys");
		if(null == featureengineeringAssemblys){
        	return id;
        }
		List<String>  columnNamess =new ArrayList<String>();
		List<String>  columnNames =new ArrayList<String>();
		for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
			columnNames.add(featureengineeringAssembly.getName());
			columnNamess.add(featureengineeringAssembly.getName());
			columnNamess.add(featureengineeringAssembly.getName()+"1");
			columnNamess.add(featureengineeringAssembly.getName()+"2");
			columnNamess.add(featureengineeringAssembly.getName()+"3");
		}
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();

		for (String columnName : columnNames) {
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
				
		List<Double>  nums = new ArrayList<Double>();
		LinkedHashMap<String, Double> columnAvgs = new LinkedHashMap<String, Double>();
		LinkedHashMap<String, Double> columnMins = new LinkedHashMap<String, Double>();
		for (String columnName : columnNames) {
			for (LinkedHashMap<String, List<Object>> str : datas) {//遍历数据集
				if(str.get(columnName)!=null){
					for (int i = 0; i< str.get(columnName).size(); i++) {
						nums.add(Double.parseDouble(str.get(columnName).get(i).toString()));
					}
					double max = Collections.max(nums);
					double min = Collections.min(nums);
					nums.removeAll(nums);
					double avg =  (max - min)/3; 
					columnAvgs.put(columnName, avg);
					columnMins.put(columnName, min);
				}
			}	
		}	

		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < featureengineeringAssemblys.size(); i++){
				FeatureengineeringAssembly FeatureengineeringAssembly = featureengineeringAssemblys.get(i);
				if(FeatureengineeringAssembly != null && string.get(FeatureengineeringAssembly.getName())!=null){//匹配选中的字段
					double num = Double.parseDouble(string.get(FeatureengineeringAssembly.getName()).toString());
					double avgParam = columnAvgs.get(FeatureengineeringAssembly.getName());
					double minParam = columnMins.get(FeatureengineeringAssembly.getName());

					if(minParam <= num && num < (avgParam + minParam)){					
						map.put(FeatureengineeringAssembly.getName(), string.get(FeatureengineeringAssembly.getName()));
						map.put(FeatureengineeringAssembly.getName()+"1", 1);
						map.put(FeatureengineeringAssembly.getName()+"2", 0);
						map.put(FeatureengineeringAssembly.getName()+"3", 0);
					}else if((avgParam + minParam) <= num && num <(avgParam*2 + minParam)){
						map.put(FeatureengineeringAssembly.getName(), string.get(FeatureengineeringAssembly.getName()));
						map.put(FeatureengineeringAssembly.getName()+"1", 0);
						map.put(FeatureengineeringAssembly.getName()+"2", 1);
						map.put(FeatureengineeringAssembly.getName()+"3", 0);
					}else{
						map.put(FeatureengineeringAssembly.getName(), string.get(FeatureengineeringAssembly.getName()));
						map.put(FeatureengineeringAssembly.getName()+"1", 0);
						map.put(FeatureengineeringAssembly.getName()+"2", 0);
						map.put(FeatureengineeringAssembly.getName()+"3", 1);
					}
				}
			}
			resultListnew.add(map);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute("columnNames23")!=null){
			session.removeAttribute("columnNames23");
			session.setAttribute("columnNames23", columnNamess);
		}else{
			session.setAttribute("columnNames23", columnNamess);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: discreteCharacteristics
	  * @Description: 特征离散 24
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String discreteCharacteristics(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//数据库表名
		String tableName = (String)session.getAttribute("tableName");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);

		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);


		String columnValues = "";//字段字符串
		//遍历所有字段，进行拼接
		for (TableChild tableChilds : tableChildList) {
			columnValues += tableChilds.getName()+",";
		}
		//如果字段不为空，则去掉最后一个逗号
		if(!"".equals(columnValues)){
			columnValues = columnValues.substring(0,columnValues.length()-1);
		}
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

		//特征离散条件
		FeatureengineeringAssembly f = (FeatureengineeringAssembly) session.getAttribute("featureengineeringAssembly");
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

		List<FeatureengineeringChildAssembly>  featureengineeringChildAssemblies = (List<FeatureengineeringChildAssembly>)session.getAttribute("featureengineeringChildAssemblies");
		LinkedHashMap<String,String> minMap = InsertIntoDB.queryMin(tableName,columnValues);
		for (LinkedHashMap<String,Object> e : resultList){
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			for(FeatureengineeringChildAssembly o: featureengineeringChildAssemblies){
				if(e.containsKey(o.getName())){
					e.replace(o.getName(),e.get(o.getName()),discreteFeatures(f.getEquidistantDispersionInterval(),o.getName(),e.get(o.getName()),minMap));
					//map.putAll(e);
					map.put(o.getName(),e.get(o.getName()));
				}

			}
			resultListnew.add(map);
		}
//		List<TableChild> tableChilds_24 = new ArrayList<>();
//		tableChilds_24.addAll(tableChildList);
//		session.setAttribute("tableChilds_24",tableChilds_24);

		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}

		return id;
	}

	/**
	 * 特征离散区间值
	 * @param equidistantDispersionInterval
	 * @param value
	 * @return
	 */
	public static  String discreteFeatures(String equidistantDispersionInterval,String columnName,Object value,LinkedHashMap<String,String> minMap){
		BigDecimal currentMin = new BigDecimal(minMap.get(columnName) == null || "".equals(minMap.get(columnName))
				? "0"
				: minMap.get(columnName));//当前列最小值
		BigDecimal currentValue = new BigDecimal(value.toString());//当前值
		String result = currentValue.subtract(currentMin)
				.divide(new BigDecimal(equidistantDispersionInterval),BigDecimal.ROUND_HALF_UP)
				.add(new BigDecimal("1"))
				.toString();
		return result;
	}
	/**
	 * 
	  * @Title: pca
	  * @Description: 主成分分析(PCA) 25
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String pca(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");

		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		//特征离散条件
		FeatureengineeringAssembly f = (FeatureengineeringAssembly) session.getAttribute("featureengineeringAssembly");

		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

		List<String> keyList = new LinkedList<>();
		LinkedHashMap<String,Object> m = resultList.get(0);
		for (String key : m.keySet()){
			keyList.add(key);
		}

		SparkConf conf = new SparkConf().setAppName("PCA_Example").setMaster("local[4]");
		SparkContext sc = new SparkContext(conf);
		LinkedList<Vector> rowsList = new LinkedList<Vector>();
//		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
//		if(sc==null||sc.sc().isStopped()){
//			sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
//			session.removeAttribute("sc");
//			session.setAttribute("sc", sc);
//		}
		for (LinkedHashMap<String,Object> e : resultList){
			LinkedHashMap<String,Object> map = new LinkedHashMap<>();
			double[] data = new double[keyList.size()];
			for (int i = 0; i< keyList.size(); i++){
				data[i] = Double.parseDouble(e.get(keyList.get(i)).toString());
			}
			Vector currentRow = Vectors.dense(data);
			rowsList.add(currentRow);

		}
		JavaRDD<Vector> rows = JavaSparkContext.fromSparkContext(sc).parallelize(rowsList);
		RowMatrix mat = new RowMatrix(rows.rdd());
		Matrix pc = mat.computePrincipalComponents(Integer.valueOf(f.getK()));
		RowMatrix projected = mat.multiply(pc);//行矩阵乘法 -- 得到的新矩阵
		List<Vector> vectors = projected.rows().toJavaRDD().collect();
		List<double[]> l = new ArrayList<>();
		for (Vector e : vectors){
			l.add(e.toArray());
		}

		session.removeAttribute("pcaList");
		session.setAttribute("pcaList",l);
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, l);
		}else{
			session.setAttribute(id, l);
		}

		if (sc!=null && !sc.isStopped())
			sc.stop();
		return id;
	}
	
	/**
	 * 
	  * @Title: importanceAnalysis
	  * @Description: 特征重要性分析 26
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String importanceAnalysis(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");

		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);

		List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
		if(tableChilds==null){
			tableChilds = (List<TableChild>)session.getAttribute("table"+parentId);
		}

		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

		List<LabeledPoint> labeledPoints = new ArrayList<LabeledPoint>();

		Map<String,JavaRDD<LabeledPoint>> mapData = split2ResultList(labeledPoints,session,tableChilds,resultList);
		JavaRDD<LabeledPoint> parsedData = mapData.get("trainingDataSet");
		// 模型训练
		LinearRegressionModel  model = LinearRegressionService.train(parsedData);

		double[] l = model.weights().toArray();


		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, l);
		}else{
			session.setAttribute(id, l);
		}
		
		return id;
	}
	
	/**
	 * 数据拆分私有方法
	 * @param labeledPoints
	 * @param session
	 * @param tableChilds
	 * @param resultList
	 * @return
	 */
	private  static Map<String,JavaRDD<LabeledPoint>> split2ResultList(List<LabeledPoint> labeledPoints,HttpSession session,List<TableChild> tableChilds,List<LinkedHashMap<String,Object>> resultList){

		for (LinkedHashMap<String, Object> string : resultList) {
			double last = 0;
			double[] value = new double[tableChilds.size()];
			for (int i = 0; i < tableChilds.size(); i++) {
				TableChild tableChild = tableChilds.get(i);
				if(string.get(tableChild.getName())!=null){//匹配选中的字段
					if(!Tools.isNumeric(string.get(tableChild.getName())+"")){
						if(i==tableChilds.size()-1){
							last = Double.parseDouble(string.get(tableChild.getName())+"");
							value[i] = Double.parseDouble(string.get(tableChild.getName())+"");
						}else{
							value[i] = Double.parseDouble(string.get(tableChild.getName())+"");
						}
					}
				}
			}
			Vector vector = Vectors.dense(value);
			LabeledPoint labeledPoint = new LabeledPoint(last, vector);
			labeledPoints.add(labeledPoint);
		}

		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
		if(sc==null||sc.sc().isStopped()){
			sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
			session.removeAttribute("sc");
			session.setAttribute("sc", sc);
		}

		JavaRDD<LabeledPoint> DataSet = sc.parallelize(labeledPoints);

		Map<String, JavaRDD<LabeledPoint>> mapData = LogisticRegressionBinaryClassificationService.slitData(DataSet,1.0,0.0);

		return  mapData;
	}
	
	/**
	 * 
	  * @Title: filterFeature
	  * @Description: 过滤式特征选择 28
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String filterFeature(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//所有的列
		List<TableChild> tableChildList = (List<TableChild>)session.getAttribute("table"+id);
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);

		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());

		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<>();

		List<FeatureengineeringChildAssembly>  featureengineeringChildAssemblies = (List<FeatureengineeringChildAssembly>)session.getAttribute("featureengineeringChildAssemblies_28");

		for (LinkedHashMap<String,Object> e : resultList){
			LinkedHashMap<String,Object> map = new LinkedHashMap<>();
			for (TableChild o : tableChildList){
				if (e.containsKey(o.getName())){
					map.put(o.getName(),e.get(o.getName()));
				}
			}
			resultListnew.add(map);
		}

		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}

		return id;
	}
	
	/**
	 * 
	  * @Title: linearRegression
	  * @Description: 线性回归 50
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String linearRegression(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		JavaRDD<LabeledPoint> parsedData = mapData.get("trainingDataSet");
		
    	LinearRegressionModel linearRegressionModel = LinearRegressionService.train(parsedData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, linearRegressionModel);
		}else{
			session.setAttribute(id, linearRegressionModel);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: decisionTreeRegression
	  * @Description: 决策树回归 52
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String decisionTreeRegression(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		DecisionTreeModel decisionTreeModel = DecisionTreeRegressionService.train(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, decisionTreeModel);
		}else{
			session.setAttribute(id, decisionTreeModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: randomForestRegression
	  * @Description: 随机森林回归 53
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String randomForestRegression(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		RandomForestModel randomForestModel = RandomForestRegressionService.train(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, randomForestModel);
		}else{
			session.setAttribute(id, randomForestModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: gradientBoostedTreesRegression
	  * @Description: 梯度提升回归树 56
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String gradientBoostedTreesRegression(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		GradientBoostedTreesModel gradientBoostedTreesModel = GradientBoostedTreesRegressionService.train(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, gradientBoostedTreesModel);
		}else{
			session.setAttribute(id, gradientBoostedTreesModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: statisticalanalysis
	  * @Description: 统计分析 32-48
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String statisticalanalysis(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
    	List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
    	
		List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
		for (LinkedHashMap<String, Object> string : resultList) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>(); 
			for (int i = 0; i < tableChilds.size(); i++) {
				TableChild tableChild = tableChilds.get(i);
				if(string.get(tableChild.getName())!=null){//匹配选中的字段
					map.put(tableChild.getName(), string.get(tableChild.getName()));
				}
			}
			resultListnew.add(map);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: logisticRegressionBinaryClassification
	  * @Description: 逻辑回归二分类 57
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String logisticRegressionBinaryClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		LogisticRegressionModel LRmodel = LogisticRegressionBinaryClassificationService.buildModelandTraining(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, LRmodel);
		}else{
			session.setAttribute(id, LRmodel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: logisticRegressionMultiClassification
	  * @Description: 逻辑回归多分类 58
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String logisticRegressionMultiClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		LogisticRegressionModel LRmodel = LogisticRegressionMultiClassificationService.buildModelandTraining(mapData,3);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, LRmodel);
		}else{
			session.setAttribute(id, LRmodel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: decisionTreeClassification
	  * @Description: 决策树分类 59
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String decisionTreeClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		DecisionTreeModel decisionTreeModel = DecisionTreeClassificationService.train(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, decisionTreeModel);
		}else{
			session.setAttribute(id, decisionTreeModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: randomForestClassification
	  * @Description: 随机森林分类 60
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String randomForestClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		RandomForestModel randomForestModel = RandomForestClassificationService.trainClassifier(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, randomForestModel);
		}else{
			session.setAttribute(id, randomForestModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: gradientBoostingTreesClassification
	  * @Description: 梯度提升分类树 61
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String gradientBoostingTreesClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		GradientBoostedTreesModel gradientBoostedTreesModel = GradientBoostingTreesClassificationService.train(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, gradientBoostedTreesModel);
		}else{
			session.setAttribute(id, gradientBoostedTreesModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: kMeansBisecting
	  * @Description: KMeans聚类 63
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String kMeansBisecting(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//列数
		int count = (int)parms.get("count");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//数据重组，拼接
		List<Vector> vectors = new ArrayList<Vector>();
		for (LinkedHashMap<String, Object> string : resultList){
			int textCount = 0;
            Set<String> sets = string.keySet();
            double[] values = new double[count];
            for (String string2 : sets) {
				 if(textCount<count){
					 if(!Tools.isNumeric(string.get(string2)+"")){
						 values[textCount] = Double.parseDouble(string.get(string2)+"");
						 textCount++;
					 }
				 }
			}
            Vector vector = Vectors.dense(values);
            vectors.add(vector);
		}
		
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
		
		//List<Vector>转JavaRDD<Vector>
		JavaRDD<Vector> parsedData = sc.parallelize(vectors);
		
		KMeansModel kMeansModel = KMeansService.train(parsedData, 2, 20);
		
		session.removeAttribute("kMeansModel");
		session.setAttribute("kMeansModel", kMeansModel);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, parsedData);
		}else{
			session.setAttribute(id, parsedData);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: naiveBayesClassification
	  * @Description: 贝叶斯分类 64
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String naiveBayesClassification(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		NaiveBayesModel naiveBayesModel = NaiveBayesClassificationService.modelTrain(mapData);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, naiveBayesModel);
		}else{
			session.setAttribute(id, naiveBayesModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: svmClassifier
	  * @Description: 支持向量机分类 65
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String svmClassifier(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		SVMModel svmModel = SVMClassifierService.train(mapData,3);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, svmModel);
		}else{
			session.setAttribute(id, svmModel);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: fpGrowth
	  * @Description: 频繁项集挖掘算法66
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String fpGrowth(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		List<String> outList = new ArrayList<String>();
		for (LinkedHashMap<String, Object> linkedHashMap : resultList) {
			Set<String> set = linkedHashMap.keySet();
			for (String string : set) {
				outList.add(linkedHashMap.get(string)+"");
			}
		}
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
		if(sc==null||sc.sc().isStopped()){
			sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
			session.removeAttribute("sc");
			session.setAttribute("sc", sc);
		}
		
		JavaRDD<String> javaRDD = sc.parallelize(outList);
		
		JavaRDD<List<String>> transactions = FPGrowthService.transactions(javaRDD);
		
		FPGrowthModel<String> fpGrowthModel = FPGrowthService.model(transactions);
		
		Map<List<String>,Long> resultMap = FPGrowthService.itemset(fpGrowthModel);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultMap);
		}else{
			session.setAttribute(id, resultMap);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: collaborativeFiltering
	  * @Description: 协同过滤算法 67
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String collaborativeFiltering(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie.getId());
		
		//训练数据集
		List<LabeledPoint> trainingList = mapData.get("trainingDataSet").collect();
		//将List<LabeledPoint>转为List<Rating>
		List<Rating> ratings = new ArrayList<Rating>();
		for (LabeledPoint labeledPoint : trainingList) {
			double label = labeledPoint.label();
			Vector vector = labeledPoint.features();
			double[] testdob = vector.toArray();
			Rating rating = new Rating(Tools.stringToInt(testdob[0]+""),Tools.stringToInt(testdob[1]+""),label);
			ratings.add(rating);
		}
		
		//获取JavaSparkContext
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	
    	//转为JavaRDD<Rating>对象
    	JavaRDD<Rating> rating = sc.parallelize(ratings);
		
    	//生成协同过滤模型
		MatrixFactorizationModel  matrixFactorizationModel  = CollaborativeFilteringService.buildModelandTraining(rating);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, matrixFactorizationModel);
		}else{
			session.setAttribute(id, matrixFactorizationModel);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: collaborativeFilteringPrediction
	  * @Description: 推荐预测 68
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String  collaborativeFilteringPrediction(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String[] parentIds = parms.get("parentId").toString().split(",");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie0 = null;//拆分
		Assembly assemblie1 = null;//协同过滤算法
		for (int i = 0; i < parentIds.length; i++) {
			String parentId = parentIds[i];
			Assembly assemblie = (Assembly)parms.get(parentId);
			if("13".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//拆分
				assemblie1 = assemblie;
			}else if("67".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//协同过滤算法
				assemblie0 = assemblie;
			}
		}
		//训练和测试数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie1.getId());
		//获取协同过滤模型
		MatrixFactorizationModel matrixFactorizationModel = (MatrixFactorizationModel)session.getAttribute(assemblie0.getId());
		
		//训练数据集
		List<LabeledPoint> trainingList = mapData.get("trainingDataSet").collect();
		
		//List<LabeledPoint>转为List<Rating>
		List<Rating> ratings = new ArrayList<Rating>();
		for (LabeledPoint labeledPoint : trainingList) {
			double label = labeledPoint.label();
			Vector vector = labeledPoint.features();
			double[] testdob = vector.toArray();
			Rating rating = new Rating(Tools.stringToInt(testdob[0]+""),Tools.stringToInt(testdob[1]+""),label);
			ratings.add(rating);
		}
		
		//获取JavaSparkContext
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	
    	//转为JavaRDD<Rating>
    	JavaRDD<Rating> rating = sc.parallelize(ratings);
		
    	//对ratings数据提取userProducts
		JavaRDD<Tuple2<Object, Object>> userProducts =  CollaborativeFilteringService.userProducts(rating);
		
		//对userProducts进行predict预测
		JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = CollaborativeFilteringService.predictions(userProducts, matrixFactorizationModel);
		
		//获取预测结果
		JavaRDD<Tuple2<Double, Double>> predictionAndLabels =  CollaborativeFilteringService.ratesAndPreds(rating, predictions);
		
		//将测试结果集转为List<Tuple2<Double, Double>>
		List<Tuple2<Double, Double>> tuplelist = predictionAndLabels.collect();
		
		//预测值
		List<Map<Double,Double>> outlist = new ArrayList<Map<Double,Double>>();
		
		//遍历测试的真实值和预测值
		for (Tuple2<Double, Double> tuple2 : tuplelist) {
			Map<Double,Double> map = new HashMap<Double,Double>();
			map.put(tuple2._1(),tuple2._2());
			outlist.add(map);
		}
		session.removeAttribute("outlist"+id);
		session.setAttribute("outlist"+id, outlist);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, predictionAndLabels);
		}else{
			session.setAttribute(id, predictionAndLabels);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: classifiedPrediction
	  * @Description: 分类预测 69
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String  classifiedPrediction(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String[] parentIds = parms.get("parentId").toString().split(",");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie0 = null;//拆分
		Assembly assemblie1 = null;//逻辑回归二分类
		for (int i = 0; i < parentIds.length; i++) {
			String parentId = parentIds[i];
			Assembly assemblie = (Assembly)parms.get(parentId);
			if("13".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//拆分
				assemblie1 = assemblie;
			}else if("57".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//逻辑回归二分类
				assemblie0 = assemblie;
			}
		}
		
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie1.getId());
		
		LogisticRegressionModel LRmodel = (LogisticRegressionModel)session.getAttribute(assemblie0.getId());
		
		JavaRDD<Tuple2<Object, Object>> predictionAndLabels = LogisticRegressionBinaryClassificationService.getPredictionAndLabels(mapData, LRmodel);
		
		List<Tuple2<Object, Object>> tuplelist = predictionAndLabels.collect();
		
		//预测值
		List<Map<Object,Object>> outlist = new ArrayList<Map<Object,Object>>();
		
		for (Tuple2<Object, Object> tuple2 : tuplelist) {
			Map<Object,Object> map = new HashMap<Object,Object>();
			map.put(tuple2._1(),tuple2._2());
			outlist.add(map);
		}
		session.removeAttribute("outlist"+id);
		session.setAttribute("outlist"+id, outlist);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, predictionAndLabels);
		}else{
			session.setAttribute(id, predictionAndLabels);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: regressorPredict
	  * @Description: 回归预测 70
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String  regressorPredict(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String[] parentIds = parms.get("parentId").toString().split(",");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie0 = null;//决策树回归、线性回归
		Assembly assemblie1 = null;//拆分
		for (int i = 0; i < parentIds.length; i++) {
			String parentId = parentIds[i];
			Assembly assemblie = (Assembly)parms.get(parentId);
			if("13".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//拆分
				assemblie1 = assemblie;
			}else if("52".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//决策树回归
				assemblie0 = assemblie;
			}else if("50".equals(assemblie.getBdpfMllibAssemblyLibraryId())){//线性回归
				assemblie0 = assemblie;
			}
		}
		//获取上一个组件的数据
		Map<String, JavaRDD<LabeledPoint>> mapData = (Map<String, JavaRDD<LabeledPoint>>)session.getAttribute(assemblie1.getId());
		//获取回归模型
		if (assemblie0 != null && "52".equals(assemblie0.getBdpfMllibAssemblyLibraryId())){
			DecisionTreeModel decisionTreeModel = (DecisionTreeModel) session.getAttribute(assemblie0.getId());

			//获取测试结果集
			JavaRDD<LabeledPoint> parsedData = mapData.get("testDataSet");
			//评估 训练样本模型 并计算 训练误差
			JavaRDD<Tuple2<Object, Object>> predictionAndLabels = DecisionTreeRegressionService.getValuesAndPreds2(parsedData,decisionTreeModel);
			//JavaRDD转为List
			List<Tuple2<Object, Object>> tuplelist = predictionAndLabels.collect();
			//预测值和真实值
			List<Map<Object,Object>> outlist = new ArrayList<Map<Object,Object>>();

			//遍历，拼接需要的数据
			for (Tuple2<Object, Object> tuple2 : tuplelist) {
				Map<Object,Object> map = new HashMap<Object,Object>();
				map.put(Tools.doubleNumberFormat(Double.parseDouble((tuple2._1()+"").substring(0, 10)), 4),tuple2._2());
				outlist.add(map);
			}
			session.removeAttribute("outlist"+id);
			session.setAttribute("outlist"+id, outlist);
			//将当前缓存值放入到session中
			if(session.getAttribute(id)!=null){
				session.removeAttribute(id);
				session.setAttribute(id, predictionAndLabels);
			}else{
				session.setAttribute(id, predictionAndLabels);
			}
		}else{
			LinearRegressionModel linearRegressionModel = (LinearRegressionModel)session.getAttribute(assemblie0.getId());


			//获取测试结果集
			JavaRDD<LabeledPoint> parsedData = mapData.get("testDataSet");

			//评估 训练样本模型 并计算 训练误差
			JavaRDD<Tuple2<Object, Object>> predictionAndLabels = LinearRegressionService.getValuesAndPreds(parsedData,linearRegressionModel);

			//JavaRDD转为List
			List<Tuple2<Object, Object>> tuplelist = predictionAndLabels.collect();

			//预测值和真实值
			List<Map<Object,Object>> outlist = new ArrayList<Map<Object,Object>>();

			//遍历，拼接需要的数据
			for (Tuple2<Object, Object> tuple2 : tuplelist) {
				Map<Object,Object> map = new HashMap<Object,Object>();
				map.put(Tools.doubleNumberFormat(Double.parseDouble((tuple2._1()+"").substring(0, 10)), 4),tuple2._2());
				outlist.add(map);
			}
			session.removeAttribute("outlist"+id);
			session.setAttribute("outlist"+id, outlist);
			//将当前缓存值放入到session中
			if(session.getAttribute(id)!=null){
				session.removeAttribute(id);
				session.setAttribute(id, predictionAndLabels);
			}else{
				session.setAttribute(id, predictionAndLabels);
			}
		}

		return id;
	}
	
	/**
	 * 
	  * @Title: bisectingPredict
	  * @Description: 聚类预测 71
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String  bisectingPredict(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		JavaRDD<Vector> parsedData = (JavaRDD<Vector>)session.getAttribute(assemblie.getId());
		
		KMeansModel kMeansModel = (KMeansModel)session.getAttribute("kMeansModel");
		
		JavaRDD<Integer> javaRDD = KMeansService.predict(kMeansModel, parsedData);
		
		List<Integer> outlist = javaRDD.collect();
		
		//预测值
		session.removeAttribute("outlist"+id);
		session.setAttribute("outlist"+id, outlist);
		
		//kMeans聚类模型
		session.removeAttribute("kMeansModel");
		session.setAttribute("kMeansModel", kMeansModel);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, parsedData);
		}else{
			session.setAttribute(id, parsedData);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: collaborativeFilteringEvaluation
	  * @Description: 推荐评估 73
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String collaborativeFilteringEvaluation(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//预测结果集
		JavaRDD<Tuple2<Double, Double>> predictionAndLabels = (JavaRDD<Tuple2<Double, Double>>)session.getAttribute(assemblie.getId());
		
		//计算 MSE
		double mse =  CollaborativeFilteringService.MSE(predictionAndLabels);
    	
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, mse);
		}else{
			session.setAttribute(id, mse);
		}
		return id;
	}
	
	/**
	 * 
	  * @Title: classifiedEvaluation
	  * @Description: 分类评估 75
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String classifiedEvaluation(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		JavaRDD<Tuple2<Object, Object>> predictionAndLabels = (JavaRDD<Tuple2<Object, Object>>)session.getAttribute(assemblie.getId());
		
		BinaryClassificationMetrics metrics = LogisticRegressionBinaryClassificationService.getMetrics(predictionAndLabels);
		
    	Map<String, JavaRDD<Tuple2<Object, Object>>> javaRddMap = LogisticRegressionBinaryClassificationService.metricsDetails(metrics);
    	
    	//返回结果
    	List<Classified> classifieds = new ArrayList<Classified>();
    	//准确率
		JavaRDD<Tuple2<Object, Object>> precisions = javaRddMap.get("precision");
    	List<Tuple2<Object, Object>> precisionlist =  precisions.collect();
    	for (Tuple2<Object, Object> tuple2 : precisionlist) {
    		Classified classified = new Classified();
    		classified.setLabel(tuple2._1()+"");
    		classified.setPrecision(Tools.doubleNumberFormat(Tools.stringToDouble(tuple2._2()+""), 4));
    		classifieds.add(classified);
		}
    	
    	//召回率
    	JavaRDD<Tuple2<Object, Object>> recalls = javaRddMap.get("recall");
    	List<Tuple2<Object, Object>> recallslist =  recalls.collect();
    	for (Tuple2<Object, Object> tuple2 : recallslist) {
    		for (Classified classified : classifieds) {
				if(classified.getLabel().equals(tuple2._1()+"")){
					classified.setRecall(Tools.doubleNumberFormat(Tools.stringToDouble(tuple2._2()+""), 4));
				}
			}
		}
    	
    	//F1值
    	JavaRDD<Tuple2<Object, Object>> f1Score = javaRddMap.get("f1Score");
    	List<Tuple2<Object, Object>> f1Scorelist =  f1Score.collect();
    	for (Tuple2<Object, Object> tuple2 : f1Scorelist) {
    		for (Classified classified : classifieds) {
				if(classified.getLabel().equals(tuple2._1()+"")){
					classified.setF1Score(Tools.doubleNumberFormat(Tools.stringToDouble(tuple2._2()+""), 4));
				}
			}
		}
    	
    	//F2值
    	JavaRDD<Tuple2<Object, Object>> f2Score = javaRddMap.get("f2Score");
    	List<Tuple2<Object, Object>> f2Scorelist =  f2Score.collect();
    	for (Tuple2<Object, Object> tuple2 : f2Scorelist) {
    		for (Classified classified : classifieds) {
				if(classified.getLabel().equals(tuple2._1()+"")){
					classified.setF2Score(Tools.doubleNumberFormat(Tools.stringToDouble(tuple2._2()+""), 4));
				}
			}
		}
    	//AUC:
    	Map<String, Object> areaUnderMap =  LogisticRegressionBinaryClassificationService.prcAndRoc(metrics);
    	double auprc = Double.parseDouble(areaUnderMap.get("AUPRC")+"");
    	double auroc = Double.parseDouble(areaUnderMap.get("AUROC")+"");
    	
    	session.removeAttribute("auc");
		session.setAttribute("auc", Tools.doubleNumberFormat(((auprc+auroc)/2),4));
    	session.removeAttribute("classifieds");
		session.setAttribute("classifieds", classifieds);
		
		return id;
	}
	
	/**
	 * 
	  * @Title: regressorEvaluation
	  * @Description: 回归评估 77
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String regressorEvaluation(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		JavaRDD<Tuple2<Object, Object>> predictionAndLabels = (JavaRDD<Tuple2<Object, Object>>)session.getAttribute(assemblie.getId());
		
		Map<String,String> metricsMap = LinearRegressionService.metricsMap(predictionAndLabels);
		
		session.removeAttribute("metricsMap");
		session.setAttribute("metricsMap", metricsMap);
		
		return id;
	}
	
	/**
	 * 
	  * @Title: bisectingEvaluation
	  * @Description: 聚类评估 79
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String bisectingEvaluation(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		JavaRDD<Vector> parsedData = (JavaRDD<Vector>)session.getAttribute(assemblie.getId());
		
		KMeansModel kMeansModel = (KMeansModel)session.getAttribute("kMeansModel");
		
		//计算 WSSSE
		double WSSSE = KMeansService.computeCost(kMeansModel, parsedData);
		
		session.removeAttribute(id);
		session.setAttribute(id, Tools.doubleNumberFormat(WSSSE,4));
		
		return id;
	}
	
	/**
	 * 
	  * @Title: documentGenerationModel
	  * @Description: 文档主题生成模型 81
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String documentGenerationModel(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//取的条数
		int count = (int)parms.get("count");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//上个组件的结果集
		List<LinkedHashMap<String, Integer>> resultList = (List<LinkedHashMap<String, Integer>>)session.getAttribute(assemblie.getId());
		
		//JavaSparkContext连接
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	
		//数据重组，拼接
		List<Vector> vectors = new ArrayList<Vector>();
		for (LinkedHashMap<String, Integer> string : resultList){
			int textCount = 0;
            Set<String> sets = string.keySet();
            double[] values = new double[count];
            for (String string2 : sets) {
				 if(textCount<count){
					 values[textCount] = string.get(string2);
					 textCount++;
				 }
			}
            Vector vector = Vectors.dense(values);
            vectors.add(vector);
		}
		
		//List<Vector>转JavaRDD<Vector>
		JavaRDD<Vector> parsedData = sc.parallelize(vectors);
		
		//给每篇文章增加一个唯一ID
		JavaPairRDD<Long, Vector> corpus = LDA_Service.getCorpus(parsedData);
		
		//生成LDA模型
		DistributedLDAModel ldaModel = LDA_Service.ldaModel(corpus, 3);
		
		//获取top
		List<LinkedHashMap<String, Object>> resultMap = LDA_Service.topics(ldaModel);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultMap);
		}else{
			session.setAttribute(id, resultMap);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: characteristicsReverseFile
	  * @Description: 词频－逆向文件频率特征 82
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String characteristicsReverseFile(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//父组件Id
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//上个组件的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//数据遍历，拼接成需要的LIST
		List<String> outList = new ArrayList<String>();
		for (LinkedHashMap<String, Object> string : resultList) {
			Set<String> sets = string.keySet();
			for (String string2 : sets) {
				outList.add(string.get(string2)+"");
			}
		}
		
		//JavaSparkContext连接
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	//将List<String>转为JavaRDD<String>
    	JavaRDD<String> text = sc.parallelize(outList);
    	
    	//将输入文档转化为词频向量
    	JavaRDD<Vector> tfVectors = tfIDF.tf(text);
    	
    	//构建模型
		IDFModel modelIDF = tfIDF.buildIDFmodel(tfVectors);
		
		//将TF向量转换为TF-IDF向量
		JavaRDD<Vector> tf2idf = tfIDF.tfidf(modelIDF,tfVectors);
    	
		//获取List<Vector>集合
		List<Vector> list = tf2idf.collect();
		
		//将List<Vector>转为List<Classified>
		List<Classified> outlist = new ArrayList<Classified>();
		for (Vector vector : list) {
			Classified classified = new Classified();
			int[] ss = vector.toSparse().indices();
			String f1 = "";
			String f2 = "";
			for (int i = 0; i < ss.length; i++) {
				f1+=ss[i]+",";
			}
			double[]  dd = vector.toSparse().values();
			for (int i = 0; i < dd.length; i++) {
				f2+=dd[i]+",";
			}
			if(f1!=null&&f1.split(",").length>0){
				classified.setF1Score(f1.substring(0,f1.length()-1));
			}
			if(f2!=null&&f2.split(",").length>0){
				classified.setF2Score(f2.substring(0,f2.length()-1));
			}
			outlist.add(classified);
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, outlist);
		}else{
			session.setAttribute(id, outlist);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: stringSimilarity
	  * @Description: 字符串相似度 83
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String stringSimilarity(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//父组件Id
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//上个组件的结果集
		Word2VecModel word2VecModel = (Word2VecModel)session.getAttribute(assemblie.getId());
		
		//计算字符串余弦相似度
		List<Row>  rowlists =  Word2VecService.cosineSimilarity(word2VecModel, "regression", 2);
		
		for (Row row : rowlists) {
			System.out.println(row);
		}
		
		//将当前缓存值放入到session中
//		if(session.getAttribute(id)!=null){
//			session.removeAttribute(id);
//			session.setAttribute(id, resultList);
//		}else{
//			session.setAttribute(id, resultList);
//		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: stopWordFiltering
	  * @Description: 停用词过滤 84
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String stopWordFiltering(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//splitWord字段
		List<TableChild> textAnalysisAssemblies = (List<TableChild>)session.getAttribute("textAnalysisAssemblies");
		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		
		//根据选择的字段和过滤条件进行遍历
		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			for (int i = 0; i < textAnalysisAssemblies.size(); i++){
				TableChild textAnalysisAssembly = textAnalysisAssemblies.get(i);
				if(string.get(textAnalysisAssembly.getName())!=null){//匹配选中的字段
					map.put(textAnalysisAssembly.getName(), Tools.splitWord(string.get(textAnalysisAssembly.getName())+"",0));
				}
			}
			if(map.size()>0){
				resultListnew.add(map);
			}
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: word2Vec
	  * @Description: word2Vec 85
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String word2Vec(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//父组件Id
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//列数量
		int count = (int)parms.get("count");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//上个组件的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//JavaSparkContext连接
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
    	if(sc==null||sc.sc().isStopped()){
    		sc = AlgorithmFactory.initJavaSparkEnv("Classification",4);
    		session.removeAttribute("sc");
    		session.setAttribute("sc", sc);
    	}
    	
    	List<Row> rowlist = new ArrayList<Row>();
    	for (LinkedHashMap<String, Object> string : resultList) {
			Set<String> sets = string.keySet();
			for (String string2 : sets) {
				Row row = RowFactory.create(Arrays.asList(string.get(string2)+""));
	    		rowlist.add(row);
			}
		}
    	
		JavaRDD<Row> roJavaRDD = sc.parallelize(rowlist);
		
		// 1 创建sqlContext
		SQLContext sqlContext = new org.apache.spark.sql.SQLContext(sc);
		
		// 3 Schema构建
		StructType schema = Word2VecService.schemaBuilder();
		// 4 创建DataFrame
		DataFrame documentDF = Word2VecService.createDF(sqlContext,roJavaRDD,schema);
		// 5 学习从单词到向量的映射
		Word2Vec word2Vec = Word2VecService.Word2VecMap(count);
		// 6  词转向量 模型构建
		Word2VecModel model = Word2VecService.modelBuild(word2Vec,documentDF);
		// 7 Word2Vec 转换操作
		DataFrame result = Word2VecService.transform(model,documentDF);
		// 8 循环打印结果
		List<String> outlist = new ArrayList<String>();
		for (Row r : result.select("result").takeAsList(rowlist.size())) {
			outlist.add(r.toString());
		}
		
		//TODO
		session.removeAttribute("outlist");
		session.setAttribute("outlist", outlist);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, model);
		}else{
			session.setAttribute(id, model);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: frequencyCount
	  * @Description: 词频统计 86
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String frequencyCount(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//splitWord字段
		List<TextAnalysisAssembly> textAnalysisAssemblies = (List<TextAnalysisAssembly>)session.getAttribute("textAnalysisAssemblies");
		//最新的结果集
		List<LinkedHashMap<String, Integer>> resultListnew = new ArrayList<LinkedHashMap<String, Integer>>();
		
//		Map<Integer,List<Integer>> resultMap  = new HashMap<Integer,List<Integer>>();
		//根据选择的字段和过滤条件进行遍历
//		int appendIdCount = 0;
		for (LinkedHashMap<String, Object> string : resultList){
			LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
//			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < textAnalysisAssemblies.size(); i++){
				TextAnalysisAssembly textAnalysisAssembly = textAnalysisAssemblies.get(i);
				if(string.get(textAnalysisAssembly.getFilterColumn())!=null){//匹配选中的字段
					map.putAll(Tools.frequencyCount(string.get(textAnalysisAssembly.getFilterColumn())+"",map));
				}
			}
//			int textCount = 0;
			if(map!=null&&map.size()>0){
				resultListnew.add(map);
//				Set<String> sets = map.keySet();
//				for (String string2 : sets) {
//					if(textCount<count){
//						list.add(map.get(string2));
//					}
//					textCount ++;
//				}
//				resultMap.put(appendIdCount++, list);
			}
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: splitWord
	  * @Description: splitWord 87
	  *  @param parms
	  *  @return String 返回类型
	  * @throws
	 */
	public static String splitWord(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//当前组件的父组件ID
		String parentId = (String)parms.get("parentId");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//父组件相关信息
		Assembly assemblie = (Assembly)parms.get(parentId);
		
		//通过表名和列查询对应的结果集
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(assemblie.getId());
		
		//splitWord字段
		List<TableChild> textAnalysisAssemblies = (List<TableChild>)session.getAttribute("table"+assemblie.getId());
		//最新的结果集
		List<LinkedHashMap<String, Object>> resultListnew  = new ArrayList<LinkedHashMap<String, Object>>();
		
		//根据选择的字段和过滤条件进行遍历
		for (LinkedHashMap<String, Object> string : resultList){
    		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			for (int i = 0; i < textAnalysisAssemblies.size(); i++){
				TableChild textAnalysisAssembly = textAnalysisAssemblies.get(i);
				if(string.get(textAnalysisAssembly.getName())!=null){//匹配选中的字段
					map.put(textAnalysisAssembly.getName(), Tools.splitWord(string.get(textAnalysisAssembly.getName())+"",1));
				}
			}
			if(map.size()>0){
				resultListnew.add(map);
			}
		}
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultListnew);
		}else{
			session.setAttribute(id, resultListnew);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: sqlScript
	  * @Description: sql脚本 91
	  *  @param parms
	  *  @return  String 返回类型
	  * @throws
	 */
	public static String sqlScript(Map<String,Object> parms){
		//当前组件的主键ID
		String id = (String)parms.get("id");
		//缓存session
		HttpSession session = (HttpSession) parms.get("session");
		
		//通过表名和列查询对应的结果集
		SqlAssembly sqlAssembly = (SqlAssembly)session.getAttribute("sqlAssembly");
		
		List<LinkedHashMap<String, Object>> resultList = queryColumnValues(sqlAssembly.getSqlScript(), "", "",session);
		
		//将当前缓存值放入到session中
		if(session.getAttribute(id)!=null){
			session.removeAttribute(id);
			session.setAttribute(id, resultList);
		}else{
			session.setAttribute(id, resultList);
		}
		
		return id;
	}
	
	/**
	 * 
	  * @Title: queryColumnValues
	  * @Description:通过结果集元数据封装List结果集
	  * @param sqlScript String 字段集合
	  * @param tablename String 表名称
	  * @param orderName String 排序字段名称
	  * @return Map<String,Object> 返回类型
	  * @throws
	 */
	public static List<TableChild> queryColumnValues(String sqlScript){
		List<TableChild> columnNamelist = new ArrayList<TableChild>();
		//通用List结果集
		Connection connection = null;//jdbc连接对象
		PreparedStatement ps = null;//处理sql对象
		ResultSet rs = null;//结果集
		try {
			//SQL判空
			if(sqlScript==null||"".equals(sqlScript)){
				return columnNamelist;
			}
			//获取jdbc连接对象
			connection = JDBCUtil.getConnection();
			//处理好拼接好的SQL
			ps = connection.prepareStatement(sqlScript);
			//获取结果集
			rs = ps.executeQuery();
			
			//定义一个返回结果集map
			LinkedHashMap<String, Object>  resultHashMap = null;
			
			//获取所表有关信息
			ResultSetMetaData data = rs.getMetaData();
			
			//字段名称
			
			for (int i = 1; i <= data.getColumnCount(); i++) {
				TableChild tableChild = new TableChild();
				tableChild.setName(data.getColumnName(i));
				if("VARCHAR".equals(data.getColumnTypeName(i))){
					tableChild.setType("string");
				}else{
					tableChild.setType("string");
				}
				columnNamelist.add(tableChild);
			}
			
			return columnNamelist;
		} catch (Exception e) {
			e.printStackTrace();
			return columnNamelist;
		}finally {
			JDBCUtil.close(connection, rs, ps);
		}		
	}
	
	/**
	 * 
	  * @Title: queryColumnValues
	  * @Description:通过结果集元数据封装List结果集
	  * @param sqlScript String 字段集合
	  * @param tablename String 表名称
	  * @param limitName String 取前多少条数据
	  * @return Map<String,Object> 返回类型
	  * @throws
	 */
	public static List<LinkedHashMap<String, Object>> queryColumnValues(String sqlScript,String tablename,String limitName,HttpSession session){
		//通用List结果集
		List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
		Connection connection = null;//jdbc连接对象
		PreparedStatement ps = null;//处理sql对象
		ResultSet rs = null;//结果集
		try {
			//SQL判空
			if(sqlScript==null||"".equals(sqlScript)){
				return resultList;
			}
			//获取jdbc连接对象
			connection = JDBCUtil.getConnection();
			//进行SQL拼接
			
			//如果需要取固定的数据，则追加到SQL后即可
			if(limitName!=null&&!"".equals(limitName)){
				sqlScript +=" LIMIT 0,"+limitName;
			}
			//处理好拼接好的SQL
			ps = connection.prepareStatement(sqlScript);
			//获取结果集
			rs = ps.executeQuery();
			
			//定义一个返回结果集map
			LinkedHashMap<String, Object>  resultHashMap = null;
			
			//获取所表有关信息
			ResultSetMetaData data = rs.getMetaData();
			
			//字段名称
			List<TableChild> columnNamelist = new ArrayList<TableChild>();
			for (int i = 1; i <= data.getColumnCount(); i++) {
				TableChild tableChild = new TableChild();
				tableChild.setName(data.getColumnName(i));
				if("VARCHAR".equals(data.getColumnTypeName(i))){
					tableChild.setType("string");
				}else{
					tableChild.setType("string");
				}
				columnNamelist.add(tableChild);
			}
			
			//进行循环，取到所有的值
			while(rs.next()){
				resultHashMap = new LinkedHashMap<String, Object>();
				//遍历所有字段并放入到resultMap中
				for (TableChild tableChilds : columnNamelist){
					//字段类型判断
					if("int".equals(tableChilds.getType())){//bigint类型
						resultHashMap.put(tableChilds.getName(), rs.getInt(tableChilds.getName()));
					}else if("double".equals(tableChilds.getType())){//double类型
						resultHashMap.put(tableChilds.getName(), rs.getDouble(tableChilds.getName()));
					}else if("decimal".equals(tableChilds.getType())){//decimal类型
						resultHashMap.put(tableChilds.getName(), rs.getBigDecimal(tableChilds.getName()));
					}else if("string".equals(tableChilds.getType())){//string字符串
						resultHashMap.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
					}else if("boolean".equals(tableChilds.getType())){//boolean类型
						resultHashMap.put(tableChilds.getName(), rs.getBoolean(tableChilds.getName()));
					}else if("datetime".equals(tableChilds.getType())){//datetime 日期类型
						resultHashMap.put(tableChilds.getName(), rs.getTimestamp(tableChilds.getName()));
					}else{//其它类型
						resultHashMap.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
					}
				}
				//将当前resultMap放入resultList
				resultList.add(resultHashMap);
			}
			session.removeAttribute("columnNamelist");
			session.setAttribute("columnNamelist", columnNamelist);
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}finally {
			JDBCUtil.close(connection, rs, ps);
		}		
	}
	
	/**
	 * 
	  * @Title: queryColumnValues
	  * @Description:通过结果集元数据封装List结果集
	  * @param tableChild List<TableChild> 字段集合
	  * @param tablename String 表名称
	  * @param limitName String 取前多少条数据
	  * @return List<Map<String, Object>> 返回类型
	  * @throws
	 */
	public static List<LinkedHashMap<String, Object>> queryColumnValues(List<TableChild> tableChild,String tablename,String limitName){
		//通用List结果集
		List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
		Connection connection = null;//jdbc连接对象
		PreparedStatement ps = null;//处理sql对象
		ResultSet rs = null;//结果集
		String sql = "";//sql脚本
		String columnValues = "";//字段字符串
		//遍历所有字段，进行拼接
		for (TableChild tableChilds : tableChild) {
			columnValues += tableChilds.getName()+",";
		}
		//如果字段不为空，则去掉最后一个逗号
		if(!"".equals(columnValues)){
			columnValues = columnValues.substring(0,columnValues.length()-1);
		}
		try {
			//获取jdbc连接对象
			connection = JDBCUtil.getConnection();
			//进行SQL拼接
			sql = "SELECT "+columnValues+" FROM "+tablename+"";
			//如果需要取固定的数据，则追加到SQL后即可
			if(limitName!=null&&!"".equals(limitName)){
				limitName +=" LIMIT 0,"+limitName;
			}
			//处理好拼接好的SQL
			ps = connection.prepareStatement(sql);
			//获取结果集
			rs = ps.executeQuery();
			
			//定义一个返回结果集map
			LinkedHashMap<String, Object>  resultMap = null;
			
			//进行循环，取到所有的值
			while(rs.next()){
				
				resultMap = new LinkedHashMap<String, Object>();
				//遍历所有字段并放入到resultMap中
				for (TableChild tableChilds : tableChild) {
					//字段类型判断
					if("bigint".equals(tableChilds.getType())){//bigint类型
						resultMap.put(tableChilds.getName(), rs.getInt(tableChilds.getName()));
					}else if("double".equals(tableChilds.getType())){//double类型
						resultMap.put(tableChilds.getName(), rs.getDouble(tableChilds.getName()));
					}else if("decimal".equals(tableChilds.getType())){//decimal类型
						resultMap.put(tableChilds.getName(), rs.getBigDecimal(tableChilds.getName()));
					}else if("string".equals(tableChilds.getType())){//string字符串
						resultMap.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
					}else if("boolean".equals(tableChilds.getType())){//boolean类型
						resultMap.put(tableChilds.getName(), rs.getBoolean(tableChilds.getName()));
					}else if("datetime".equals(tableChilds.getType())){//datetime 日期类型
						resultMap.put(tableChilds.getName(), rs.getTimestamp(tableChilds.getName()));
					}else{//其它类型
						resultMap.put(tableChilds.getName(), rs.getString(tableChilds.getName()));
					}
				}
				//将当前resultMap放入resultList
				resultList.add(resultMap);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}finally {
			JDBCUtil.close(connection, rs, ps);
		}		
	}
	
    /**
     * 缺失值填充当前列随机取值
     * @param resultList
     * @param columnName
     * @return
     */
    public static Object replaceValue(List<LinkedHashMap<String,Object>> resultList,String columnName){

        Map<String,List<Object>> name_values_map = new HashMap<>();
        List<Object> valueList = new ArrayList<>();
        for (LinkedHashMap<String,Object> e : resultList){
            Object o = e.get(columnName);
            if (o == null || "".equals(o))
                continue;
            valueList.add(o);
            name_values_map.put(columnName,valueList);
        }
        Random r = new Random();
        return (valueList.get(r.nextInt(valueList.size())) == null
                || valueList.get(r.nextInt(valueList.size())) == "")
                ? 0 :valueList.get(r.nextInt(valueList.size()));//当前列随机值
//		switch (columnType){
//			case "int":
//				Integer s = 0;
//				for (Object i : valueList){
//					s += (Integer) i;
//				}
//				return s/num;//平均值
//			case "bigint":
//				BigInteger sb = new BigInteger("0");
//				for (Object i : valueList){
//					sb.add(new BigInteger(i.toString()));
//				}
//				return sb.divide(new BigInteger(String.valueOf(num)));//平均值
//			case "double":
//				BigDecimal bd  = new BigDecimal("0");
//				for (Object i : valueList){
//					bd.add(new BigDecimal(i.toString()));
//				}
//				return bd.divide(new BigDecimal(String.valueOf(num)));//平均值
//			case "string":
//				return valueList.get(num);//当列随机值
//			case "boolean":
//				return valueList.get(num);
//		}

    }

	/**
	 * 查询JOIN结果集
	 * @param sql
	 * @return
	 */
	public  static List<LinkedHashMap<String,Object>> queryResult(String sql){
		Connection connection = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		LinkedHashMap<String,Object> map ;
		List<LinkedHashMap<String,Object>> resultList = new ArrayList<>();
		try {
			connection =JDBCUtil.getConnection();
			//sql = "SELECT income,cp,education FROM adult a ,heart_disease_prediction b WHERE  a.age = b.age";
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

	
}
