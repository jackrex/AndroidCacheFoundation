package info.jackrex.androidcachefoundation.cachefoundation.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class HttpService {
    public static String TAG = HttpService.class.getName();
    public static String DEFAULT_HTTP_TAG = "DEFAULT HTTP REQUEST";
    private RequestQueue httpQueue;
    private static HttpService httpService;

    public  Context context;

    private HttpService(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.httpQueue = Volley.newRequestQueue(context);
    }


    public static HttpService newInstance (Context context){



        if(httpService == null){
            httpService = new HttpService(context);
        }
        return httpService;
    }


    public  Context getContext() {

        return context;

    }

    /**
     * 往全局队列里加入一个新的http请求
     *
     * @param request
     */
    public <T> void addToRequestQueue(Request<T> request) {
        this.addToRequestQueue(request, DEFAULT_HTTP_TAG);
    }

    /**
     * 往全局队列里加入一个新的http请求
     *
     * @param request
     * @param tag     请求tag, 可以通过tag取消请求
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? DEFAULT_HTTP_TAG : tag);
        VolleyLog.d("Adding request to queue: %s", request.getUrl());
        if (this.httpQueue != null) {
            this.httpQueue.add(request);

        } else {
            Log.d(TAG, "http queue null");
        }


    }

    /**
     * 取消指定tag的请求
     *
     * @param tag
     */
    public void cancelRequests(Object tag) {
        if (this.httpQueue != null) {
            this.httpQueue.cancelAll(tag);
        }
    }

}
