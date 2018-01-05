package com.xunfang.bdpf.mllib.binaryclassification.service;

import scala.Tuple2;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

public class LogisticRegressionBinaryClassificationService implements Serializable{

	private static final long serialVersionUID = -5206326555263151019L;
	
    /**
	 * 0 初始化环境配置
	 */
	static SparkContext sc;
	private String[] name;
	 
    public LogisticRegressionBinaryClassificationService(String[] name){
    	this.name = name;
		this.sc = AlgorithmFactory.initSparkEnv("LogisticRegressionBinaryClassification",4);
    }
    
    /**
     * 1 加载解析数据
     */
    
    public static JavaRDD<LabeledPoint> load(SparkContext sc,String path){
		 JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sc,path).toJavaRDD();
		 return data;
	 }

    /**
     * 2 切分数据集(训练数据集60% 测试数据集40%)
     * @param DataSet 加载的整体数据集
     * @return map,包含训练数据集、测试数据集
     */
    public static Map<String, JavaRDD<LabeledPoint>> slitData(JavaRDD<LabeledPoint> DataSet,Double factor1,Double factor2){
    	Map<String, JavaRDD<LabeledPoint>> mapData = new HashMap<String, JavaRDD<LabeledPoint>>();
    	
    	JavaRDD<LabeledPoint>[] splits = DataSet.randomSplit(new double[]{factor1, factor2}, 11L);
        
    	JavaRDD<LabeledPoint> training = splits[0].cache();
        JavaRDD<LabeledPoint> test = splits[1];
        
        mapData.put("trainingDataSet", training);
        mapData.put("testDataSet", test);
        
        return mapData;
    }
    
    /**
     * 3 运行训练算法，构建逻辑回归分类模型
     */
    
    public static LogisticRegressionModel buildModelandTraining(Map<String, JavaRDD<LabeledPoint>> mapData){
    	LogisticRegressionModel model = new LogisticRegressionWithLBFGS()
                .setNumClasses(2)
                .run(mapData.get("trainingDataSet").rdd());
		return model;
    }

    /**
     * 4 清除预测阈值,使“预测”将输出原始预测分数.它仅用于二分类.
     *
     *  Clear the prediction threshold so the model will return probabilities
     * @return 
     */
    public static LogisticRegressionModel clearThreshold(LogisticRegressionModel model){
    	return model.clearThreshold();
    }
    
    /**
     * 5 在测试集上计算原始分数
     */
    public static JavaRDD<Tuple2<Object, Object>> getPredictionAndLabels
    (Map<String, JavaRDD<LabeledPoint>> mapData,final LogisticRegressionModel model){
    	return mapData.get("testDataSet").map(
    			new Function<LabeledPoint, Tuple2<Object, Object>>() {
            public Tuple2<Object, Object> call(LabeledPoint p) {
                Double prediction = model.predict(p.features());
                return new Tuple2<Object, Object>(prediction, p.label());
            }
        });
    }
    
    
    
    /**
     * 6 *************************************计算得到评估指标 *********************************************
     */

    public static BinaryClassificationMetrics getMetrics(JavaRDD<Tuple2<Object, Object>> predictionAndLabels){
    	BinaryClassificationMetrics metrics = 
    			new BinaryClassificationMetrics(predictionAndLabels.rdd());
		return metrics;
    }
    /**
     * 返回综合指标的Map信息
     * @param metrics
     * @return metricsDetailsData
     * 2017-10-16 houjingru@xunfang.com
     */
    public static Map<String, JavaRDD<Tuple2<Object, Object>>> metricsDetails(BinaryClassificationMetrics metrics){
    	Map<String, JavaRDD<Tuple2<Object, Object>>> metricsDetailsData = new HashMap<String, JavaRDD<Tuple2<Object, Object>>>();
    	
    	JavaRDD<Tuple2<Object, Object>> precision = metrics.precisionByThreshold().toJavaRDD();
    	JavaRDD<Tuple2<Object, Object>> recall = metrics.recallByThreshold().toJavaRDD();
    	JavaRDD<Tuple2<Object, Object>> f1Score = metrics.fMeasureByThreshold().toJavaRDD();
    	JavaRDD<Tuple2<Object, Object>> f2Score = metrics.fMeasureByThreshold(2.0).toJavaRDD();
    	JavaRDD<Tuple2<Object, Object>> prc = metrics.pr().toJavaRDD();
    	JavaRDD<Tuple2<Object, Object>> roc = metrics.roc().toJavaRDD();
    	
    	
    	metricsDetailsData.put("precision", precision);
    	metricsDetailsData.put("recall", recall);
    	metricsDetailsData.put("f1Score", f1Score);
    	metricsDetailsData.put("f2Score", f2Score);
    	metricsDetailsData.put("prc", prc);
    	metricsDetailsData.put("roc", roc);
    	
    	return metricsDetailsData;
    }
    /**
     * 返回综合指标(AUPRC,AUROC)Map信息
     * @param metrics
     * @return prcAndRocData
     * 2017-10-16 houjingru@xunfang.com
     */
    public static Map<String, Object> prcAndRoc(BinaryClassificationMetrics metrics){
    	Map<String, Object> prcAndRocData = new HashMap<String, Object>();
    	double AUPRC = metrics.areaUnderPR();
    	double AUROC = metrics.areaUnderROC();
    	
    	prcAndRocData.put("AUPRC", AUPRC);
    	prcAndRocData.put("AUROC", AUROC);
    	return prcAndRocData;
    }
    
    /**
     * 7 Precision by threshold (阈值,精度)曲线
     * @param metrics
     * @return precision
     */
    public static JavaRDD<Tuple2<Object, Object>> getPrecision(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> precision = metrics.precisionByThreshold().toJavaRDD();
    	return precision;
    }
    /**
     * 8 Recall by threshold (阈值,召回)曲线
     * @param metrics
     * @return recall
     */
    public static JavaRDD<Tuple2<Object, Object>> getRecall(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> recall = metrics.recallByThreshold().toJavaRDD();
    	return recall;
    }
   
    /**
     * 9 F Score by threshold β= 1.0的(阈值,F-Measure)曲线。
     * @param metrics
     * @return f1Score
     */
    public static JavaRDD<Tuple2<Object, Object>> getF1score(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> f1Score = metrics.fMeasureByThreshold().toJavaRDD();
    	return f1Score;
    }
    
    /**
     * 10 F2 Score by threshold
     * @param metrics
     * @return f2Score
     */
    public static JavaRDD<Tuple2<Object, Object>> getF2Score(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> f2Score = metrics.fMeasureByThreshold(2.0).toJavaRDD();
    	return f2Score;
    }
    
    /**
     * 11 Precision-recall curve 
     * @param metrics
     * @return prc
     */
    public static JavaRDD<Tuple2<Object, Object>> getPRC(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> prc = metrics.pr().toJavaRDD();
    	return prc;
    }

    /**
     * 12 ROC Curve
     * @param metrics
     * @return roc
     */
    public static JavaRDD<Tuple2<Object, Object>> getROC(BinaryClassificationMetrics metrics){
    	JavaRDD<Tuple2<Object, Object>> roc = metrics.roc().toJavaRDD();
    	return roc;
    }

    /**
     * 13 AUPRC
     * @param metrics
     * @return
     */
    public static double getAUPRC(BinaryClassificationMetrics metrics){
    	return metrics.areaUnderPR();
    }
    /**
     * 14 AUROC
     * @param metrics
     * @return
     */
    public static double getAUROC(BinaryClassificationMetrics metrics){
    	return metrics.areaUnderROC();
    }

    /**
     * 15 保存模型
     */
    
    public static void saveModel
    	(SparkContext sc,LogisticRegressionModel model,String path){
    	
    	model.save(sc, path);
    }
    
    /**
     * 16 加载模型
     */
    public static void loadModel(SparkContext sc,String path){
    	LogisticRegressionModel.load(sc, path);
    }

    /**
     * 8 关闭 SparkContext 对象
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
		sc = AlgorithmFactory.initSparkEnv("LogisticRegressionBinaryClassificationService", 4);
		
		//加载解析数据
		JavaRDD<LabeledPoint> DataSet = load(sc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_binary_classification_data.txt");
		//切分数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = slitData(DataSet,0.6,0.4);
		//运行训练算法，构建模型
		LogisticRegressionModel LRmodel = buildModelandTraining(mapData);
		//清除预测阈值,使“预测”将输出原始预测分数.它仅用于二分类.
		clearThreshold(LRmodel);
		//计算原始分数
		JavaRDD<Tuple2<Object, Object>> predictionAndLabels=getPredictionAndLabels(mapData, LRmodel);
		System.out.println(predictionAndLabels);
		
		BinaryClassificationMetrics metrics = getMetrics(predictionAndLabels);
		
		//getPrecision(metrics);
		System.out.println(getPrecision(metrics).collect());
		
		//getRecall(metrics);
		System.out.println(getRecall(metrics).collect());
		
		//getF1score(metrics);
		System.out.println(getF1score(metrics).collect());
		
		//getF2Score(metrics);
		System.out.println(getF2Score(metrics).collect());
		
		//getPRC(metrics);
		System.out.println(getPRC(metrics).collect());
		
		//getROC(metrics);
		System.out.println(getROC(metrics).collect());
		
		//getAUPRC(metrics);
		System.out.println(getAUPRC(metrics));
		
		//getAUROC(metrics);
		System.out.println(getAUROC(metrics));
		
		saveModel(sc, LRmodel,"C:/Users/16273/Desktop/sparkTemp2017xunfang/LRmodel");
		
		loadModel(sc, "C:/Users/16273/Desktop/sparkTemp2017xunfang/LRmodel");
		
		
		System.out.println("权重-Weights: " + LRmodel.weights() + " Intercept: " + LRmodel.intercept());
		
		stopSC(sc);
	}

}
