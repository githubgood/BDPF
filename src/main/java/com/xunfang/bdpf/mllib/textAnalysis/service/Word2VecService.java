package com.xunfang.bdpf.mllib.textAnalysis.service;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.feature.Word2Vec;
import org.apache.spark.ml.feature.Word2VecModel;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

public class Word2VecService {
	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	/**
	 * 0 初始化环境
	 * 
	 * @param name
	 */
	public Word2VecService(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("Word2VecService", 4);
	}
	/**
	 * 2 Schema构建
	 * @return
	 */
	public static StructType schemaBuilder(){
		StructType schema = new StructType(new StructField[] {
				new StructField("text", new ArrayType(DataTypes.StringType, true), false, Metadata.empty()) });
		return schema;
	}

	/**
	 * 3 documentDF构建
	 * @param sqlContext
	 * @param jrdd
	 * @param schema
	 * @return
	 */
	public static DataFrame createDF(SQLContext sqlContext,JavaRDD<Row> jrdd,StructType schema){
		DataFrame documentDF = sqlContext.createDataFrame(jrdd, schema);
		return documentDF;
	}
	/**
	 * 4 学习从单词到向量的映射
	 * @param k
	 * @return
	 */
	public static Word2Vec Word2VecMap(int k){
		Word2Vec word2Vec = new Word2Vec()
				.setInputCol("text").setOutputCol("result")
				.setVectorSize(k).setMinCount(0);
		return word2Vec;
	}
	
	/**
	 * 5 词转向量 模型构建
	 * @param word2Vec
	 * @param documentDF
	 * @return
	 */
	public static Word2VecModel modelBuild(Word2Vec word2Vec,DataFrame documentDF){
		Word2VecModel model = word2Vec.fit(documentDF);
		return model;
	}
	
	/**
	 * 6 Word2Vec 转换操作
	 * 		将 句子列 转换为 向量列 以表示整个句子
	 * 		转变是通过对所包含的所有单词向量进行平均来完成的
	 * @param model
	 * @param documentDF
	 * @return
	 * 
	 */
	public static DataFrame transform(Word2VecModel model,DataFrame documentDF){
		DataFrame result = model.transform(documentDF);
		return result;
	}
	/**
	 * 7 计算指定单词的余弦相似度，并打印同义词
	 * @param model
	 * @param text
	 * @param K
	 */
	public static List<Row> cosineSimilarity(Word2VecModel model,String text,int K){
		// 寻找一个单词的同义词
	    DataFrame dataFrame = model.findSynonyms(text, K);
	    List<Row> rowlist =  dataFrame.collectAsList();
	    return rowlist;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		// 0 初始化环境
		jsc = AlgorithmFactory.initJavaSparkEnv("Word2VecService", 4);
		// 1 创建sqlContext
		SQLContext sqlContext = new org.apache.spark.sql.SQLContext(jsc);

		// 2 输入数据 ：每一行都是一个句子或文档中的一个单词
		JavaRDD<Row> jrdd = jsc.parallelize(Arrays.asList(RowFactory.create(Arrays.asList("Hi I heard about Spark".split(" "))),
						RowFactory.create(Arrays.asList("I wish Java could use case classes".split(" "))),
						RowFactory.create(Arrays.asList("Logistic regression models are neat".split(" ")))));
		// 3 Schema构建
		StructType schema = schemaBuilder();
		// 4 创建DataFrame
		DataFrame documentDF = createDF(sqlContext,jrdd,schema);
		// 5 学习从单词到向量的映射
		Word2Vec word2Vec = Word2VecMap(10);
		// 6  词转向量 模型构建
		Word2VecModel model = modelBuild(word2Vec,documentDF);
		// 7 Word2Vec 转换操作
		DataFrame result = transform(model,documentDF);
		// 8 循环打印结果
		for (Row r : result.select("result").take(3)) {
			System.out.println(r);
		}
		
		List<Row> rowlist = cosineSimilarity(model, "neat", 2);
		
		for (Row row : rowlist) {
			System.out.println(row);
		}
		
		// 9 stop jsc
		jsc.stop();
	}

}
