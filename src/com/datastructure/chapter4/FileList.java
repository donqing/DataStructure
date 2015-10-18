package com.datastructure.chapter4;

import java.io.File;

public class FileList {
	public static void main(String[] args) {
		FileList fileList = new FileList();
		File file = new File("F:"+File.separator+"class");
		fileList.list(file);
	}
	
	
	public void list(File file){
		printName(file);
		if(file.isDirectory()){
			File[] list = file.listFiles();
			for (File file2 : list) {
				list(file2);
			}
		}
	}
	
	public void printName(File file){
		String name = file.getName();
		if(file.isDirectory())
			System.out.println("Dir: "+name);
		else
			System.out.println(file.getName()+" "+file.length());
	}

}
