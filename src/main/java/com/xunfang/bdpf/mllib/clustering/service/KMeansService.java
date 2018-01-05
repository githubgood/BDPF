package com.xunfang.bdpf.mllib.clustering.service;

import java.io.Serializable;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

/**
 * @ClassName KMeansService
 * @Description: KMeans线性回归算法接口类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月11日 下午5:41:27
 * @version V1.0
 */
public class KMeansService implements Serializable {

	/**
	* @Fields serialVersionUID : 
	* TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -5206326555263151019L;
	
	 JavaSparkContext jsc;
	 
	 private String[] name;
	 
	 public KMeansService(String[] name) {
		 this.name = name;
		 this.jsc = AlgorithmFactory.initJavaSparkEnv("K-means", 4);
	 }
	 
	/**
	 * 
	  * @Title: load
	  * @Description: 加载解析数据
	  *  @param sc JavaSparkContext 初始化对象
	  *  @param path String 待解析的文件路径
	  *  @return  JavaRDD<Vector> 返回类型
	  * @throws
	 */
	public static JavaRDD<Vector> load(JavaSparkContext jsc,String path){
		JavaRDD<String> data = jsc.textFile(path);
        JavaRDD<Vector> parsedData = data.map(
                new Function<String, Vector>() {
                    public Vector call(String s) {
                        String[] sarray = s.split(" ");
                        double[] values = new double[sarray.length];
                        for (int i = 0; i < sarray.length; i++){
                        	values[i] = Double.parseDouble(sarray[i]);
                        }
                        return Vectors.dense(values);
                    }
                }
        );
        parsedData.cache();
        return parsedData;
	}

	/**
	 * 
	  * @Title: train
	  * @Description: 模型训练：使用 KMeans算法 将 数据 聚类 到 两个类簇 中去
	  *  @param parsedData JavaRDD<Vector> 解析过的数据集合
	  *  @param numClusters int  簇类数量
	  *  @param numIterations int  迭代次数
	  *  @return  KMeansModel 返回类型
	  * @throws
	 */
	public static KMeansModel train(JavaRDD<Vector> parsedData,int numClusters,int numIterations){
		return KMeans.train(parsedData.rdd(), numClusters, numIterations);
	}
	/**
	 * 预测[parsedData数据属于哪个类标]
	 * @param model
	 * @param parsedData
	 */
	public static JavaRDD<Integer> predict(KMeansModel model,JavaRDD<Vector> parsedData){
		return model.predict(parsedData);
	}
	
	/**
	 * 
	  * @Title: 评估：计算平方误差和 computeCost
	  * @Description: 模型评估：通过计算平方误差和评估聚类模型
	  *  @param clusters KMeansModel  训练模型对象
	  *  @param parsedData JavaRDD<Vector> 解析过的数据集合
	  *  @return  double 返回类型
	  * @throws
	 */
	public static double computeCost(KMeansModel clusters,JavaRDD<Vector> parsedData){
		double WSSSE = clusters.computeCost(parsedData.rdd());
		return WSSSE;
	}
	
	/**
	 * 
	  * @Title: getKMeansModel
	  * @Description: 保存和加载模型
	  *  @param clusters KMeansModel  训练模型对象
	  *  @param path String 保存路径
	  *  @return  KMeansModel 返回类型
	  * @throws
	 */
	public static KMeansModel getKMeansModel(JavaSparkContext jsc,KMeansModel clusters,String path){
		 clusters.save(jsc.sc(), path);
	     KMeansModel sameModel = KMeansModel.load(jsc.sc(), path);
		return sameModel;
	}
	
	private synchronized void produce(String i) {
		jsc = AlgorithmFactory.initJavaSparkEnv("K-means", 4);
		
		JavaRDD<Vector> parsedData = load(jsc,"C:/Working/xunfangProjects2017/Data/mllib/kmeans_data.txt");
		KMeansModel clusters = train(parsedData, 2, 20);
		double WSSSE = computeCost(clusters, parsedData);
		System.out.println("Within Set Sum of Squared Errors = " + WSSSE);
		KMeansModel sameModel = getKMeansModel(jsc,clusters, "C:/Working/xunfangProjects2017/model/kmeans/"+i+"/");
		
		AlgorithmFactory.stopJavaSparkContext(jsc);
    }
	
    class Produce implements Runnable{
    	public void run() {
            for (int i = 0; i < name.length; i++) {//学生数量
            	produce(name[i]);
            }
        }
	}
    
	/**
	 * 
	  * @Title: main
	  * @Description: 测试主方法
	  *  @param args  void 返回类型
	  * @throws
	 */
	public static void main(String[] args) {
		
		String[] strings = new String[]{"001","002","003","004","005","006","007","008","009","010"};
		KMeansService  kMeansService = new KMeansService(strings);
		Produce produce = kMeansService.new Produce();
		Thread t = new Thread(produce);
		t.start();
	}
	
}
