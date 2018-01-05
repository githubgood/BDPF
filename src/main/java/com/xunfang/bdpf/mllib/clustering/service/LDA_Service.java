package com.xunfang.bdpf.mllib.clustering.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.DistributedLDAModel;
import org.apache.spark.mllib.clustering.LDA;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import com.xunfang.bdpf.mllib.impl.AlgorithmFactory;

import scala.Tuple2;

public class LDA_Service {

	/**
	 * 0 初始化环境配置
	 */
	static JavaSparkContext jsc;
	private String[] name;

	public LDA_Service(String[] name) {
		this.name = name;
		this.jsc = AlgorithmFactory.initJavaSparkEnv("LDA", 4);
	}

	/**
	 * 1 加载解析数据
	 * return JavaRDD<Vector>,而不是JavaRDD<LabeledPoint>
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

		return parsedData;
	}
	
	/**
	 * 2 Index documents with unique IDs 使用唯一ID检索文章
	 * @param parsedData
	 * @return corpus
	 */
	public static JavaPairRDD<Long, Vector> getCorpus(JavaRDD<Vector> parsedData) {
		JavaPairRDD<Long, Vector> corpus = JavaPairRDD
				.fromJavaRDD(parsedData.zipWithIndex().map(new Function<Tuple2<Vector, Long>, Tuple2<Long, Vector>>() {
					public Tuple2<Long, Vector> call(Tuple2<Vector, Long> doc_id) {
						return doc_id.swap();
					}
				}));
		corpus.cache();
		return corpus;
	}

	/**
	 * 3 使用LDA将文档集成到三个主题中(模型构建)
	 * @param corpus
	 * @return
	 */
	public static DistributedLDAModel ldaModel(JavaPairRDD<Long, Vector> corpus,int K) {
		DistributedLDAModel ldaModel = (DistributedLDAModel) new LDA()
				.setK(K)
				.run(corpus);
		
		return ldaModel;
	}

	/**
	 * 4 输出主题. 每个都是一个分词（匹配字数向量）
	 * 
	 * @param ldaModel
	 * @return 词汇大小（词汇表中的术语或术语数量）
	 */
	public static int vocabSize(DistributedLDAModel ldaModel) {
		int vocabSize = ldaModel.vocabSize();

		// 测试打印 【词汇大小 “词汇表中的术语或术语 的数量”】
		System.out.println("Learned topics (as distributions over vocab of " + ldaModel.vocabSize() + " words):");

		return vocabSize;
	}
	
	/**
	 * 5 topics
	 * @param ldaModel
	 */
	public static List<LinkedHashMap<String, Object>> topics(DistributedLDAModel ldaModel) {
		Matrix topics = ldaModel.topicsMatrix();
		List<LinkedHashMap<String, Object>> outlist = new ArrayList<LinkedHashMap<String, Object>>();
		for (int word = 0; word < ldaModel.vocabSize(); word++) {
			LinkedHashMap<String, Object> outmap=null;
			for (int topic = 0; topic < 3; topic++) {
				outmap = new LinkedHashMap<String, Object>();
				outmap.put("Topic"+topic,topics.apply(word, topic));
			}
			outlist.add(outmap);
		}
		return outlist;
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
		jsc = AlgorithmFactory.initJavaSparkEnv("LDA", 4);
		// 加载解析数据
		JavaRDD<Vector> DataSet = load(jsc,"C:/Users/16273/Desktop/Apache/spark-1.6.3-bin-hadoop2.6/data/mllib/sample_lda_data.txt");
		
		JavaPairRDD<Long, Vector> corpus = getCorpus(DataSet);
		
		DistributedLDAModel model = ldaModel(corpus,3);
		
		vocabSize(model);
		topics(model);
		
		stopSC(jsc);
	}

}
