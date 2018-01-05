package com.xunfang.bdpf.mllib.experiment.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.mllib.experiment.vo.TableVo;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;

/**
 * 
 * @ClassName: DataSourceController
 * @Description: 数据源模块控制器
 * @author: yaolianhua
 * @date: 2017年10月9日 下午4:02:33
 */
@Controller
@RequestMapping(value = "datasource")
public class DataSourceController {
	
	private static Logger log = Logger.getLogger(DataSourceController.class);
	
	@Autowired
	private DataSourceService dataSourceService;

	@RequestMapping(value="fileRead")
	@ResponseBody
	public Object fileRead(@RequestParam(value="file",required =false)MultipartFile file,HttpSession session){
		log.info(" = = = = = DataSourceController fileRead in = = = = =");
		String[] text = null;
		List<String> textList = new ArrayList<>();
		try{
			text = dataSourceService.readData(file);
			textList = Arrays.asList(text);
			textList = textList.size() > 100 ? textList.subList(0,100) : textList;
		}catch(Exception e){
			log.error("= = = = = DataSourceController fileRead error = = = = =");
			e.printStackTrace();
			return new Feedback(false,e.getMessage(),textList);
		}
		
		return new Feedback(true,"文件信息读取成功",textList);
	}

	@RequestMapping(value ="checkTableName")
	@ResponseBody
	public Object checkTableName(HttpSession session ,TableVo vo){
		log.info(" = = = = = DataSourceController checkTableName in = = = = =");
		try{
			User user = (User) session.getAttribute("loginuser");
			if(user == null) return new Feedback(false,"未登录或登录超时，请重新登录！");
			vo.setAccount(user.getId());
			
			boolean isExist = dataSourceService.checkTableName(vo);
			if(!isExist) return new Feedback(false,"表不存在，请先设计表结构");
			
			if(isExist){
				List<TableChild> tcList = dataSourceService.findTableChilds(vo);
				return new Feedback(true,"表已经存在，可以直接上传数据",tcList);
			}
		}catch(Exception e){
			log.error(" = = = = = DataSourceController checkTableName error = = = = =");
			e.printStackTrace();
			return new Feedback(false,"校验异常"+e.getMessage()+"");
		}
		
		return null;
	}
	@RequestMapping(value ="save")
	@ResponseBody
	public Object saveDataSource(TableVo vo,HttpSession session,@RequestParam(value = "file",required = false) MultipartFile file){
		log.info("= = = = = DataSourceController saveDataSource in = = = = =");
		User user = (User) session.getAttribute("loginuser");
		if(user == null) return new Feedback(false,"未登录或登录超时，请重新登录");
		boolean flag = false;
		boolean isTableName = false;//建表数据是否存在
		boolean isTable =false;//数据表是否存在
		String[] text = null;
		Feedback fb = new Feedback();
		vo.setAccount(user.getId());//用户id
		try{
			isTable = dataSourceService.isTableExist(vo.getName());
			isTableName = dataSourceService.checkTableName(vo);
			text = dataSourceService.readData(file);
			if(isTableName){//表名已经存在，追加数据
				if(!isTable)//数据表若不存在，新建数据表
					dataSourceService.createTable(vo);//库中新建数据表
				fb = dataSourceService.insertData(text, vo);
			}
			if(!isTableName){
				flag = dataSourceService.insertTableAndChild(vo);//保存建表数据
				if(!flag) 
					return new Feedback(false,"表创建失败");
				dataSourceService.createTable(vo);//库中新建数据表
				fb = dataSourceService.insertData(text, vo);
			}
			/*		
			if (!fb.isSuccessful()) {
				boolean b = dataSourceService.isTableExist(vo.getName());
				if (b) {
					dataSourceService.deleteTable(vo.getName());
				}
			}
			*/
			return fb;
		}catch(Exception e){
			log.error("= = = = = DataSourceController saveDataSource error = = = = =");
			e.printStackTrace();
			return new Feedback(false,"数据源创建失败"+e.getMessage()+"");
		}
		
	}
	
	
}