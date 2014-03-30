/*
 * Copyright (c) 2014.
 * Jackrex
 */

package info.jackrex.androidcachefoundation.cachefoundation.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import info.jackrex.androidcachefoundation.cachefoundation.cache.DataCache;


//region Description
//<editor-fold desc="Description">

/**
 * Created by Jackrex on 2/18/14.
 */
public class GsonRequest<T> extends Request<T> {


    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private String url;
    private Map<String, String> params;


    /**
     * 带参数 带头(Header)的 GET POST 请求
     * @param method
     * @param url
     * @param clazz
     * @param headers
     * @param params
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method,String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.url = url;
        this.params = params;
    }

    /**
     *  不带参数的请求 GET POST
     * @param method
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method,String url, Class<T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
       this(method, url, null, null,listener ,errorListener);
    }

    /**
     * 带参数的请求 GET POST
     *
     * @param method GET POST
     * @param url
     * @param clazz
     * @param params 带参数的请求
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method,String url, Class<T> clazz, Map<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
       this(method, url, clazz, null, params, listener, errorListener);

    }




    /**
     * 不带参数默认GET 请求方法
     * @param url 传入url http://.... 格式错误可能报NULLPointer
     * @param clazz 需要转化的实体类
     * @param listener 传入成功监听listener
     * @param errorListener 失败listener
     */
   public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener){
       this(Method.GET, url, clazz, null, null, listener, errorListener);
   }



    //default for POST PUT
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params :super.getParams();
    }



    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }







    /**
     * 处理网络返回
     * @param response
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));

            DataCache.getDataCache().saveToCache(url,json);

            return Response.success(
                    gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }


}

