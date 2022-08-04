package com.kenshiro.bootdocker;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, MyException {
        ClientGlobal.init("fdfs_client.conf");
//        System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());



        String filePath = "H:/vm/U098 慕羽茜/U098 (16).jpg";

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();
        StorageClient sc = new StorageClient(connection, null);

        int group1 = sc.delete_file("group1", "M00/00/00/CgAACl0opfuAH5SfAH3AAqdfW9Q013.png");
        System.out.println(group1);

        if (1 == 1) {
            return;
        }


        String[] pngs = sc.upload_file(filePath, "png", null);

        for (String png : pngs) {
            System.out.println(png);
        }
    }
}
