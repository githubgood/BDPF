package com.xunfang.bdpf.mllib.regression.service;

import java.util.HashMap;
import java.util.Map;

import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

public class DecisionTreeRegressionService {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public DecisionTreeRegressionService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("DecisionTreeRegression", 4);
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
	 * 3 决策树 回归模型训练
	 * 
	 * @param mapData
	 * @return GradientBoostedTreesModel
	 */
	public static DecisionTreeModel train(Map<String, JavaRDD<LabeledPoint>> mapData) {

		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
		String impurity = "variance";
		Integer maxDepth = 5;
		Integer maxBins = 32;

		final DecisionTreeModel model = DecisionTree.trainRegressor(mapData.get("trainingDataSet"),
				categoricalFeaturesInfo, impurity, maxDepth, maxBins);

		return model;
	}
	// 构造函数
	public static DecisionTreeModel train(JavaRDD<LabeledPoint> trainingData) {

		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
		String impurity = "variance";
		Integer maxDepth = 5;
		Integer maxBins = 32;

		final DecisionTreeModel model = DecisionTree.trainRegressor(trainingData,categoricalFeaturesInfo, impurity, maxDepth, maxBins);

		return model;
	}
	/**
     * 4 在测试集上评估模型
     * @param model
     * @param mapData
     * @return
     */
    public static JavaPairRDD<Double, Double> getPredictionAndLabels(DecisionTreeModel model,Map<String, JavaRDD<LabeledPoint>> mapData){
    	
    	JavaPairRDD<Double, Double> predictionAndLabel =
    			mapData.get("testDataSet").mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
    			    @Override
    			    public Tuple2<Double, Double> call(LabeledPoint p) {
    			      return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
    			    }
    			  });
    	
    	return predictionAndLabel;
    }
    // 构造函数
    public static JavaPairRDD<Double, Double> getPredictionAndLabels_Bike(DecisionTreeModel model,JavaRDD<LabeledPoint> trainingData){
    	
    	JavaPairRDD<Double, Double> predictionAndLabel =
    			trainingData.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
    			    @Override
    			    public Tuple2<Double, Double> call(LabeledPoint p) {
    			      return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
    			    }
    			  });
    	
    	return predictionAndLabel;
    }

/**************************************************************************************************/
    /**
	 * 4  在训练样本上 评估模型 并计算 训练误差
	 * @return valuesAndPreds
	 */
	public static JavaRDD<Tuple2<Object, Object>> getValuesAndPreds(Map<String, JavaRDD<LabeledPoint>> mapData,DecisionTreeModel model) {
		JavaRDD<Tuple2<Object, Object>> valuesAndPreds = mapData.get("trainingDataSet")
				.map(new Function<LabeledPoint, Tuple2<Object, Object>>() {
					public Tuple2<Object, Object> call(LabeledPoint point) {
						double prediction = model.predict(point.features());
						return new Tuple2<Object, Object>(prediction, point.label());
					}
				});
		return valuesAndPreds;
	}

	public static JavaRDD<Tuple2<Object, Object>> getValuesAndPreds2(JavaRDD<LabeledPoint> parsedData,DecisionTreeModel model) {
		JavaRDD<Tuple2<Object, Object>> valuesAndPreds = parsedData
				.map(new Function<LabeledPoint, Tuple2<Object, Object>>() {
					public Tuple2<Object, Object> call(LabeledPoint point) {
						double prediction = model.predict(point.features());
						return new Tuple2<Object, Object>(prediction, point.label());
					}
				});
		return valuesAndPreds;
	}
	/**
	 * 5 计算回归指标
	 * @param valuesAndPreds
	 */
	public static Map<String,Double> metricsMap(JavaRDD<Tuple2<Object, Object>> valuesAndPreds){
		
		RegressionMetrics metrics = new RegressionMetrics(valuesAndPreds.rdd());
		
		//计算平方误差与平方根误差
		double MSE = metrics.meanSquaredError();
		double RMSE = metrics.rootMeanSquaredError();
		
		//计算R平方
		double r2 = metrics.r2();
		
		//计算平均绝对误差
		double MAE = metrics.meanAbsoluteError();
		
		//计算解释方差
		double EV = metrics.explainedVariance();
		
		Map<String, Double> metricsMap = new HashMap<String, Double>();
		metricsMap.put("MSE",MSE);
		metricsMap.put("RMSE",RMSE);
		metricsMap.put("r2",r2);
		metricsMap.put("MAE",MAE);
		metricsMap.put("EV", EV);
		
		return metricsMap;
	}
/*********************************************************************************************/
    
    
    
    
    
    /**
	 * 5 计算测试误差
	 * 
	 * @param predictionAndLabel
	 * @param mapData
	 * @return double testErr
	 * 
	 *         注：参数比GBDT分类算法多两个参数jsc,原始数据路径(原始数据的总行数data.count())
	 */
	public static double testErr(JavaPairRDD<Double, Double> predictionAndLabel, JavaSparkContext jsc, String path,
			Map<String, JavaRDD<LabeledPoint>> mapData) {

		Double testErr = predictionAndLabel.map(new Function<Tuple2<Double, Double>, Double>() {
			@Override
			public Double call(Tuple2<Double, Double> pl) {
				Double diff = pl._1() - pl._2();
				return diff * diff;
			}
		}).reduce(new Function2<Double, Double, Double>() {
			@Override
			public Double call(Double a, Double b) {
				return a + b;
			}
		}) / load(jsc, path).count();

		return testErr;
	}
	/**
	 * 6 保存模型
	 */

	public static void saveModel(JavaSparkContext jsc, DecisionTreeModel model, String path) {

		model.save(jsc.sc(), path);
	}

	/**
	 * 7 加载模型
	 */
	public static void loadModel(JavaSparkContext jsc, String path) {
		DecisionTreeModel.load(jsc.sc(), path);
	}

	/**
	 * 8 关闭 JavaSparkContext 对象
	 */
	public static void stopSC(JavaSparkContext jsc) {
		AlgorithmFactory.stopJavaSparkContext(jsc);
	}
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("DecisionTreeRegression", 4);
		// 加载解析数据
		JavaRDD<LabeledPoint> DataSet = load(jsc,
				"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt");
		// 切分数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = slitData(DataSet, 0.7, 0.3);
		// 模型训练
		DecisionTreeModel  model = train(mapData);
		JavaPairRDD<Double, Double> predictionAndLabel = getPredictionAndLabels(model, mapData);

		// 模型预测
		System.out.println(predictionAndLabel.collect());
		// 计算测试误差
		System.out.println("Test Error: " + testErr(predictionAndLabel, jsc,
				"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt", mapData));

		System.out.println("******************************************************************");

		System.out.println("Learned regression tree model:\n" + model.toDebugString());
		
		stopSC(jsc);
	}

}
