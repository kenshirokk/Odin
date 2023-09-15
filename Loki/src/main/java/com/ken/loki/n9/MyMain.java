package com.msw.java;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MyMain {
    public static void main(String[] args) throws IOException {


        XWPFDocument doc = new XWPFDocument(new FileInputStream("I:/Temp/数据库表结构(MySQL).docx"));

        List<XWPFTable> tables = doc.getTables();
        System.out.println(tables.size());
    }
}
