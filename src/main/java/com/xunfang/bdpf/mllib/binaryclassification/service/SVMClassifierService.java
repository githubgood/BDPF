package com.xunfang.bdpf.mllib.binaryclassification.service;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import scala.Tuple2;

public class SVMClassifierService implements Serializable {

	/**
	* @Fields serialVersionUID : 
	* TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5206326555263151019L;
	
	/**
	 * 0 初始化环境配置
	 */
	 static SparkContext sc;
	 private String[] name;
	 
	 public SVMClassifierService(String[] name) {
		 this.name = name;
		 this.sc = AlgorithmFactory.initSparkEnv("SVMClassifier",4);
	 }
	 /**
	  * 1 加载解析数据
	  * @param sc
	  * @param path
	  * @return JavaRDD<LabeledPoint>
	  */
	 public static JavaRDD<LabeledPoint> load(SparkContext sc,String path){
		 JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sc,path).toJavaRDD();
		 return data;
	 }
	 /**
	  * 2 采样数据集（例如返回整个数据集的60%作为训练集  40%作为测试集）
	  * @param data 数据集
	  * @param fraction 分数 （0.6等）
	  * @return map,包含训练数据集、测试数据集
	  */
	 public static Map<String, JavaRDD<LabeledPoint>> sampleDataSet(JavaRDD<LabeledPoint> data,Double fraction){
		 Map<String, JavaRDD<LabeledPoint>> mapData = new HashMap<String, JavaRDD<LabeledPoint>>();
		 
		 JavaRDD<LabeledPoint> training = data.sample(false, fraction, 11L).cache();
	     JavaRDD<LabeledPoint> test = data.subtract(training);
	     
	     mapData.put("trainingDataSet", training);
	     mapData.put("testDataSet", test);
	     
	     return mapData;
	 }
	 
	 /**
	  * 3 运行训练算法，构建基于SGD-随机梯度下降的SVM-支持向量机 分类模型
	  * @param mapData
	  * @param numIterations
	  * @return SVMModel
	  */
     public static SVMModel train(Map<String, JavaRDD<LabeledPoint>> mapData,int numIterations){
    	 SVMModel model = SVMWithSGD.train(mapData.get("trainingDataSet").rdd(),numIterations);
    	 return model;
     }
     /**
      * 4 清除阈值,使“预测”将输出原始预测分数
      * @param model
      * @return
      */
     public static SVMModel clearThreshold(SVMModel model){
    	 return model.clearThreshold();
     }
     
     /**
      * 5 在测试集上计算原始分数
      */
     public static JavaRDD<Tuple2<Object, Object>> getScoreAndLabels
     (Map<String, JavaRDD<LabeledPoint>> mapData,final SVMModel svmModel){
    	 JavaRDD<LabeledPoint> test = mapData.get("testDataSet");
    	 return test.map(
                 new Function<LabeledPoint, Tuple2<Object, Object>>() {
                     public Tuple2<Object, Object> call(LabeledPoint p) {
                         Double score = svmModel.predict(p.features());
                         return new Tuple2<Object, Object>(score, p.label());
                     }
                 });
     }
     /**
      * 6 计算得到评估指标：auROC
      *
      */
     public static double getBinaryClassificationMetrics(JavaRDD<Tuple2<Object, Object>> scoreAndLabels){
    	 BinaryClassificationMetrics metrics = 
    			 new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
         return metrics.areaUnderROC();
     }

     /**
      * 7 保存模型
      */
     public static void saveModel(SparkContext sc,SVMModel model,String path){
    	 model.save(sc, path); 
     }
     
     /**
      * 8 加载模型
      * @param sc
      * @param model
      * @param path
      * @return
      */
     public static SVMModel loadSVMModel(SparkContext sc,String path){
    	 return SVMModel.load(sc, path);
     }
     
     /**
      * 9 停止SC对象
      * @param sc
      */
     public static void stopSC(SparkContext sc){
    	 AlgorithmFactory.stopSparkContext(sc);
     }
     /**
      * 测试
      * @param args
      */
	public static void main(String[] args) {
		
		//初始化环境
		sc = AlgorithmFactory.initSparkEnv("SVMClassifierService", 4);
		
		// 加载数据
		JavaRDD<LabeledPoint> DataSet = load(sc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt");
		// 切分数据
		Map<String, JavaRDD<LabeledPoint>> map = sampleDataSet(DataSet,0.6);
		// 训练模型
		SVMModel svmModel=train(map,100);
		svmModel.clearThreshold();
		// 计算原始分数
		JavaRDD<Tuple2<Object,Object>> scoreAndLabels = getScoreAndLabels(map,svmModel);
		// 计算得到 auROC
		getBinaryClassificationMetrics(scoreAndLabels);
		// 打印 auROC
		System.out.println(getBinaryClassificationMetrics(scoreAndLabels));
		// 保存模型
		saveModel(sc,svmModel,"C:/Users/16273/Desktop/sparkTemp2017xunfang/");
		// 加载模型
		loadSVMModel(sc,"C:/Users/16273/Desktop/sparkTemp2017xunfang/");
		// 停止SC对象
		stopSC(sc);
		
	}

}
