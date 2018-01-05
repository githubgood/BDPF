package com.xunfang.bdpf.mllib.textAnalysis.service;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.linalg.Vector;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import org.apache.spark.mllib.feature.IDF;
import org.apache.spark.mllib.feature.IDFModel;
import org.apache.spark.api.java.function.Function; 

public class tfIDF {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public tfIDF(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("tfIDF", 4);
	}
	/**
	 * 1 数据加载
	 * @return text
	 */
	public static JavaRDD<String> load(JavaSparkContext jsc, String path){
		JavaRDD<String> text = jsc.textFile(path);
		return text;
	}
	/**
	 * 2 将输入文档转化为词频向量
	 * @return 
	 */
	public static JavaRDD<Vector> tf(JavaRDD<String> text){
		final HashingTF hashingTF = new HashingTF();
		JavaRDD<Vector> tf = text.map(new Function<String, Vector>() {
			@Override
			public Vector call(String v1) throws Exception {
				return hashingTF.transform(Arrays.asList(v1.split(" ")));
			}
		});
		return tf;
	}
	/**
	 * 3 构建模型
	 * @return 
	 */
	public static IDFModel buildIDFmodel(JavaRDD<Vector> tf){
		IDFModel idf = new IDF().fit(tf);
		return idf;
	}
	/**
	 * 4 将TF向量转换为TF-IDF向量
	 * @return 
	 */
	public static JavaRDD<Vector> tfidf(IDFModel idf,JavaRDD<Vector> tf){
		JavaRDD<Vector> tfIdf = idf.transform(tf);
		return tfIdf;
	}
	
//	public static Similarity(){
//		
//	}
	
	/**
	 * 测试
	 * @param args
	 */
	
	public static void main(String[] args) {
	
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("tfIDF", 4);
		
		JavaRDD<String> text = load(jsc,"C:/Users/16273/Desktop/新闻分类/tfidfData.txt");
		JavaRDD<Vector> tfVectors = tf(text);
		IDFModel modelIDF = buildIDFmodel(tfVectors);
		JavaRDD<Vector> tf2idf = tfidf(modelIDF,tfVectors);
		// 打印结果
		List<Vector> list = tf2idf.collect();
		System.out.println(list);
		
		jsc.stop();
//		/**
//		 * 数据加载
//		 */
//		JavaRDD<String> text = sc.textFile("C:/Users/16273/Desktop/新闻分类/C000007.txt");
//		/**
//		 * 模型构建
//		 */
//		final HashingTF hashingTF = new HashingTF();
//		JavaRDD<Vector> tf = text.map(new Function<String, Vector>() {
//			@Override
//			public Vector call(String v1) throws Exception {
//				return hashingTF.transform(Arrays.asList(v1.split(" ")));
//			}
//		});
//		IDFModel idf = new IDF().fit(tf);
//
//		/**
//		 * 将TF向量转换为TF-IDF向量
//		 */
//		JavaRDD<Vector> tfIdf = idf.transform(tf);
//
//		/**
//		 * 结果数据打印
//		 */
//		List<Vector> list = tfIdf.collect();
//		System.out.println(list);
		
		
		
		
	}

}
