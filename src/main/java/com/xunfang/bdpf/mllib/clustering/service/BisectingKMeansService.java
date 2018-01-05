package com.xunfang.bdpf.mllib.clustering.service;

import com.google.common.collect.Lists;
import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import java.util.ArrayList;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.BisectingKMeans;
import org.apache.spark.mllib.clustering.BisectingKMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

public class BisectingKMeansService {
	
	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public BisectingKMeansService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("BisectingKMeans", 4);
	}
	/**
	 * 本地测试数据
	 */
	public static ArrayList<Vector> localData() {
		ArrayList<Vector> localData = Lists.newArrayList(
				  Vectors.dense(0.1, 0.1),   Vectors.dense(0.3, 0.3),
				  Vectors.dense(10.1, 10.1), Vectors.dense(10.3, 10.3),
				  Vectors.dense(20.1, 20.1), Vectors.dense(20.3, 20.3),
				  Vectors.dense(30.1, 30.1), Vectors.dense(30.3, 30.3)
				);
		
		return localData;
	}
	/**
	 * 1 加载数据集
	 * @param jsc
	 * @param localData
	 * @return
	 */
	public static JavaRDD<Vector> load(JavaSparkContext jsc, ArrayList<Vector> localData){
		JavaRDD<Vector> data = jsc.parallelize(localData,2);
		return data;
	}
	/**
	 * 2 模型构建与训练
	 * @param data
	 * @return
	 */
	public static BisectingKMeansModel model(JavaRDD<Vector> data) {
		BisectingKMeans bkm = new BisectingKMeans().setK(4);
		BisectingKMeansModel model = bkm.run(data);
		return model;
	}

	/**
	 * 3 计算Cost
	 * @param model
	 * @param data
	 * @return
	 */
	public static double Cost(BisectingKMeansModel model,JavaRDD<Vector> data){
		double cost = model.computeCost(data);
		
		return cost;
	}
	/**
	 * 4 clusterCenter 
	 * @param model
	 * @return 
	 */
	public static void clusterCenter(BisectingKMeansModel model){
		Vector[] clusterCenters = model.clusterCenters();
		
		for (int i = 0; i < clusterCenters.length; i++) {
		  Vector clusterCenter = clusterCenters[i];
		  System.out.println("Cluster Center - 聚类中心   " + i + ": " + clusterCenter);
		}
	}
	/**
	 * 5 关闭 JavaSparkContext 对象
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
		jsc = AlgorithmFactory.initJavaSparkEnv("BisectingKMeans", 4);
		
		//加载数据
		JavaRDD<Vector> data = load(jsc,localData());
		BisectingKMeansModel model = model(data);
		System.out.println("Cost: "+Cost(model,data));
		clusterCenter(model);
		
		stopSC(jsc);
	}

}
