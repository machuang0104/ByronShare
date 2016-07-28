package com.byron.library.file;

import com.byron.library.ConfigFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sinyoo on 2016/7/15.
 */
public final class FileOperate {
    /*保存信息到文件，fileName包含文件类型；如: .txt
    * */
    public static final void saveStrToFile(String fileName, String filePath, String info) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath + fileName);
            fos.write(info.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * 如果文件数达到配置的数量，清除一次，以后可以按时间或仿Lru缓存策略删除
    * */
    public static final void fileNumControl(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            int l = files.length;
            if (l == ConfigFile.FILE_EXCEPTION_MAXNUM) {
                for (int i = 0; i < ConfigFile.FILE_EXCEPTION_MAXNUM; i++) {
                    files[i].delete();
                }
            }
        }
    }
}
