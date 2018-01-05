/**  
* @Title: ThreadUtil.java
* @Package com.xunfang.cloud.util
* @Description: ThreadUtil
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author huangjf
* @date 2017年6月14日 下午5:48:31
* @version V1.0  
*/
package com.xunfang.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/**
* @ClassName: ThreadUtil
* @Description: 线程执行任务数情况工具类，装载执行情况，如实时更新任务的执行信息、总数、正确数、错误数、完成数
* @author huangjf
* @date 2017年6月14日 下午5:48:31
*
*/  
public class ThreadUtil {

	
	/**
	* @Fields threadCash : 线程执行任务数情况数据集
	*/
	public static Map<Object, Object> threadCash = Collections.synchronizedMap(new HashMap<Object, Object>());
	
	
	/**
	* @Fields SPLIT_REGEX : 连接符
	*/
	public static String SPLIT_REGEX = "##=##";
	
	/**
	* @Fields TOTAL_COUNT : 总数，作为key
	*/
	public static String TOTAL_COUNT = "TOTAL_COUNT";
	
	/**
	* @Fields TOTAL_COUNT_MARK : 总数初始化标记，作为key
	*/
	public static String TOTAL_COUNT_MARK = "TOTAL_COUNT_MARK";
	
	/**
	* @Fields FINISH_COUNT : 完成数，作为key
	*/
	public static String FINISH_COUNT = "FINISH_COUNT";
	
	/**
	* @Fields OVER_COUNT : 正确数，作为key
	*/
	public static String OVER_COUNT = "OVER_COUNT";
	
	/**
	* @Fields ERROR_COUNT : 错误数，作为key
	*/
	public static String ERROR_COUNT = "ERROR_COUNT";
	
	/**
	* @Title: Initialize
	* @Description: 根据线程唯一码对线程执行任务初始化
	* @param threadId 线程唯一码
	* void
	*/
	public static void Initialize(String threadId){
		threadCash.put(threadId + SPLIT_REGEX + TOTAL_COUNT, 0);
		threadCash.put(threadId + SPLIT_REGEX + FINISH_COUNT , 0);
		threadCash.put(threadId + SPLIT_REGEX + OVER_COUNT , 0);
		threadCash.put(threadId + SPLIT_REGEX + ERROR_COUNT , 0);
		threadCash.put(threadId + SPLIT_REGEX + TOTAL_COUNT_MARK, 0);
	}
	
	/**
	* @Title: setThreadCash
	* @Description: 设置增加线程执行任务情况信息描述
	* @param threadId 线程唯一码
	* @param obj 信息描述
	* void
	*/
	public static void setThreadCash(String threadId, Object obj){
		threadCash.put(threadId, obj);
	}
	
	
	/**
	* @Title: getThreadCash
	* @Description: 根据线程唯一码获取线程执行任务情况信息描述
	* @param threadId 线程唯一码
	* @return
	* String
	*/
	public static String getThreadCash(String threadId){
		return (String) threadCash.get(threadId);
	}
	
	
	/**
	* @Title: refresh
	* @Description: 线程执行任务成功后刷新完成数量及正确数量
	* @param threadId 线程唯一码
	* @param step 刷新数
	* void
	*/
	public static synchronized void refresh(String threadId, long step){
		setFinishCount(getFinishCount(threadId) + step, threadId);
		setOverCount(getTotalCount(threadId) - getFinishCount(threadId) - getErrorCount(threadId), threadId);
	}
	
	
	/**
	* @Title: destory
	* @Description: 销毁线程执行任务情况
	* @param threadId 线程唯一码
	* void
	*/
	public static void destory(String threadId){
		threadCash.remove(threadId);
		threadCash.remove(threadId + SPLIT_REGEX + TOTAL_COUNT);
		threadCash.remove(threadId + SPLIT_REGEX + FINISH_COUNT);
		threadCash.remove(threadId + SPLIT_REGEX + OVER_COUNT);
		threadCash.remove(threadId + SPLIT_REGEX + TOTAL_COUNT_MARK);
	}
	
	
	/**
	* @Title: setTotalCount
	* @Description: 设置总数
	* @param totalCount 总数
	* @param threadId 线程唯一码
	* void
	*/
	public static void setTotalCount(long totalCount, String threadId){
		threadCash.put(threadId + SPLIT_REGEX + TOTAL_COUNT , totalCount);
	}
	
	
	/**
	* @Title: setTotalCountMark
	* @Description: 设置总数标记
	* @param mark 标记值
	* @param threadId 线程唯一码
	* void
	*/
	public static void setTotalCountMark(long mark, String threadId){
		threadCash.put(threadId + SPLIT_REGEX + TOTAL_COUNT_MARK, mark);
	}
	
	
	/**
	* @Title: getTotalCountMark
	* @Description: 获取总数标记
	* @param threadId 线程唯一码
	* @return
	* long
	*/
	public static long getTotalCountMark(String threadId){
		if(threadCash.containsKey(threadId + SPLIT_REGEX + TOTAL_COUNT_MARK))
			return Long.parseLong(String.valueOf(threadCash.get(threadId + SPLIT_REGEX + TOTAL_COUNT_MARK)));
		return 0;
	}
	
	
	/**
	* @Title: getTotalCount
	* @Description: 获取总数
	* @param threadId 线程唯一码
	* @return
	* long
	*/
	public static long getTotalCount(String threadId){
		if(threadCash.containsKey(threadId + SPLIT_REGEX + TOTAL_COUNT))
			return Long.parseLong(String.valueOf(threadCash.get(threadId + SPLIT_REGEX + TOTAL_COUNT)));
		return 0;
	}
	
	
	/**
	* @Title: setFinishCount
	* @Description: 设置完成数
	* @param finishCount 完成数
	* @param threadId 线程唯一码
	* void
	*/
	private static void setFinishCount(long finishCount, String threadId){
		threadCash.put(threadId + SPLIT_REGEX + FINISH_COUNT , finishCount);
	}
	
	
	/**
	* @Title: getFinishCount
	* @Description: 获取完成数
	* @param threadId 线程唯一码
	* @return
	* long
	*/
	public static long getFinishCount(String threadId){
		if(threadCash.containsKey(threadId + SPLIT_REGEX + FINISH_COUNT))
			return Long.parseLong(String.valueOf(threadCash.get(threadId + SPLIT_REGEX + FINISH_COUNT)));
		return 0;
	}
	
	
	/**
	* @Title: setOverCount
	* @Description: 设置正确数
	* @param overCount 正确数
	* @param threadId 线程唯一码
	* void
	*/
	private static void setOverCount(long overCount, String threadId){
		threadCash.put(threadId + SPLIT_REGEX + OVER_COUNT , overCount);
	}
	
	
	/**
	* @Title: getOverCount
	* @Description: 获取正确数
	* @param threadId 线程唯一码
	* @return
	* long
	*/
	public static long getOverCount(String threadId){
		if(threadCash.containsKey(threadId + SPLIT_REGEX + OVER_COUNT))
			return Long.parseLong(String.valueOf(threadCash.get(threadId + SPLIT_REGEX + OVER_COUNT)));
		return 0;
	}
	
	
	/**
	* @Title: setErrorCount
	* @Description: 设置错误数
	* @param errorCount 错误数
	* @param threadId 线程唯一码
	* void
	*/
	public static void setErrorCount(long errorCount, String threadId){
		setOverCount(getOverCount(threadId) - errorCount, threadId);
		threadCash.put(threadId + SPLIT_REGEX + ERROR_COUNT , getErrorCount(threadId) + errorCount);
	}
	
	
	/**
	* @Title: getErrorCount
	* @Description: 获取错误数
	* @param threadId 线程唯一码
	* @return
	* long
	*/
	public static long getErrorCount(String threadId){
		if(threadCash.containsKey(threadId + SPLIT_REGEX + ERROR_COUNT))
			return Long.parseLong(String.valueOf(threadCash.get(threadId + SPLIT_REGEX + ERROR_COUNT)));
		return 0;
	}
}
