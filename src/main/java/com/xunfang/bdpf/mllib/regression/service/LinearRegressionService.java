package com.xunfang.bdpf.mllib.regression.service;

import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;
import com.xunfang.utils.Tools;

import org.apache.spark.mllib.evaluation.RegressionMetrics;

public class LinearRegressionService {
	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public LinearRegressionService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("LinearRegression", 4);
	}

	/**
	 * 1 加载解析数据
	 */
	public static JavaRDD<LabeledPoint> load(JavaSparkContext jsc, String path) {
		JavaRDD<String> data = jsc.textFile(path);
		
		JavaRDD<LabeledPoint> parsedData = data.map(new Function<String, LabeledPoint>() {
			public LabeledPoint call(String line) {
				String[] parts = line.split(" ");
				double[] v = new double[parts.length - 1];
				for (int i = 1; i < parts.length - 1; i++)
					v[i - 1] = Double.parseDouble(parts[i].split(":")[1]);
				return new LabeledPoint(Double.parseDouble(parts[0]), Vectors.dense(v));
			}
		});
		parsedData.cache();

		return parsedData;
	}

	/**
	 * 3 线性回归 模型训练
	 * 
	 * @param mapData
	 * @return GradientBoostedTreesModel
	 */
	public static LinearRegressionModel train(JavaRDD<LabeledPoint> parsedData) {

		int numIterations = 100;
		final LinearRegressionModel model = LinearRegressionWithSGD.train(JavaRDD.toRDD(parsedData), numIterations);

		return model;
	}

	/**
	 * 4 评估 训练样本模型 并计算 训练误差
	 * @return valuesAndPreds
	 */
	public static JavaRDD<Tuple2<Object, Object>> getValuesAndPreds(JavaRDD<LabeledPoint> parsedData,LinearRegressionModel model) {
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
	public static Map<String,String> metricsMap(JavaRDD<Tuple2<Object, Object>> valuesAndPreds){
		
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
		
		Map<String, String> metricsMap = new HashMap<String, String>();
		metricsMap.put("MSE",Tools.doubleNumberFormat(MSE, 4));
		metricsMap.put("RMSE",Tools.doubleNumberFormat(RMSE, 4));
		metricsMap.put("r2",Tools.doubleNumberFormat(r2, 4));
		metricsMap.put("MAE",Tools.doubleNumberFormat(MAE, 4));
		metricsMap.put("EV", Tools.doubleNumberFormat(EV, 4));
		
		return metricsMap;
	}
	/**
	 * 2017-11-13 houjingru@xunfang.com
	 * 计算权重 【特征重要性计算】
	 * @param model
	 * @return weightsVectors
	 */
	public static Vector getWeights(LinearRegressionModel model){
		Vector weightsVectors = model.weights();
		return weightsVectors;
	}
	 
	/**
	 * 6 保存模型
	 */

	public static void saveModel(JavaSparkContext jsc, LinearRegressionModel model, String path) {

		model.save(jsc.sc(), path);
	}

	/**
	 * 7 加载模型
	 */
	public static void loadModel(JavaSparkContext jsc, String path) {
		LinearRegressionModel.load(jsc.sc(), path);
	}

	/**
	 * 8 关闭 JavaSparkContext 对象
	 */
	public static void stopSC(JavaSparkContext jsc) {
		AlgorithmFactory.stopJavaSparkContext(jsc);
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("LinearRegression", 4);
		// 加载解析数据
		JavaRDD<LabeledPoint> parsedData = load(jsc,
				"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_linear_regression_data.txt");
		// 模型训练
		LinearRegressionModel  model = train(parsedData);
		JavaRDD<Tuple2<Object, Object>> valuesAndPreds = getValuesAndPreds(parsedData,model);
		Map<String,String> metricsMap = metricsMap(valuesAndPreds);
		System.out.println("MSE 平方误差： "+metricsMap.get("MSE"));
		System.out.println("RMSE 平方根误差： "+metricsMap.get("RMSE"));
		System.out.println("r2 R方："+metricsMap.get("r2"));
		System.out.println("MAE 平均绝对误差： "+metricsMap.get("MAE"));
		System.out.println("EV 解释方差： "+metricsMap.get("EV"));
		
		// 权重计算[线性模型特征重要性]
		Vector weights = getWeights(model);
		System.out.println("特征重要性: "+weights);
		
		stopSC(jsc);
	}

}
