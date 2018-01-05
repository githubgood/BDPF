package com.xunfang.utils.office.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunfang.utils.office.domain.ConvertStatus;
import com.xunfang.utils.office.domain.SwfConfig;
import com.xunfang.utils.office.service.Pdf2SwfService;

/**
 * 将PDF转换为SWF文件
 *
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 16:27
 * @since Jdk1.7
 */
@Service
public class Pdf2SwfServiceImpl implements Pdf2SwfService {

    private Logger logger = Logger.getLogger(getClass());

    private SwfConfig swfConfig;

    @Autowired
    public void setSwfConfig(SwfConfig swfConfig) {
        this.swfConfig = swfConfig;
    }

    @Override
    public ConvertStatus pdf2swf(InputStream inputStream, OutputStream outputStream) {
        File inputFile = null;
        File outputFile = null;
        Process process = null;

        try {
            // 将输入流转换为本地文件存储到临时目录
            String tempPath = System.getProperty("java.io.tmpdir");
            inputFile = new File(tempPath,"xxx.pdf");
            FileOutputStream fos = new FileOutputStream(inputFile);

            IOUtils.copy(inputStream, fos);
            IOUtils.closeQuietly(fos);

            // 调用pdf2swf.exe将pdf文件转为swf存储到临时目录
            StringBuilder command = getCommand(inputFile);
            String commandStr = command.toString();
            logger.debug("exec command:" + commandStr);

            process = Runtime.getRuntime().exec(commandStr);

            IOUtils.copy(process.getInputStream(), System.out); // 参数链接：http://berdy.iteye.com/blog/810223
            IOUtils.copy(process.getErrorStream(), System.out); // 不打印子进程的输入流和错误流时，子进程出现死锁
            IOUtils.closeQuietly(process.getInputStream());
            IOUtils.closeQuietly(process.getErrorStream());

            int exitCode = process.waitFor(); // 等待子进程执行完成
            if (exitCode != 0) {
                logger.error("exec command:" + commandStr + " error! exitCode:" + exitCode);
                return ConvertStatus.CONVERT_PDF2SWF_COMMAND_ERROR;
            }

            // 将临时目录中的swf文件写到输出流
            outputFile = new File(inputFile.getPath() + ".swf");
            if (!outputFile.exists()) {
                throw new FileNotFoundException("outputFile not found! path:" + outputFile.getPath());
            }
            FileInputStream fis = new FileInputStream(outputFile);

            IOUtils.copy(fis, outputStream);
            IOUtils.closeQuietly(fis);

            return ConvertStatus.SUCCESS;
        } catch (IOException e) {
            logger.error("io exception!", e);
            return ConvertStatus.CONVERT_PDF2SWF_IO_ERROR;
        } catch (InterruptedException e) {
            logger.error("execute swf command error!", e);
            return ConvertStatus.CONVERT_PDF2SWF_COMMAND_INTERRUPT_ERROR;
        } finally {
            if (process != null) {
                process.destroy();
            }
            // 删除临时文件
            deleteFileQuietly(outputFile);
            deleteFileQuietly(inputFile);
        }
    }

    /**
     * 拼装命令
     *
     * @param inputFile
     * @return
     */
    private StringBuilder getCommand(File inputFile) {
        StringBuilder command = new StringBuilder();
        command.append(swfConfig.getFilePath() + " ");
        command.append(inputFile.getPath());
        command.append(" -o \"" + inputFile.getPath() + ".swf\"");
        command.append(" -z -s flashversion=9");
        command.append(" -s languagedir=\"" + swfConfig.getLanguageDir() + "\"");
        command.append(" -s storeallcharacters -f -j " + swfConfig.getQuality());
        // command.append(" -s storeallcharacters -f -p \"1-21\" -j " + swfConfig.getQuality());
        return command;
    }


    /**
     * 删除文件
     *
     * @param file
     */
    private void deleteFileQuietly(File file) {
        try {
            if (file != null && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            logger.error("deleteFileQuietly file error!", e);
        }
    }
}
