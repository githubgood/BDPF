package com.xunfang.bdpf.mllib.assembly.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.LibraryAssemblyService;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;

/**
 * 
 * @ClassName MachineLearningController
 * @Description: 机器学习控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月9日 下午5:18:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "machinelearning")
public class MachineLearningController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(MachineLearningController.class);
	
	@Autowired
	private ExperimentService  experimentService;

	@Autowired
	private LibraryAssemblyService libraryAssemblyService;
	
	@Autowired
	private AssemblyService assemblyService;
	
	
}