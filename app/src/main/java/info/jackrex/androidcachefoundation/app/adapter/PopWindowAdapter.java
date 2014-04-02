package info.jackrex.androidcachefoundation.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.jackrex.androidcachefoundation.app.R;

/**
 * Created by Jackrex on 4/2/14.
 */
public class PopWindowAdapter extends BaseAdapter {

    List<String> itemList = new ArrayList<String>();
    final Context context;


    public PopWindowAdapter(Context context, List<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (position >= itemList.size()){
                //add
                convertView = LayoutInflater.from(context).inflate(R.layout.item_index_dialog_addcar, null);
                convertView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub






                    }
                });
            }else{
                //edit
                convertView = LayoutInflater.from(context).inflate(R.layout.item_index_dialog_editcar, null);
                holder = new ViewHolder();
                holder.ivCarImage = (ImageView)convertView.findViewById(R.id.ivCarImage);
                holder.tvCarBrand = (TextView)convertView.findViewById(R.id.tvCarBrand);
                holder.tvCarSeries=(TextView)convertView.findViewById(R.id.tvCarSeri);
                holder.editcar_divider=(RelativeLayout) convertView.findViewById(R.id.editcar_divider);

                if(position==itemList.size()-1){
                    holder.editcar_divider.setVisibility(View.INVISIBLE);
                }
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        return convertView;

}


    private final class ViewHolder {
        ImageView ivCarImage;
        TextView tvCarBrand,tvCarSeries;
        private RelativeLayout editcar_divider;

    }
}
