package com.xunfang.bdpf.test;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ss = "http://192.168.98.197:8083/res/doc/f7fc274029453e71447736f845449b3b..pdf";
		String[] split = ss.split("res");
		 String suff = ".doc";
		 boolean contains = split[1].contains(".doc");
		 if(split[1].contains(".doc") || split[1].contains(".xls") || split[1].contains(".ppt")|| split[1].contains(".txt")){
			 System.out.println("////////////");
		 }
		System.out.println(split[1]+"    "+contains);
	}

}
