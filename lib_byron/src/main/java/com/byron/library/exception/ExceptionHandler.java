package com.byron.library.exception;

import android.os.Environment;

import com.byron.library.ConfigFile;
import com.byron.library.ConfigLog;
import com.byron.library.file.FileOperate;
import com.byron.library.log.LogUtil;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by machuang on 2016/7/15.
 */
public final class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        saveCatchInfoToFile(ex);
    }

    private void saveCatchInfoToFile(Throwable e) {
        Writer w = new StringWriter();
        PrintWriter p = new PrintWriter(w);
        e.printStackTrace(p);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(p);
            cause = cause.getCause();
        }
        p.close();
        String info = w.toString();
        try {
            String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
            String fileName = time + ConfigFile.FILE_TYPE_EXCEPTION;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String filePath = Environment.getExternalStorageDirectory() + ConfigFile.PATH_EXCEPTION;
                File dir = new File(filePath);
                if (!dir.exists()) {
                    if (!dir.mkdirs()) {
                        //创建目录失败
                        LogUtil.e(ConfigLog.TAG_APP, "SAVE EXCEPTION INFO TO FILE FAIL : MAKEDIRS FAIL");
                        return;
                    }
                } else {
                    FileOperate.fileNumControl(dir);
                }
                FileOperate.saveStrToFile(fileName, filePath, info);
            }
        } catch (Exception e1) {
            LogUtil.e(ConfigLog.TAG_APP, "SAVE EXCEPTION INFO TO FILE FAIL :" + e.getCause());
        }
    }
}
