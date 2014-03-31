package info.jackrex.androidcachefoundation.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import java.util.List;

import info.jackrex.androidcachefoundation.app.R;
import info.jackrex.androidcachefoundation.app.entity.News;
import info.jackrex.androidcachefoundation.cachefoundation.utils.Date.DateUtil;


/**
 * Created by Jackrex on 3/13/14.
 */
public class CarNewsAdapter extends BaseAdapter {

    private Context context = null;
    private LayoutInflater inflater = null;
    private ImageView imageView;
    private List<News> newsList;


    public CarNewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.item_news,null);
            holder = new ViewHolder();
            holder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
            holder.tv_comment = (TextView) view.findViewById(R.id.tv_comment);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);

            view.setTag(holder);

        }else{

            holder = (ViewHolder) view.getTag();
        }

        imageView = holder.iv_pic;

        News news = newsList.get(i);

        holder.tv_content.setText(news.getTitle());
        holder.tv_time.setText(DateUtil.friendly_time(news.getPublish_time()));
        holder.tv_comment.setText(news.getRead_time()+"评论");


        ImageLoader.getInstance().displayImage(news.getImage(),holder.iv_pic ,new ImageLoadingListener(){
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

                imageView.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                if(bitmap!=null){
                ((ImageView)view).setImageBitmap(bitmap);
                }else{
                    imageView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

                imageView.setVisibility(View.GONE);

            }
        });


        return view;
    }



    class ViewHolder {

        ImageView iv_pic;
        TextView tv_content, tv_time,tv_comment;




    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
}
