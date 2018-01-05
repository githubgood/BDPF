package com.xunfang.bdpf.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunPythonScript {
	
	public boolean RunPython(String path){
		boolean isDone=true;
		 try {
			   System.out.println("start");
			   String [] cmd={"/bin/sh","-c",path}; 
			   //String [] cmd={"cmd","/c","echo %date%"}; 
			 // String [] cmd={"/bin/sh","-c","fab -f start.py start"}; 
			  Process pr = Runtime.getRuntime().exec(cmd);
			 //  Process pr = Runtime.getRuntime().exec("python D:/a.py");
			 // Process pr = Runtime.getRuntime().exec("python /opt/start.py");
			   BufferedReader in = new BufferedReader(new InputStreamReader(
			     pr.getInputStream()));
			   String line;
			   while ((line = in.readLine()) != null) {
			    System.out.println(line);
			   }
			   in.close();
			   pr.waitFor();
			   System.out.println("end");
			  } catch (Exception e) {
				  isDone=false;
			   e.printStackTrace();
			  }
		 return isDone;
	}
	
 public static void main(String[] args) {
     new RunPythonScript();
 }
}