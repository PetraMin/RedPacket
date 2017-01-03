package cnhubei.rb.Utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mxp on 2016/6/21.
 */

public class CacheUtils {
    public CacheUtils() {
    }

    public static <T> void saveCacheObject(Context context, List<T> objects, String fileName) {
        saveCacheObject(objects, context.getCacheDir(), fileName);
    }

    public static boolean existCacheObjectFile(Context context, String fileName) {
        try {
            File e = context.getCacheDir();
            File file = new File(e, fileName);
            if(file.exists()) {
                return true;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return false;
    }

    public static boolean checkAndClearCacheObjectFile(Context context, String fileName) {
        try {
            File e = context.getCacheDir();
            File file = new File(e, fileName);
            if(file.exists()) {
                Log.w(context.getPackageName(),"缓存文件：" + file.getAbsolutePath());
                return file.delete();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return false;
    }

    public static <T> ArrayList<T> readCacheObject(Context context, String fileName) {
        ArrayList articles = null;

        try {
            File e = context.getCacheDir();
            File file = new File(e, fileName);
            if(!file.exists()) {
                Log.w(context.getPackageName(),"缓存文件不存在：" + file.getAbsolutePath());
                return null;
            }

            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            articles = (ArrayList)objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return articles;
    }

    public static <T> void saveCacheObject(List<T> objects, File Path, String fileName) {
        if(!Path.exists()) {
            Path.mkdir();
        }

        File file = new File(Path, fileName);

        try {
            if(file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileOutputStream e = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(e);
            objectOutputStream.writeObject(objects);
            objectOutputStream.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public static <T> T readSimpleCacheObject(Context context, String fileName) {
        Object obj = null;

        try {
            File e = context.getCacheDir();
            File file = new File(e, fileName);
            if(!file.exists()) {
                Log.w(context.getPackageName(),"缓存文件不存在：" + file.getAbsolutePath());
                return null;
            }

            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return (T) obj;
    }

    public static <T> void saveSimpleCacheObject(T obj, Context context, String fileName) {
        File folder = context.getCacheDir();
        if(!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(folder, fileName);

        try {
            if(file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileOutputStream e = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(e);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }
}
