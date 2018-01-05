package com.xunfang.bdpf.mllib.multiclassification.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import scala.Tuple2;

public class LogisticRegressionMultiClassificationService {

	private static final long serialVersionUID = -5206326555263151019L;

	/**
	 * 0 初始化环境配置
	 */
	static SparkContext sc;
	private String[] name;

	public LogisticRegressionMultiClassificationService(String[] name) {
		this.name = name;
		this.sc = AlgorithmFactory.initSparkEnv("LogisticRegressionMultiClassification", 4);
	}

	/**
	 * 1 加载解析数据
	 */

	public static JavaRDD<LabeledPoint> load(SparkContext sc, String path) {
		JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sc, path).toJavaRDD();
		return data;
	}

	/**
	 * 2 切分数据集(训练数据集60% 测试数据集40%)
	 * 
	 * @param DataSet 加载的整体数据集
	 * @return map,包含训练数据集、测试数据集
	 */
	public static Map<String, JavaRDD<LabeledPoint>> slitData(JavaRDD<LabeledPoint> DataSet, Double factor1,
			Double factor2) {
		Map<String, JavaRDD<LabeledPoint>> mapData = new HashMap<String, JavaRDD<LabeledPoint>>();

		JavaRDD<LabeledPoint>[] splits = DataSet.randomSplit(new double[] { factor1, factor2 }, 11L);

		JavaRDD<LabeledPoint> training = splits[0].cache();
		JavaRDD<LabeledPoint> test = splits[1];

		mapData.put("trainingDataSet", training);
		mapData.put("testDataSet", test);

		return mapData;
	}

	/**
	 * 3 运行训练算法，构建模型
	 */

	public static LogisticRegressionModel buildModelandTraining(Map<String, JavaRDD<LabeledPoint>> mapData,int numClasses) {
		LogisticRegressionModel model = new LogisticRegressionWithLBFGS().setNumClasses(numClasses)
				.run(mapData.get("trainingDataSet").rdd());
		return model;
	}

	/**
	 * 4 在测试集上计算原始分数
	 */
	public static JavaRDD<Tuple2<Object, Object>> getPredictionAndLabels(Map<String, JavaRDD<LabeledPoint>> mapData,
			final LogisticRegressionModel model) {
		return mapData.get("testDataSet").map(new Function<LabeledPoint, Tuple2<Object, Object>>() {
			public Tuple2<Object, Object> call(LabeledPoint p) {
				Double prediction = model.predict(p.features());
				return new Tuple2<Object, Object>(prediction, p.label());
			}
		});
	}

	/**
	 * 5 计算得到（多分类）评估指标
	 */
	public static MulticlassMetrics getMetrics(JavaRDD<Tuple2<Object, Object>> predictionAndLabels) {
		MulticlassMetrics metrics = new MulticlassMetrics(predictionAndLabels.rdd());
		return metrics;
	}
	/**
	 * 6 计算混淆矩阵
	 * @param metrics
	 * @return
	 */
    public static Matrix getConfusion(MulticlassMetrics metrics){
    	Matrix confusion = metrics.confusionMatrix();
    	return confusion;
        //System.out.println("Confusion matrix: \n" + confusion);
    }
    
    /**
     * 7 Precision 精确率
     * @param metrics
     * @return precision
     */
    public static double getPrecision(MulticlassMetrics metrics){
    	double precision = metrics.precision();
    	return precision;
    }
    /**
     * 8 Recall 召回率
     * @param metrics
     * @return recall
     */
    public static double getRecall(MulticlassMetrics metrics){
    	double recall = metrics.recall();
    	return recall;
    }
   
    /**
     * 9 F Score by threshold β= 1.0
     * @param metrics
     * @return f1Score
     */
    public static double getF1score(MulticlassMetrics metrics){
    	double f1Score = metrics.fMeasure();
    	return f1Score;
    }
    /**
     * 10 按标签统计
     * @param metrics
     */
    public static void statsByLabels(MulticlassMetrics metrics){
    	for (int i = 0; i < metrics.labels().length; i++) {
    	      System.out.format("Class %f precision = %f\n", 
    	    		  metrics.labels()[i],
    	    		  metrics.precision(metrics.labels()[i]));
    	      
    	      System.out.format("Class %f recall = %f\n", 
    	    		  metrics.labels()[i], 
    	    		  metrics.recall(metrics.labels()[i]));
    	      
    	      System.out.format("Class %f F1 score = %f\n", 
    	    		  metrics.labels()[i], 
    	    		  metrics.fMeasure(metrics.labels()[i]));
    	    }
    }
    /**
     * 11 加权统计
     * @param metrics
     */
    public static void weightedStats(MulticlassMetrics metrics){
    	 System.out.format("Weighted precision = %f\n", metrics.weightedPrecision());
    	 System.out.format("Weighted recall = %f\n", metrics.weightedRecall());
    	 System.out.format("Weighted F1 score = %f\n", metrics.weightedFMeasure());
    	 System.out.format("Weighted false positive rate = %f\n", metrics.weightedFalsePositiveRate());
    }
    /**
     * 12 保存模型
     */
    
    public static void saveModel
    	(SparkContext sc,LogisticRegressionModel model,String path){
    	
    	model.save(sc, path);
    }
    
    /**
     * 13 加载模型
     */
    public static void loadModel(SparkContext sc,String path){
    	LogisticRegressionModel.load(sc, path);
    }

    /**
     * 14 关闭 SparkContext 对象
     */
    public static void stopSC(SparkContext sc){
   	 	AlgorithmFactory.stopSparkContext(sc);
    }
    
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//初始化环境
		sc = AlgorithmFactory.initSparkEnv("LogisticRegressionMultiClassification", 4);	
		//加载解析数据
		JavaRDD<LabeledPoint> DataSet = load(sc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_multiclass_classification_data.txt");
		//切分数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = slitData(DataSet,0.6,0.4);
		//运行训练算法，构建模型
		LogisticRegressionModel LRmodel = buildModelandTraining(mapData,6);
		//在测试集上计算原始分数
		JavaRDD<Tuple2<Object, Object>> predictionAndLabels = getPredictionAndLabels(mapData,LRmodel);
		//计算得到评估指标（多分类）
		MulticlassMetrics metrics = getMetrics(predictionAndLabels);
		// 计算得到混淆矩阵
		Matrix confusion = getConfusion(metrics);
		System.out.println("Confusion matrix（混淆矩阵）: \n" + confusion);
		
		System.out.println("Precision(精确率) = " + getPrecision(metrics));
	    System.out.println("Recall（召回率） = " + getRecall(metrics));
	    System.out.println("F1 Score（F1值） = " + getF1score(metrics));
	    /**
	     * 也可以直接通过metrics.precision()形式调用
	     */
	    //System.out.println("Precision = " + metrics.precision());
	    //System.out.println("Recall = " + metrics.recall());
	    //System.out.println("F1 Score = " + metrics.fMeasure());
	    
	    //按标签统计
	    statsByLabels(metrics);
	    //加权统计
	    weightedStats(metrics);
	    
	    stopSC(sc);
	}

}
