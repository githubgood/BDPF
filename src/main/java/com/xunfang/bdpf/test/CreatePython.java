package com.xunfang.bdpf.test;

import java.io.*;

public class CreatePython {
	
    BufferedReader bufferedReader;  
    BufferedWriter bufferedWriter;  
    InputStreamReader reader;  
	 /** 
     * 生成数据文件 
     *  
     * @param filePath 写入文件的路径 
     * @param content 写入的字符串内容 
     * @return 
     */  
    public boolean string2File(String content, String filePath) {  
        boolean flag = true;  
        try {  
            File file = new File(filePath);  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            bufferedReader = new BufferedReader(new StringReader(content));  
            bufferedWriter = new BufferedWriter(new FileWriter(file));  
            char buffer[] = new char[1024];  
            int len;  
            while ((len = bufferedReader.read(buffer)) != -1) {  
                bufferedWriter.write(buffer, 0, len);  
            }  
            bufferedWriter.flush();  
            bufferedReader.close();  
            bufferedWriter.close();  
        } catch (IOException e) {  
            flag = false;  
            return flag;  
        } finally {  
            if (bufferedReader != null) {  
                try {  
                    bufferedReader.close();  
                } catch (IOException e) {  
                    
                }  
            }  
        }  
        return flag;  
    }  
    
    public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }  
    
    public static void main(String[] args) {
    	CreatePython c=new CreatePython();
    	c.string2File("aaaa", "D:/vvvvv");
    }
    
}