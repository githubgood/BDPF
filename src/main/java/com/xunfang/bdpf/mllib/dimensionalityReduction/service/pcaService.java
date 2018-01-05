package com.xunfang.bdpf.mllib.dimensionalityReduction.service;

import org.apache.spark.mllib.feature.PCA;
import org.apache.spark.mllib.feature.PCAModel;
import org.apache.spark.mllib.linalg.Vectors;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;

public class pcaService {
	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public pcaService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("pca", 4);
	}
	/**
	 * 1 加载解析数据
	 * return JavaRDD<Vector>,而不是JavaRDD<LabeledPoint>
	 * 
	 * 原始数据如下：
	 * 190.33 43.77 9.73 60.54 49.01 9.04 
	 * 135.20 36.40 10.47 44.16 36.49 3.94 
	 * 95.21 22.83 9.30 22.44 22.81 2.80 
	 * 104.78 25.11 6.40 9.89 18.17 3.25 
	 * 128.41 27.63 8.94 12.58 23.99 3.27 
	 * 145.68 32.83 17.79 27.29 39.09 3.47 
	 * 159.37 33.38 18.37 11.81 25.29 5.22 
	 * 116.22 29.57 13.24 13.76 21.75 6.04 
	 * 221.11 38.64 12.53 115.65 50.82 5.89 
	 * 144.98 29.12 11.67 42.60 27.30 5.74 
	 * 169.92 32.75 12.72 47.12 34.35 5.00 
	 * 153.11 23.09 15.62 23.54 18.18 6.39 
	 * 144.92 21.26 16.96 19.52 21.75 6.73 
	 * 140.54 21.50 17.64 19.19 15.97 4.94 
	 * 115.84 30.26 12.20 33.61 33.77 3.85 
	 * 101.18 23.26 8.46 20.20 20.50 4.30 
	 */
	public static JavaRDD<Vector> load(JavaSparkContext jsc, String path) {

		//数据路径加载
		JavaRDD<String> data = jsc.textFile(path);
		//格式解析
		JavaRDD<Vector> parsedData = data.map(new Function<String, Vector>() {
			public Vector call(String s) {
				String[] sarray = s.trim().split(" ");
				double[] values = new double[sarray.length];
				for (int i = 0; i < sarray.length; i++)
					values[i] = Double.parseDouble(sarray[i]);
				return Vectors.dense(values);
			}
		});
		return parsedData;
	}
	/**
	 * 2 PCA model构建
	 * @return 
	 */
	public static PCAModel buildingModel(JavaRDD<Vector> parsedData,int K){
		PCAModel model = new PCA(K).fit(parsedData);	
		return model;
	}
	/**
	 * 3 PCA转化
	 * @param args
	 * @return result
	 */
	public static JavaRDD<Vector> pcaResult(PCAModel model,JavaRDD<Vector> parsedData){
		JavaRDD<Vector> vectorResult = model.transform(parsedData);
		return vectorResult;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("PCA", 4);
		// 加载解析数据
		JavaRDD<Vector> DataSet = load(jsc,"C:/Users/16273/Desktop/PCA/pca2.data");
		// 构建模型
		PCAModel model =  buildingModel(DataSet, 3);
		// 转换【结果】
		JavaRDD<Vector> result = pcaResult(model,DataSet);
		List<Vector> resultList = result.collect();
	}
}
