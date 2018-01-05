package com.xunfang.bdpf.mllib.clustering.service;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.GaussianMixture;
import org.apache.spark.mllib.clustering.GaussianMixtureModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import org.apache.spark.SparkConf;

public class GaussianMixtureService {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public GaussianMixtureService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("GaussianMixture", 4);
	}

	/**
	 * 1 加载解析数据 return JavaRDD<Vector>,而不是JavaRDD<LabeledPoint>
	 */
	public static JavaRDD<Vector> load(JavaSparkContext jsc, String path) {

		JavaRDD<String> data = jsc.textFile(path);
		JavaRDD<Vector> parsedData = data.map(new Function<String, Vector>() {
			public Vector call(String s) {
				String[] sarray = s.trim().split(" ");
				double[] values = new double[sarray.length];
				for (int i = 0; i < sarray.length; i++)
					values[i] = Double.parseDouble(sarray[i]);
				return Vectors.dense(values);
			}
		});
		parsedData.cache();
		return parsedData;
	}

	/**
	 * 2 构建高斯混合模型
	 * 
	 * @param parsedData
	 * @param K
	 * @return
	 */
	public static GaussianMixtureModel gmm(JavaRDD<Vector> parsedData, int K) {
		GaussianMixtureModel gmm = new GaussianMixture().setK(2).run(parsedData.rdd());
		return gmm;
	}

	/**
	 * 3 输出高斯混合模型参数
	 * 
	 * @param gmm
	 */
	public static void parametersGmm(GaussianMixtureModel gmm) {
		for (int j = 0; j < gmm.k(); j++) {
			System.out.printf("weight(各组成成分权重)=%f\nmu(各类质点位置)=%s\nsigma(样本协方差矩阵)=\n%s\n", gmm.weights()[j], gmm.gaussians()[j].mu(),
					gmm.gaussians()[j].sigma());
		}

	}
	
	/**
	 * 4 关闭 JavaSparkContext 对象
	 */
	public static void stopSC(JavaSparkContext jsc) {
		AlgorithmFactory.stopJavaSparkContext(jsc);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("BisectingKMeans", 4);
				
		//加载数据
		JavaRDD<Vector> data = load(jsc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/gmm_data.txt");
		//模型构建
		GaussianMixtureModel model = gmm(data,2);
		//输出高斯混合模型参数
		parametersGmm(model);
	}

}
