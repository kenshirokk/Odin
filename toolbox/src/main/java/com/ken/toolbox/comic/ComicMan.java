package com.ken.toolbox.comic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ComicMan {

    public static void main(String[] args) throws IOException {

        //d:\picture\comic
        String fileRoot = "K:\\picture\\comic";
        //d:\picture\html\
        String newFile = "K:\\picture\\html\\";

        String str1 = "<img src='";
        String str2 = "' class='img-responsive' alt=''>";

        String f1 = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->\n" +
                "\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap" +
                ".min.css\" integrity=\"sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu\" " +
                "crossorigin=\"anonymous\">\n" +
                "\n" +
                "\t<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->\n" +
                "\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/3.4" +
                ".1/css/bootstrap-theme.min.css\" integrity=\"sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7" +
                "/0pbek5mMa1upzvWbrUbOZ\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "\t<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->\n" +
                "\t<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\" " +
                "integrity=\"sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd\" " +
                "crossorigin=\"anonymous\"></script>\n" +
                "\t<title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t\n" +
                "\t<div class=\"container-fluid\">\n" +
                "  \t<div class=\"row\">";
        String f2 = "\t</div>\n" +
                "\t</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        File f = new File(fileRoot);
        File[] files = f.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                continue;
            }
            String hname = file.getName();
            File[] pics = file.listFiles();
            StringBuilder sb = new StringBuilder();
            for (File pic : pics) {
                String picName = pic.getAbsolutePath();
                sb.append(str1 + picName + str2);
            }
            File ff = new File(newFile + hname+".html");
            if (!ff.exists()) {
                ff.createNewFile();
                try (FileWriter fw = new FileWriter(ff)) {
                    fw.write(f1+sb.toString()+f2);
                    fw.flush();
                }
            }
        }
    }
}
