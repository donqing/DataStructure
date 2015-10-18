package com.datastructure.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Chapter1 {

	public static void main(String[] args) {
		String str = "abad";
		List<String> list = permute(str);
		System.out.println(list+""+list.size());
	}
	
	public static List<String> permute(String s){
		List<String> list = new ArrayList<String>();
		if(s ==null){
			return null;
		}else if(s.length()==0){
			list.add("");
			return list;
		}
		char tmp = s.charAt(0);
		String reminder = s.substring(1);
		List<String> lists = permute(reminder);
		for (String string : lists) {
			for(int i=0;i<=string.length();i++)
			list.add(insertChar(string,tmp,i));
		}
		return list;
		
	}
	
	public static String insertChar(String str, char ch, int i){
		String str1 = str.substring(0,i);
		String str2 = str.substring(i, str.length());
		return str1+ch+str2;
	}
}
