package info.jackrex.androidcachefoundation.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.cache.disc.impl.TotalSizeLimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import info.jackrex.androidcachefoundation.cachefoundation.cache.DataCache;
import info.jackrex.androidcachefoundation.cachefoundation.cache.SharePreferenceStorageService;
import info.jackrex.androidcachefoundation.cachefoundation.http.HttpService;
import info.jackrex.androidcachefoundation.cachefoundation.http.VolleyHttpClient;

/**
 * Created by Jackrex on 3/30/14.
 */
public class MyApplication extends Application {

    public static HttpService httpService;
    public static SharePreferenceStorageService preferenceStorageService;
    public static VolleyHttpClient volleyHttpClient;

    public static DiscCacheAware cache;
    public static DisplayImageOptions options;

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        httpService = HttpService.newInstance(getApplicationContext());
        preferenceStorageService = SharePreferenceStorageService.newInstance(getApplicationContext());
        DataCache.newInstance(getApplicationContext());
        volleyHttpClient = VolleyHttpClient.newInstance(httpService);
        context = this.getApplicationContext();
        setupUIL();

    }


    public static Context getContext() {
        return context;
    }

    /**
     * Universal Image Loader Setup
     */
    private void setupUIL() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "CafeCar/cache");
        options = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true).build();
        cache = new TotalSizeLimitedDiscCache(cacheDir, 50 * 1024 * 1024);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                        // You can pass your own memory cache implementation
                .discCache(cache)
                        // You can pass your own disc cache implementation
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(config);
    }



}
