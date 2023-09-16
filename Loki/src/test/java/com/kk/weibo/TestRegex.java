package com.kk.weibo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("d:/tt.txt")));
		StringBuffer sb = new StringBuffer();
		String str;
		while((str = br.readLine()) != null) {
			sb.append(str + "\n");
		}
		String s = sb.toString();
		
		Pattern p = Pattern.compile("(\\$CONFIG\\[\\'onick\\'\\]=\\')(.*)(\\')");
		Matcher m = p.matcher(s);
		p(m.find());
		p(m.group(2));
	}
	public static void p(Object object) {
		System.out.println(object);
	}
}
