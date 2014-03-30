package info.jackrex.androidcachefoundation.cachefoundation.cache;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Jackrex on 2/24/14.
 */
public class SharePreferenceStorageService {

    private static SharePreferenceStorageService preferenceStorageService;
    private Context context;

    public SharePreferenceStorageService(Context context) {
        this.context=context;
    }


    public static SharePreferenceStorageService newInstance(Context context){

        if(preferenceStorageService == null){
            preferenceStorageService =  new SharePreferenceStorageService(context);
        }

        return  preferenceStorageService;
    }


    /**
     * 第一次启动的flag
     * @return
     */
    public boolean isFirstLaunch(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getBoolean("isfirstlaunch",true);
    }


    public void setFirstLaunch(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("isfirstlaunch", false);
        editor.commit();
    }


    /**
     * 设置某些仅仅使用一次的flag
     * @return
     */
    public boolean isFirst(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getBoolean("isfirst",true);
    }


    public void setFirst(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = preferences.edit();
        editor.putBoolean("isfirst", false);
        editor.commit();
    }


    /**
     * 序列化写入文件
     * @param filename
     * @param data
     * @param <T>
     */
    public  <T> void writeToFile(String filename,T... data) {
        // TODO Auto-generated method stub
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(context.getFilesDir().toString()+"/" + filename));

            for(T list:data){
                out.writeObject(list);
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 从文件中读取
     * @param filename
     * @param <T>
     * @return
     */
    public <T> Object getFromFile(String filename){


        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(context.getFilesDir().toString()+"/" + filename));
            Object data = inputStream.readObject();
            return data;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    

}
