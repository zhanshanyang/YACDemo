package com.yangaiche.yackeeper.login.io;

import android.content.Context;

import com.yangaiche.yackeeper.base.MyApplication;
import com.yangaiche.yackeeper.utils.log.LogUtil;

import java.io.File;

/**
 * Created by Taurus on 2015/12/14.
 * 文件目录与路径管理器
 */
public class DirectoryManager {

    private static final String DIRECTORY_NAME_IKAN = "ikan";/*缓存目录下ikan名称*/
    private static final String DIRECTORY_NAME_IKAN_WEB = "ikanweb";/*本地H5资源目录名称*/
    private static final String DIRECTORY_NAME_VIDEO = ".video";/*缓存的视频目录名称*/
    private static final String DIRECTORY_NAME_IMAGE_CACHE = ".ImageCache";/*图片缓存的视频目录名称*/
    private static final String DIRECTORY_NAME_LOG = "log";/*日志文件目录名称*/
    private static final String DIRECTORY_NAME_GLIDE = "glide";/*图片缓存目录名称*/
    private static final String DIRECTORY_NAME_TEMP = "temp";/*临时目录*/

    public static String IKAN_WEB = "/.ikanweb/";// 目录
    public static String ASS_WEB = "/.tmpikanweb/";
    public static String VIDEO_DIR = "/.video/";// 目录
    public static String H5_IMAGE_CACHE_DIR = "/.ImageCache/";// 目录

    public static final String SCHEME_HEADER = "file://";
    public static String fileScheme = SCHEME_HEADER;

    private static String cameraPath;

    public static void init(){
        IKAN_WEB = getIkanWebPath(MyApplication.getContext());
        ASS_WEB = getCacheIkanPath(MyApplication.getContext());

        String ikanCache = getCacheIkanPath(MyApplication.getContext());
        H5_IMAGE_CACHE_DIR = ikanCache + File.separator + DIRECTORY_NAME_IMAGE_CACHE;
        VIDEO_DIR = ikanCache + File.separator + DIRECTORY_NAME_VIDEO;
        fileScheme = SCHEME_HEADER + IKAN_WEB;
//        fileScheme = "http://" + SystemInitialization.ikan.get("httphost");
    }

    /**
     * 获取ikan下的ikanweb目录
     * @param context
     * @return
     */
    public static String getIkanWebPath(Context context){
        String ikanWebPath = getCacheIkanPath(context);
        String resultPath = null;
        if(ikanWebPath!=null){
            File file = new File(ikanWebPath,DIRECTORY_NAME_IKAN_WEB);
            if(!file.exists()){
                file.mkdirs();
            }
            resultPath = file.getAbsolutePath();
        }
        LogUtil.d("ikanwebpath = " + resultPath);
        return resultPath;
    }

    public static long getIkanWebLastModify(Context context){
        File file = new File(getIkanWebPath(context));
        if(file.exists()){
            return file.lastModified();
        }
        return 0;
    }

    /**
     * 获取cache下的ikan目录
     * @param context
     * @return
     */
    public static String getCacheIkanPath(Context context){
        String cachePath = getCachePath(context);
        File file = new File(cachePath);
        String cacheRoot = file.getParentFile().getAbsolutePath();
        String ikanPath = cacheRoot + File.separator + DIRECTORY_NAME_IKAN;
        LogUtil.d("directory_manager : ikanpath = " + ikanPath);
        File ikan = new File(ikanPath);
        if(!ikan.exists()){
            boolean result = ikan.mkdirs();
            LogUtil.d("directory_manager : mkdirs = " + result);
        }
        return ikanPath;
    }

    /**
     * 获取cache目录
     * @param context
     * @return
     */
    public static String getCachePath(Context context){
        return SDCardUtils.getCacheDir(context);
    }

    /**
     * 获取glide图片加载库的缓存路径
     * @param context
     * @return
     */
    public static String getGlideImageCachePath(Context context){
        String path = getCachePath(context);
        File file = new File(path,DIRECTORY_NAME_GLIDE);
        if(!file.exists()){
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /**
     * 获取日志文件路径
     * @param context
     * @return
     */
    public static String getLogPath(Context context){
        String path = getCachePath(context);
        File log = new File(path,DIRECTORY_NAME_LOG);
        if(!log.exists()){
            log.mkdirs();
        }
        return log.getAbsolutePath();
    }

    /**
     *
     * @param context
     * @return
     */
    public static String getTempPath(Context context){
        String path = getCachePath(context);
        File temp = new File(path,DIRECTORY_NAME_TEMP);
        if(!temp.exists()){
            temp.mkdirs();
        }
        return temp.getAbsolutePath();
    }

    public static File getTempCameraFile(Context context){
        File file = new File(getTempPath(context),System.currentTimeMillis() + ".jpg");
        cameraPath = file.getAbsolutePath();
        return file;
    }

    public static String getCameraPath(){
        return cameraPath;
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isFile()) {
                    item.delete();
                } else {
                    deleteFilesByDirectory(item);
                }
            }
        }
    }
}
