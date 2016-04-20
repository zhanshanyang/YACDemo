package com.yangaiche.yackeeper.utils.log;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.yangaiche.yackeeper.base.MyApplication;
import com.yangaiche.yackeeper.login.io.DirectoryManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Taurus
 * 文件日志工具
 */
@SuppressLint("SimpleDateFormat")
public class LogFileUtils {

	public static final String EXCEPTION_TYPE_CRASH = "crash_exception";
	public static final String EXCEPTION_TYPE_HTTP = "http_exception";
	public static final String EXCEPTION_TYPE_PARSE = "parse_exception";
	public static final String EXCEPTION_TYPE_OTHER = "other_exception";

	private static String log_file_dir;
	private static String today_file_name;
	public static boolean allowW = true;
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	private static void init() {
		log_file_dir = DirectoryManager.getLogPath(MyApplication.getContext()) + "/";
		today_file_name = getNowFileName();
	}

	private static String getLogFileName() {
		init();
		return log_file_dir + today_file_name;
	}

	private static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}

	private static String getNowFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date()) + ".log";
	}
	
	public static void writoLog(String log){
		if(!allowW)
			return;
		appendMethodB(getLogFileName(), getHandledLog(log));
	}
	
	private static String getHandledLog(String log){
		return getNowTime() + "\t" + log + "\n";
	}

	private synchronized static void appendMethodB(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveExceptionInfo2File(String exceptionType, Throwable ex) {
		if(!allowW)
			return;
		StringBuffer sb = new StringBuffer();
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			String fileName = exceptionType + time + "-" + timestamp + ".log";
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				String path = DirectoryManager.getLogPath(MyApplication.getContext());
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + "/" + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
		} catch (Exception e) {
			LogUtil.d("an error occured while writing file...");
		}
	}


}
