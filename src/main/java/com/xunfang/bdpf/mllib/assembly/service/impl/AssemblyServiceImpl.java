package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.mapper.AssemblyMapper;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FlowBussiness;

/**
 * @ClassName AssemblyServiceImpl
 * @Description: 组件Service实现服务类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月29日 上午10:08:10
 * @version V1.0
 */
@Service
@Transactional
public class AssemblyServiceImpl implements AssemblyService {

	@Autowired
	private AssemblyMapper assemblyMapper;
	
	/**
	 * 
	  * @Title: countByExample
	  * @Description: 根据条件查询数量
	  *  @return  long 返回类型
	  * @throws
	 */
	@Override
	public long countByExample(AssemblyExample example) {
		return assemblyMapper.countByExample(example);
	}

    /**
   	 * 
   	  * @Title: deleteByExample
   	  * @Description: 根据条件删除多条数据
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int deleteByExample(AssemblyExample example) {
		return assemblyMapper.deleteByExample(example);
	}

    /**
 	 * 
 	  * @Title: deleteByPrimaryKey
 	  * @Description: 根据条件删除单条数据
 	  *  @return  int 返回类型
 	  * @throws
 	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		return assemblyMapper.deleteByPrimaryKey(id);
	}

    /**
  	 * 
  	  * @Title: insert
  	  * @Description: 插入数据
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insert(Assembly record) {
		return assemblyMapper.insert(record);
	}

    /**
  	 * 
  	  * @Title: insertSelective
  	  * @Description: 插入数据,不为null的字段
  	  *  @return  int 返回类型
  	  * @throws
  	 */
	@Override
	public int insertSelective(Assembly record) {
		return assemblyMapper.insertSelective(record);
	}

    /**
	 * 
	  * @Title: selectByExample
	  * @Description: 根据条件查询数据
	  *  @return  List<Assembly> 返回类型
	  * @throws
	 */
	@Override
	public List<Assembly> selectByExample(AssemblyExample example) {
		return assemblyMapper.selectByExample(example);
	}

    /**
   	 * 
   	  * @Title: selectByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  Assembly 返回类型
   	  * @throws
   	 */
	@Override
	public Assembly selectByPrimaryKey(String id) {
		return assemblyMapper.selectByPrimaryKey(id);
	}

    /**
	 * 
	  * @Title: updateByExampleSelective
	  * @Description: 按条件更新值不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByExampleSelective(Assembly record, AssemblyExample example) {
		return assemblyMapper.updateByExampleSelective(record, example);
	}

    /**
   	 * 
   	  * @Title: updateByExample
   	  * @Description: 按条件更新
   	  *  @return  int 返回类型
   	  * @throws
   	 */
	@Override
	public int updateByExample(Assembly record, AssemblyExample example) {
		return assemblyMapper.updateByExample(record, example);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKeySelective
	  * @Description: 按条件更新,带主键且不为null的字段
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(Assembly record) {
		return assemblyMapper.updateByPrimaryKeySelective(record);
	}

    /**
	 * 
	  * @Title: updateByPrimaryKey
	  * @Description: 按主键更新
	  *  @return  int 返回类型
	  * @throws
	 */
	@Override
	public int updateByPrimaryKey(Assembly record) {
		return assemblyMapper.updateByPrimaryKey(record);
	}

	/**
     * 
      * @Title: queryMaxXh
      * @Description: 查询当前实验最大的序号ID
      *  @param expid
      *  @return  int 返回类型
      * @throws
     */
	@Override
	public int queryMaxXh(String expid) {
		return assemblyMapper.queryMaxXh(expid);
	}
	
	/**
   	 * 
   	  * @Title: queryByPrimaryKey
   	  * @Description: 根据主键查询数据
   	  *  @return  Assembly 返回类型
   	  * @throws
   	 */
	@Override
	public Assembly queryByPrimaryKey(String id) {
		return assemblyMapper.queryByPrimaryKey(id);
	}
	
	/**
     * 
      * @Title: runFlow
      * @Description:执行流程方法 
      * @throws 
     */
	@Override
	public String runFlow(Map<String,Object> parms) {
		int assemblyId = Integer.parseInt(parms.get("assemblyId")+"");
		//统计分析
		if(assemblyId>=32&&assemblyId<=48){
			return FlowBussiness.statisticalanalysis(parms);
		}else{
			switch(assemblyId){
			//读数据库
			case 3:return FlowBussiness.readDb(parms);
			//随机采样
			case 5:return FlowBussiness.randomSampling(parms);
			//过滤与映射
			case 6:return FlowBussiness.filterMapping(parms);
			//JOIN
			case 9:return FlowBussiness.join(parms);
			//类型转换
			case 11:return FlowBussiness.typeConversion(parms);
			//增加序列号
			case 12:return FlowBussiness.addSerialNum(parms);
			//拆分
			case 13:return FlowBussiness.split(parms);
			//缺失值填充
			case 14:return FlowBussiness.fillMissingValues(parms);
			//归一化
			case 15:return FlowBussiness.normalization(parms);
			//标准化
			case 16:return FlowBussiness.standardization(parms);
			//Union
			case 17:return FlowBussiness.union(parms);
			//特征尺寸变换
			case 20:return FlowBussiness.featureSizeTransform(parms);
			//二值化
			case 21:return FlowBussiness.binaryzation(parms);
			//one-hot编码
			case 23:return FlowBussiness.oneHotEncoding(parms);
			//特征离散
			case 24:return FlowBussiness.discreteCharacteristics(parms);
			//主成分分析(PCA)
			case 25:return FlowBussiness.pca(parms);
			//特征重要性分析
			case 26:return FlowBussiness.importanceAnalysis(parms);
			//过滤式特征选择
			case 28:return FlowBussiness.filterFeature(parms);
			//线性回归
			case 50:return FlowBussiness.linearRegression(parms);
			//决策树回归
			case 52:return FlowBussiness.decisionTreeRegression(parms);
			//随机森林回归
			case 53:return FlowBussiness.randomForestRegression(parms);
			//梯度提升回归树
			case 56:return FlowBussiness.gradientBoostedTreesRegression(parms);
			//逻辑回归二分类
			case 57:return FlowBussiness.logisticRegressionBinaryClassification(parms);
			//逻辑回归多分类
			case 58:return FlowBussiness.logisticRegressionMultiClassification(parms);
			//决策树分类
			case 59:return FlowBussiness.decisionTreeClassification(parms);
			//随机森林分类
			case 60:return FlowBussiness.randomForestClassification(parms);
			//梯度提升分类树
			case 61:return FlowBussiness.gradientBoostingTreesClassification(parms);
			//KMeans聚类
			case 63:return FlowBussiness.kMeansBisecting(parms);
			//贝叶斯分类
			case 64:return FlowBussiness.naiveBayesClassification(parms);
			//支持向量机分类
			case 65:return FlowBussiness.svmClassifier(parms);
			//频繁项集挖掘算法
			case 66:return FlowBussiness.fpGrowth(parms);
			//协同过滤算法
			case 67:return FlowBussiness.collaborativeFiltering(parms);
			//推荐预测
			case 68:return FlowBussiness.collaborativeFilteringPrediction(parms);
			//分类预测
			case 69:return FlowBussiness.classifiedPrediction(parms);
			//回归预测
			case 70:return FlowBussiness.regressorPredict(parms);
			//聚类预测
			case 71:return FlowBussiness.bisectingPredict(parms);
			//推荐评估
			case 73:return FlowBussiness.collaborativeFilteringEvaluation(parms);
			//分类评估
			case 75:return FlowBussiness.classifiedEvaluation(parms);
			//回归评估
			case 77:return FlowBussiness.regressorEvaluation(parms);
			//聚类评估
			case 79:return FlowBussiness.bisectingEvaluation(parms);
			//文档主题生成模型
			case 81:return FlowBussiness.documentGenerationModel(parms);
			//词频－逆向文件频率特征
			case 82:return FlowBussiness.characteristicsReverseFile(parms);
			//字符串相似度  TODO//存在问题
			case 83:return FlowBussiness.stringSimilarity(parms);
			//停用词过滤
			case 84:return FlowBussiness.stopWordFiltering(parms);
			//Word2Vec
			case 85:return FlowBussiness.word2Vec(parms);
			//词频统计
			case 86:return FlowBussiness.frequencyCount(parms);
			//Split Word
			case 87:return FlowBussiness.splitWord(parms);
			//SQL脚本
			case 91:return FlowBussiness.sqlScript(parms);
			}
		}
		return "";
	}

}
