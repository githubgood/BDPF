package com.xunfang.bdpf.mllib.regression.service;

import java.util.HashMap;
import java.util.Map;

import scala.Tuple2;

import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.GradientBoostedTrees;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.mllib.util.MLUtils;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

public class GradientBoostedTreesRegressionService {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public GradientBoostedTreesRegressionService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("GradientBoostedTreesRegressionService", 4);
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
	 * 3 梯度提升 回归模型训练
	 * 
	 * @param mapData
	 * @return GradientBoostedTreesModel
	 */
	public static GradientBoostedTreesModel train(Map<String, JavaRDD<LabeledPoint>> mapData) {

		// The defaultParams for Classification use LogLoss by default.
		BoostingStrategy boostingStrategy = BoostingStrategy.defaultParams("Regression");

		boostingStrategy.setNumIterations(3); // Note: Use more iterations in practice.
		boostingStrategy.getTreeStrategy().setMaxDepth(5);

		// Empty categoricalFeaturesInfo indicates all features are continuous.
		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();

		boostingStrategy.treeStrategy().setCategoricalFeaturesInfo(categoricalFeaturesInfo);

		final GradientBoostedTreesModel model =

		GradientBoostedTrees.train(mapData.get("trainingDataSet"),boostingStrategy);

		return model;
	}

	/**
	 * 4 在测试集上评估模型
	 * 
	 * @param model
	 * @param mapData
	 * @return predictionAndLabel
	 */
	public static JavaPairRDD<Double, Double> getPredictionAndLabels(GradientBoostedTreesModel model,
			Map<String, JavaRDD<LabeledPoint>> mapData) {

		// JavaPairRDD<Double, Double> predictionAndLabel =
		// mapData.get("testDataSet")
		// .mapToPair(p -> new Tuple2<>(model.predict(p.features()),
		// p.label()));

		JavaPairRDD<Double, Double> predictionAndLabel = mapData.get("testDataSet")
				.mapToPair(new PairFunction<LabeledPoint, Double, Double>() {
					@Override
					public Tuple2<Double, Double> call(LabeledPoint p) {
						return new Tuple2<Double, Double>(model.predict(p.features()), p.label());
					}
				});

		return predictionAndLabel;
	}

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

	public static void saveModel(JavaSparkContext jsc, GradientBoostedTreesModel model, String path) {

		model.save(jsc.sc(), path);
	}

	/**
	 * 7 加载模型
	 */
	public static void loadModel(JavaSparkContext jsc, String path) {
		GradientBoostedTreesModel.load(jsc.sc(), path);
	}

	/**
	 * 8 关闭 JavaSparkContext 对象
	 */
	public static void stopSC(JavaSparkContext jsc) {
		AlgorithmFactory.stopJavaSparkContext(jsc);
	}

	/**
	 * 测试 2017-10-10
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("GradientBoostedTreesRegressionService", 4);
		// 加载解析数据
		JavaRDD<LabeledPoint> DataSet = load(jsc,
				"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt");
		// 切分数据集
		Map<String, JavaRDD<LabeledPoint>> mapData = slitData(DataSet, 0.7, 0.3);
		// 模型训练
		GradientBoostedTreesModel model = train(mapData);
		JavaPairRDD<Double, Double> predictionAndLabel = getPredictionAndLabels(model, mapData);

		System.out.println("Test Error: " + testErr(predictionAndLabel, jsc,
				"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt", mapData));

		System.out.println("******************************************************************");

		System.out.println("Learned classification GBT model:\n" + model.toDebugString());

		stopSC(jsc);

	}

}
