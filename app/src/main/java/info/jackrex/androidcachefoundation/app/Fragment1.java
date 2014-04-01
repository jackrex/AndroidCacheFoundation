package info.jackrex.androidcachefoundation.app;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import info.jackrex.androidcachefoundation.app.popwindow.AddPopWindow;
import info.jackrex.androidcachefoundation.app.popwindow.SettingsPopWindow;
import info.jackrex.androidcachefoundation.app.view.HeaderView;
import info.jackrex.xlistview.XListView;
import info.jackrex.androidcachefoundation.app.adapter.CarNewsAdapter;
import info.jackrex.androidcachefoundation.app.entity.News;
import info.jackrex.androidcachefoundation.app.entity.NewsContent;
import info.jackrex.androidcachefoundation.app.entity.NewsEntity;
import info.jackrex.androidcachefoundation.application.MyApplication;
import info.jackrex.androidcachefoundation.framework.BaseFragment;
import roboguice.inject.InjectView;

/**
 * Created by Jackrex on 3/30/14.
 */
public class Fragment1 extends BaseFragment implements XListView.IXListViewListener,View.OnClickListener {

    List<News> newses = new ArrayList<News>();
    List<News> newsesCopy = new ArrayList<News>();
    private XListView xListView;
    private ProgressDialog dialog;
    private CarNewsAdapter adapter;
    private String url = "http://115.28.130.246/" + "news/";
    private boolean hasNext;
    private boolean isLoadMore;
    private int nextPage;

    @InjectView (R.id.headerviw)
    private HeaderView headerView;



    private SettingsPopWindow settingsPopWindow;
    private AddPopWindow addPopWindow;
    //here use roboguice



    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 0:

                    NewsEntity entity = (NewsEntity) msg.obj;

                    if (!entity.isError()) {



                        if(!isLoadMore&&newses.size()>0){

                            newses.clear();
                            newsesCopy.clear();
                        }


                        newses = entity.getContent().getList();
                        newsesCopy.addAll(newses);
                        if (hasNext && isLoadMore){

                            initListView(newsesCopy);
                        }else{


                            initListView(newses);
                        }
                    }

                    NewsContent content = entity.getContent();
                    if (content != null) {
                        if (content.isHas_next()) {
                            hasNext = true;
                            nextPage = content.getNext_page();
                        } else {
                            hasNext = false;
                        }


                    }

                    if(dialog.isShowing()){

                        dialog.dismiss();
                    }

                    stopStatus();

                    break;

            }


        }
    };

    private void stopStatus() {

        xListView.stopLoadMore();
        xListView.stopRefresh();

    }


    @Override
    public void init() {

        this.setRetainInstance(true);
        xListView = (XListView) currentView.findViewById(R.id.mylistview);
       //use roboguice
      //  headerView = (HeaderView) currentView.findViewById(R.id.headerviw);
        dialog = new ProgressDialog(getActivity());
        loadData(handler, 1);

        dialog.show();

        xListView.setXListViewListener(this);

        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {







            }
        });


        headerView.middleRe.setOnClickListener(this);
        headerView.set.setOnClickListener(this);
        headerView.add.setOnClickListener(this);

        settingsPopWindow = new SettingsPopWindow(getActivity(),getActivity(),new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        addPopWindow = new AddPopWindow(getActivity(),new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }




    private void initListView(List<News> newsList) {


        if (adapter == null) {

            adapter = new CarNewsAdapter(MyApplication.getContext(), newsList);
            xListView.setAdapter(adapter);
        } else {

            adapter.setNewsList(newsList);
        }


    }


    private void loadData(final Handler handler, int page) {

        MyApplication.volleyHttpClient.get("/news/?page=" + page, NewsEntity.class, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        Message message = new Message();
                        message.obj = response;
                        message.what = 0;
                        handler.sendMessage(message);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );


    }

    @Override
    public View createView() {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onRefresh() {
        loadData(handler, 1);
        isLoadMore = false;

    }

    @Override
    public void onLoadMore() {

        isLoadMore = true;
        if (hasNext) {

            loadData(handler, nextPage);

        } else {
            showCenterToast("没有更多了....");
        }

    }



    @Override
    public void onClick(View v) {

        if(v == headerView.add){
            
            addPopWindow.showAsDropDown(headerView.add);

        }else if(v == headerView.set){

            settingsPopWindow.showAsDropDown(headerView.set);


        }else if (v == headerView.middleRe){


        }

    }










}