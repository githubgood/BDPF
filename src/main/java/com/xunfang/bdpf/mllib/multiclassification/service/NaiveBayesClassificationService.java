package com.xunfang.bdpf.mllib.multiclassification.service;

import scala.Tuple2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

public class NaiveBayesClassificationService implements Serializable {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public NaiveBayesClassificationService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("NaiveBayesClassification", 4);
	}

	/**
	 * 1 加载解析数据
	 */

	public static JavaRDD<LabeledPoint> load(JavaSparkContext jsc, String path) {
		JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), path).toJavaRDD();
		return data;
	}

	/**
     * 2 切分数据集(训练数据集70% 测试数据集30%)
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
     * 3 朴素贝叶斯分类 模型训练
     * @param mapData
     * @return
     */
    public static NaiveBayesModel modelTrain(Map<String, JavaRDD<LabeledPoint>> mapData){
    	
    	NaiveBayesModel model = NaiveBayes.train(mapData.get("trainingDataSet").rdd(),1.0);
    	
    	return model;
    }
    
    /**
     * 4 在测试集上评估模型
     * @param model
     * @param mapData
     * @return
     */
    public static JavaPairRDD<Double, Double> getPredictionAndLabels(NaiveBayesModel model,Map<String, JavaRDD<LabeledPoint>> mapData){
    	
    	// JavaPairRDD<Double, Double> predictionAndLabel = 
    	// mapData.get("testDataSet")
    	// .mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));
    	
    	JavaPairRDD<Double, Double> predictionAndLabels =
    	mapData.get("testDataSet").mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
    	    @Override
    	    public Tuple2<Double, Double> call(LabeledPoint p) {
    	      return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
    	    }
    	  });
    	return predictionAndLabels;
    }
    /**
     * 5 计算准确率
     * @param predictionAndLabel
     * @param mapData
     * @return
     */
   
    public static double getAccuracy (JavaPairRDD<Double, Double> predictionAndLabel,Map<String, JavaRDD<LabeledPoint>> mapData){
    	
    	// double accuracy = predictionAndLabel
    	// .filter(pl -> pl._1().equals(pl._2()))
    	// .count() / (double) mapData.get("testDataSet")
    	// .count();
    	
    	double accuracy = predictionAndLabel.filter(new Function<Tuple2<Double, Double>, Boolean>() {
    		  @Override
    		  public Boolean call(Tuple2<Double, Double> pl) {
    		    return pl._1().equals(pl._2());
    		  }
    		}).count() / (double) mapData.get("testDataSet").count();
    	
    	return accuracy ;
    }
    
    /**
     * 6 保存模型
     */
    
    public static void saveModel
    	(JavaSparkContext jsc,NaiveBayesModel model,String path){
    	
    	model.save(jsc.sc(),path);
    }
    
    /**
     * 7 加载模型
     */
    public static void loadModel(JavaSparkContext jsc,String path){
    	NaiveBayesModel.load(jsc.sc(), path);
    }

    /**
     * 8 关闭 JavaSparkContext 对象
     */
    public static void stopSC(JavaSparkContext jsc){
   	 	AlgorithmFactory.stopJavaSparkContext(jsc);
    }
    
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("NaiveBayesClassification", 4);
		//加载解析数据
		JavaRDD<LabeledPoint> DataSet = load(jsc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt");
		//切分数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = slitData(DataSet,0.6,0.4);
		//模型训练
		NaiveBayesModel model = modelTrain(mapData);
		//
		JavaPairRDD<Double, Double> predictionAndLabel =getPredictionAndLabels(model,mapData);
		System.out.println(predictionAndLabel.collect());
		//计算准确率
		double accuracy = getAccuracy(predictionAndLabel,mapData);
		
		System.out.println("准确率： "+accuracy);
		
		stopSC(jsc);
	}

}
