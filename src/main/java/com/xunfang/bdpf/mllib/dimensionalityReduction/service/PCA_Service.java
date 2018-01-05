package com.xunfang.bdpf.mllib.dimensionalityReduction.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.spark.api.java.*;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.rdd.RDD;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

public class PCA_Service {

	public static void main(String[] args) {
		/**
		 * 0 环境配置
		 */
		SparkConf conf = new SparkConf().setAppName("PCA_Example").setMaster("local[4]");
	    SparkContext sc = new SparkContext(conf);
	     
	    /**
	     * 1 测试数据
	     */
	    double[][] array ={{1.0,2.0,3.0},{9.1,9.2,9.3},{1.0,2.5,4.2},{9.1,9.2,9.0},{1.0,2.5,4.2},{9.1,9.2,9.0},{1.0,2.5,4.2},{9.1,9.2,9.0}};
	    /**
	     * 2 类型转化 【数组转为密度向量】
	     */
	    LinkedList<Vector> rowsList = new LinkedList<Vector>();
	    for (int i = 0; i < array.length; i++) {
	      Vector currentRow = Vectors.dense(array[i]);
	      rowsList.add(currentRow);
	    }
	    //再转化为行向量 【通过sc的parallelize()可以创建出并行操作的分布式数据集】
	    JavaRDD<Vector> rows = JavaSparkContext.fromSparkContext(sc).parallelize(rowsList);

	    /**
	     * 3 基于 向量 创建 分布式行矩阵
	     */
	    RowMatrix mat = new RowMatrix(rows.rdd());
	   // System.out.println(mat);//打印行矩阵

	    /**
	     * 4 计算前3个主成分
	     */
	    Matrix pc = mat.computePrincipalComponents(2);
	    System.out.println(pc);
	    // -0.6461623427559642  -0.4265254485756479  -0.6328904079866169   
	    // -0.5743665268941903  -0.2743023989199542  0.7712725113280414    
	    // -0.5025707110324168  0.8618783183629234   -0.06773805982068284 
	    
	    RowMatrix projected = mat.multiply(pc);//行矩阵乘法 -- 得到的新矩阵
	    //projected.rows.foreach(println) -- Scala语言循环便利打印这个新得到的矩阵方式
	    System.out.println(projected.numRows()+"行"+"\n"+projected.numCols()+"列");
	    // 打印新数据
	    RDD<Vector> d = projected.rows();
		JavaRDD<Vector> dds = d.toJavaRDD();
		List<Vector> sd = dds.collect();
		for(int i = 0;i<sd.size();i++){
			System.out.println(sd.get(i));
		}
		
		sc.stop();
	    // Java待查 【循环遍历打印RowMatrix projected】
	}
}
