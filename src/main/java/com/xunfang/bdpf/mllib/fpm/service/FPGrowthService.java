package com.xunfang.bdpf.mllib.fpm.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.fpm.AssociationRules;
import org.apache.spark.mllib.fpm.FPGrowth;
import org.apache.spark.mllib.fpm.FPGrowthModel;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

public class FPGrowthService {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public FPGrowthService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("FPGrowthService", 4);
	}

	/**
	 * 1 加载/解析数据
	 * 
	 * @param jsc
	 * @param path 数据路径
	 * @return ratings
	 */
	public static JavaRDD<String> load(JavaSparkContext jsc, String path) {

		JavaRDD<String> data = jsc.textFile(path);

		return data;
	}

	/**
	 * 2 数据格式转换JavaRDD<List<String>>
	 * 
	 * @param data
	 * @return
	 */
	public static JavaRDD<List<String>> transactions(JavaRDD<String> data) {
		//Note:写法不兼容
		//JavaRDD<List<String>> transactions = data.map(line -> Arrays.asList(line.split(" ")));
		JavaRDD<List<String>> transactions = data.map(
				  new Function<String, List<String>>() {
				    public List<String> call(String line) {
				      String[] parts = line.split(" ");
				      return Arrays.asList(parts);
				    }
				  }
				);
		return transactions;
	}

	/**
	 * 3 构建模型 FPGrowthModel<String> model
	 * @param transactions
	 * @return model
	 */
	public static FPGrowthModel<String> model(JavaRDD<List<String>> transactions) {
		
		FPGrowth fpg = new FPGrowth()
				.setMinSupport(0.2)//最小支持度
				.setNumPartitions(10);//数据分区数量
		FPGrowthModel<String> model = fpg.run(transactions);
		return model;
	}
	/**
	 * 4 查看所有频繁项，列出它们出现的次数
	 * @param model
	 */
	public static Map<List<String>,Long> itemset(FPGrowthModel<String> model){
		Map<List<String>,Long> outListMap = new HashMap<List<String>,Long>();
		for (FPGrowth.FreqItemset<String> itemset : model.freqItemsets().toJavaRDD().collect()) {
			//生产环境下写入到MySQL或者redis
			outListMap.put(itemset.javaItems(), itemset.freq());
//			System.out.println("[" + itemset.javaItems() + "], " + itemset.freq());
		}
		return outListMap;
	}
	/**
	 * 5 通过置信度筛选出强规则
	 * @param model
	 */
	public static void rule(FPGrowthModel<String> model){
		double minConfidence = 0.8;//最小置信度
		//生产环境下写入到MySQL或者redis
		for (AssociationRules.Rule<String> rule : model.generateAssociationRules(minConfidence).toJavaRDD().collect()) {
			System.out.println(rule.javaAntecedent() + " => " + rule.javaConsequent() + ", " + rule.confidence());
		}
	}
	 /**
	  * 4 关闭 JavaSparkContext 对象
	  * 
     */
    public static void stopSC(JavaSparkContext jsc){
   	 	AlgorithmFactory.stopJavaSparkContext(jsc);
    }
    
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("FPGrowthService", 4);
		// 加载解析数据
		JavaRDD<String> data = load(jsc,
				"D:/spark-1.6.3-bin-hadoop2.6/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_fpgrowth.txt");
		JavaRDD<List<String>> transactions = transactions(data);
		List<List<String>> outlist =  transactions.collect();
		// 模型构建与训练
		FPGrowthModel<String> model = model(transactions);

/**********************************************直接调用，不用封装好的方法************************************/
		/**
		 * 查看所有频繁项，列出它们出现的次数
		 */
		for (FPGrowth.FreqItemset<String> itemset : model.freqItemsets().toJavaRDD().collect()) {
			System.out.println("[" + itemset.javaItems() + "], " + itemset.freq());
		}

		/**
		 * 通过置信度筛选出强规则
		 * javaAntecedent : 前项
		 * javaConsequent : 后项
		 * confidence : 规则的置信度
		 */
		double minConfidence = 0.8;//最小置信度
		for (AssociationRules.Rule<String> rule : model.generateAssociationRules(minConfidence).toJavaRDD().collect()) {
			System.out.println(rule.javaAntecedent() + " => " + rule.javaConsequent() + ", " + rule.confidence());
		}
/******************************************调用封装好方法*****************************************************/
		itemset(model);
		rule(model);
		
		/**
		 * stop JSC
		 */
		stopSC(jsc);
	}
}
