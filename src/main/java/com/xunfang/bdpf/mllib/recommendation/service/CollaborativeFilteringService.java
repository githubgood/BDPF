package com.xunfang.bdpf.mllib.recommendation.service;

import scala.Tuple2;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;


import java.io.Serializable;

public class CollaborativeFilteringService implements Serializable {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public CollaborativeFilteringService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("CollaborativeFiltering", 4);
	}

	/**
	 * 1 加载解析数据
	 * @param jsc 
	 * @param path 数据路径
	 * @return ratings
	 */
	public static JavaRDD<Rating> load(JavaSparkContext jsc, String path) {
		
		JavaRDD<String> data = jsc.textFile(path);
		
		JavaRDD<Rating> ratings = data.map(
		  new Function<String, Rating>() {
		    public Rating call(String s) {
		      String[] sarray = s.split(",");
		      return new Rating(Integer.parseInt(sarray[0]), Integer.parseInt(sarray[1]),
		        Double.parseDouble(sarray[2]));
		    }
		  }
		);
		
		return ratings;
	}
	/**
	 * 2 模型构建与训练
	 * @param ratings
	 * @return MatrixFactorizationModel model
	 */
	public static MatrixFactorizationModel buildModelandTraining(JavaRDD<Rating> ratings){
		
		int rank = 10;
		int numIterations = 10;
		
		MatrixFactorizationModel model = 
				ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, 0.01);
		
		return model;
	}
/**************************************模型评估（在训练集上）************************************************/
	/**
	 * 3 对ratings数据提取userProducts
	 * case Rating(user, product, rate) => (user, product)
	 * 
	 * @param ratings
	 * @return userProducts
	 */
	public static JavaRDD<Tuple2<Object, Object>> userProducts(JavaRDD<Rating> ratings){
		JavaRDD<Tuple2<Object, Object>> userProducts = ratings.map(
				  new Function<Rating, Tuple2<Object, Object>>() {
				    public Tuple2<Object, Object> call(Rating r) {
				      return new Tuple2<Object, Object>(r.user(), r.product());
				    }
				  }
				);
		return userProducts;
	}

	/**
	 * 4 对userProducts进行predict预测
	 * case Rating(user, product, rate) => ((user, product), rate)
	 * @param userProducts
	 * @param model
	 * @return predictions
	 */
	public static JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions(
			JavaRDD<Tuple2<Object, Object>> userProducts, MatrixFactorizationModel model) {
		JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD
				.fromJavaRDD(model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD()
						.map(new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
							public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r) {
								return new Tuple2<Tuple2<Integer, Integer>, Double>(
										new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
							}
						}));
		return predictions;
	}

	/**
	 * 5 ratesAndPreds
	 * @param ratings
	 * @param predictions
	 * @return ratesAndPreds
	 */
	public static JavaRDD<Tuple2<Double, Double>> ratesAndPreds(JavaRDD<Rating> ratings,
			JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions) {
		JavaRDD<Tuple2<Double, Double>> ratesAndPreds = JavaPairRDD
				.fromJavaRDD(ratings.map(new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
					public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r) {
						return new Tuple2<Tuple2<Integer, Integer>, Double>(
								new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
					}
				})).join(predictions).values();
		return ratesAndPreds;
	}
	/**
	 * 5 计算 MSE
	 * @param ratesAndPreds
	 * @return MSE
	 */
	public static double MSE(JavaRDD<Tuple2<Double, Double>> ratesAndPreds){
		double MSE = JavaDoubleRDD.fromRDD(ratesAndPreds.map(
				  new Function<Tuple2<Double, Double>, Object>() {
				    public Object call(Tuple2<Double, Double> pair) {
				      Double err = pair._1() - pair._2();
				      return err * err;
				    }
				  }
				).rdd()).mean();
		return MSE;
	}
	/**
     * 6 保存模型(矩阵分解模型)
     */
    
    public static void saveModel
    	(JavaSparkContext jsc,MatrixFactorizationModel model,String path){
    	
    	model.save(jsc.sc(), path);
    }
    
    /**
     * 7 加载模型（矩阵分解模型）
     */
    public static void loadModel(JavaSparkContext jsc,String path){
    	MatrixFactorizationModel.load(jsc.sc(), path);
    }

    /**
     * 8 关闭 JavaSparkContext 对象
     */
    public static void stopSC(JavaSparkContext jsc){
   	 	AlgorithmFactory.stopJavaSparkContext(jsc);
    }
    
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		//初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("CollaborativeFiltering", 4);
		//加载解析数据
		JavaRDD<Rating> ratings = load(jsc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/als/test.data");
		//模型构建与训练
		MatrixFactorizationModel model = buildModelandTraining(ratings);
		//userProducts
		JavaRDD<Tuple2<Object, Object>> userProducts = userProducts(ratings);
		//predictions
		JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = predictions(userProducts,model);
		//ratesAndPreds
		JavaRDD<Tuple2<Double, Double>> ratesAndPreds = ratesAndPreds(ratings,predictions);
		//打印ratesAndPreds（Ratings和预测值）
		System.out.println(ratesAndPreds.collect());
		//计算MSE
		double MSE = MSE(ratesAndPreds);
		//打印均方误差
		System.out.println("Mean Squared Error = " + MSE);
		
	}

}
