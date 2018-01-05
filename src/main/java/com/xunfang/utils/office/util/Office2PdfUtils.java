package com.xunfang.utils.office.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.xunfang.utils.office.domain.ConvertStatus;
import com.xunfang.utils.office.service.File2PdfService;
import com.xunfang.utils.office.service.impl.Doc2PdfServiceImpl;
import com.xunfang.utils.office.service.impl.Excel2PdfServiceImpl;
import com.xunfang.utils.office.service.impl.PPT2PdfServiceImpl;
import com.xunfang.utils.office.service.impl.Txt2PdfServiceImpl;
/**
 * 
 * @ClassName Office2PdfUtils
 * @Description: office文件转换为pdf文件工具类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月22日 下午4:28:24
 * @version V1.0
 */
public class Office2PdfUtils {
	/**
	 * 
	  * @Title: doc2Pdf
	  * @Description: doc文件转pdf文件 
	  * @param @param inputStream  要转换的输入流对象
	  * @param @param outputFile   转换成功后输出的文件路径
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean doc2Pdf (InputStream inputStream,String outputFile) throws Exception{
      //构建输出流对象
        OutputStream outputStream = new FileOutputStream(outputFile+".pdf");
        //执行转换的方法
        File2PdfService fileConvertService = new Doc2PdfServiceImpl();
        ConvertStatus status = fileConvertService.convert2Pdf(inputStream, outputStream);
        outputStream.flush();
        //关闭流
        outputStream.close();
        return (status == ConvertStatus.SUCCESS);
	}
	/**
	 * 
	  * @Title: ppt2Pdf
	  * @Description: ppt文件转pdf文件
	  * @param @param inputStream  要转换的输入流对象
	  * @param @param outputFile   转换成功后输出的文件路径
	  * @param @throws Exception    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean ppt2Pdf (InputStream inputStream,String outputFile) throws Exception{
		//构建输出流对象
        OutputStream outputStream = new FileOutputStream(outputFile);
      //执行转换的方法
        File2PdfService fileConvertService = new PPT2PdfServiceImpl();
        ConvertStatus status = fileConvertService.convert2Pdf(inputStream, outputStream);
        outputStream.flush();
        //关闭流
        outputStream.close();
        return (status == ConvertStatus.SUCCESS);
	}
	/**
	 * 
	  * @Title: excel2Pdf
	  * @Description: excel文件转pdf文件
	  * @param @param inputStream  要转换的输入流对象
	  * @param @param outputFile   转换成功后输出的文件路径
	  * @param @throws Exception    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean excel2Pdf (InputStream inputStream,String outputFile) throws Exception{
		//构建输出流对象
        OutputStream outputStream = new FileOutputStream(outputFile);
      //执行转换的方法
        File2PdfService fileConvertService = new Excel2PdfServiceImpl();
        ConvertStatus status = fileConvertService.convert2Pdf(inputStream, outputStream);
        outputStream.flush();
        //关闭流
        outputStream.close();
        return (status == ConvertStatus.SUCCESS);
	}
	/**
	 * 
	  * @Title: txt2Pdf
	  * @Description: txt文件转pdf文件
	  * @param @param inputStream  要转换的输入流对象
	  * @param @param outputFile   转换成功后输出的文件路径
	  * @param @throws Exception    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean txt2Pdf (InputStream inputStream,String outputFile) throws Exception{
		//构建输出流对象
        OutputStream outputStream = new FileOutputStream(outputFile);
      //执行转换的方法
        File2PdfService fileConvertService = new Txt2PdfServiceImpl();
        ConvertStatus status = fileConvertService.convert2Pdf(inputStream, outputStream);
        outputStream.flush();
        //关闭流
        outputStream.close();
        return (status == ConvertStatus.SUCCESS);
	}
	
	/**
	 * 
	  * @Title: toConversion
	  * @Description: 根据不同的文件类型执行不同的转换方法
	  * @param @param fileName  文件名
	  * @param @param inputStream
	  * @param @param outputFile
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public boolean toConversion(String fileName,InputStream inputStream,String outputFile) throws Exception{
		String prefix=fileName.substring(fileName.lastIndexOf(".")+1);//获取到文件扩展名
	      System.out.println(prefix);
		if(prefix.equals("doc")){
			return doc2Pdf(inputStream, outputFile);
		}if(prefix.equals("xls")){
			return excel2Pdf(inputStream, outputFile);
		}if(prefix.equals("ppt")){
			return ppt2Pdf(inputStream, outputFile);
		}if(prefix.equals("txt")){
			return txt2Pdf(inputStream, outputFile);
		}
		return false;
	}
	
}
