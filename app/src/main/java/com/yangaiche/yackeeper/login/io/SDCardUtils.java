package com.yangaiche.yackeeper.login.io;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

import com.yangaiche.yackeeper.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * SD卡工具类
 * */
public class SDCardUtils {
	private static SDCardInfo SDCARD_INTERNAL = null;
	private static SDCardInfo SDCARD_EXTERNAL = null;

	/**
	 * API14以下通过读取Linux的vold.fstab文件来获取SDCard信息
	 * 
	 * @return
	 */
	public static void getSDCardInfoBelow14() {
		BufferedReader bufferedReader = null;
		List<String> dev_mountStrs = null;
		try {
			// API14以下通过读取Linux的vold.fstab文件来获取SDCard信息
			bufferedReader = new BufferedReader(new FileReader(Environment
					.getRootDirectory().getAbsoluteFile()
					+ File.separator
					+ "etc" + File.separator + "vold.fstab"));
			dev_mountStrs = new ArrayList<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith("dev_mount")) {
					dev_mountStrs.add(line);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; dev_mountStrs != null && i < dev_mountStrs.size(); i++) {
			SDCardInfo sdCardInfo = new SDCardInfo();
			String[] infoStr = dev_mountStrs.get(i).split(" ");
			sdCardInfo.label = infoStr[1];
			sdCardInfo.mountPoint = infoStr[2];
			if (sdCardInfo.mountPoint.equals(Environment
					.getExternalStorageDirectory().getAbsolutePath())) {
				sdCardInfo.mounted = Environment.getExternalStorageState()
						.equals(Environment.MEDIA_MOUNTED);
				SDCARD_INTERNAL = sdCardInfo;
			} else if (sdCardInfo.mountPoint.startsWith("/mnt")
					&& !sdCardInfo.mountPoint.equals(Environment
							.getExternalStorageDirectory().getAbsolutePath())) {
				File file = new File(sdCardInfo.mountPoint + File.separator
						+ "temp");
				if (file.exists()) {
					sdCardInfo.mounted = true;
				} else {
					if (file.mkdir()) {
						file.delete();
						sdCardInfo.mounted = true;
					} else {
						sdCardInfo.mounted = true;
					}
				}
				SDCARD_EXTERNAL = sdCardInfo;
			}
		}
	}

	/**
	 * API14以上包括14通过改方法获取SDCard挂载信息
	 * 
	 * @param context
	 * @return
	 */
	public static void getSDCardInfo(Context context) {
		String[] storagePathList = null;
		try {
			StorageManager storageManager = (StorageManager) context
					.getSystemService(Context.STORAGE_SERVICE);
			Method getVolumePaths = storageManager.getClass().getMethod(
					"getVolumePaths");
			storagePathList = (String[]) getVolumePaths.invoke(storageManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (storagePathList != null && storagePathList.length > 0) {
			String mSDCardPath = storagePathList[0];
			SDCardInfo internalDevInfo = new SDCardInfo();
			internalDevInfo.mountPoint = mSDCardPath;
			internalDevInfo.mounted = checkSDCardMount14(context, mSDCardPath);
			SDCARD_INTERNAL = internalDevInfo;
			if (storagePathList.length >= 2) {
				String externalDevPath = storagePathList[1];
				SDCardInfo externalDevInfo = new SDCardInfo();
				externalDevInfo.mountPoint = storagePathList[1];
				externalDevInfo.mounted = checkSDCardMount14(context,
						externalDevPath);
				SDCARD_EXTERNAL = externalDevInfo;
			}
		}
	}

	/**
	 * @Description:判断SDCard是否挂载上,返回值为true证明挂载上了，否则未挂载
	 * @param context
	 *            上下文
	 * @param mountPoint
	 *            挂载点
	 */
	protected static boolean checkSDCardMount14(Context context,
			String mountPoint) {
		if (mountPoint == null) {
			return false;
		}
		StorageManager storageManager = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);
		try {
			Method getVolumeState = storageManager.getClass().getMethod(
					"getVolumeState", String.class);
			String state = (String) getVolumeState.invoke(storageManager,
					mountPoint);
			return Environment.MEDIA_MOUNTED.equals(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 监测设备是否具有SD
	 * 
	 * @param context
	 * @return 0:没有挂在,1:内置存储,2:外置存储,3:内置、外置都有.
	 */
	public static void checkSDCardStatus(Context context) {
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			// 当前API>=14
			getSDCardInfo(context);
		} else {
			// 当前API<14
			getSDCardInfoBelow14();
		}
	}

	/**
	 * 获取设备SD卡的总容量 MB
	 * */
	public static float SDCardSize(Context context) {
		android.os.StatFs statfs = null;
		if (SDCARD_EXTERNAL == null || SDCARD_INTERNAL == null) {
			checkSDCardStatus(context);
		}
		if (SDCARD_INTERNAL != null && SDCARD_INTERNAL.mounted) {// sdcard0有挂在
			// 取得sdcard文件路径
			File pathFile = Environment
					.getExternalStorageDirectory();
			statfs = new android.os.StatFs(pathFile.getPath());
		} else if (SDCARD_EXTERNAL != null && SDCARD_EXTERNAL.mounted) {// sdcard0挂在不存在，切换到sdcard1
			String esDir = SDCARD_EXTERNAL.mountPoint;
			statfs = new android.os.StatFs(esDir);
		}
		if (statfs != null) {
			// 获取SDCard上BLOCK总数
			long nTotalBlocks = statfs.getBlockCount();
			// 获取SDCard上每个block的SIZE
			long nBlocSize = statfs.getBlockSize();
			// 获取剩下的所有Block的数量(包括预留的一般程序无法使用的块)
			// long nFreeBlock = statfs.getFreeBlocks();
			// 计算SDCard 总容量大小MB
			float nSDTotalSize = (nTotalBlocks * nBlocSize) / 1024.0f / 1024.0f;
			return nSDTotalSize;
		}
		return 0f;
	}

	/**
	 * 获取设备SD卡的剩余容量 MB
	 * */
	public static float SDCardRemainSize(Context context) {
		android.os.StatFs statfs = null;
		if (SDCARD_EXTERNAL == null || SDCARD_INTERNAL == null) {
			checkSDCardStatus(context);
		}
		if (SDCARD_INTERNAL != null && SDCARD_INTERNAL.mounted) {// sdcard0有挂在
			// 取得sdcard文件路径
			File pathFile = Environment
					.getExternalStorageDirectory();
			statfs = new android.os.StatFs(pathFile.getPath());
		} else if (SDCARD_EXTERNAL != null && SDCARD_EXTERNAL.mounted) {// sdcard0挂在不存在，切换到sdcard1
			String esDir = SDCARD_EXTERNAL.mountPoint;
			statfs = new android.os.StatFs(esDir);
		}
		if (statfs != null) {
			// 获取SDCard上每个block的SIZE
			long nBlocSize = statfs.getBlockSize();
			// 获取可供程序使用的Block的数量
			long nAvailaBlock = statfs.getAvailableBlocks();
			// 计算 SDCard 剩余大小MB
			float nSDFreeSize = (nAvailaBlock * nBlocSize) / 1024.0f / 1024.0f;
			return nSDFreeSize;
		}
		return 0f;
	}

	public static String getCacheDir(Context context) {
		String esDir = null;
		try {
			esDir=context.getExternalCacheDir().toString();
		} catch (Exception e) {
			esDir=context.getCacheDir().toString();
		}
		LogUtil.d("cache_dir : " + esDir);
		return esDir;
	}

	static class SDCardInfo {
		/** 名称 */
		public String label;
		/** 挂载点 */
		private String mountPoint;
		/** 是否已挂载 */
		private boolean mounted;

	}
}
