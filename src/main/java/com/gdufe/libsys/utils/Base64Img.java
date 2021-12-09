package com.gdufe.libsys.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Base64Img {
    /**
     * 存储base64图片
     * @param imgStr
     * @param path
     * @return
     */
    public static boolean generateImage(String imgStr, String base64, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()){// 先创建文件夹和文件
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
