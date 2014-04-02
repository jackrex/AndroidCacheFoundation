package info.jackrex.androidcachefoundation.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import info.jackrex.androidcachefoundation.app.R;

/**
 * Created by Jackrex on 4/1/14.
 */
public class HeaderView extends RelativeLayout {

    public RelativeLayout middleRe;
    public ImageView add;
    public ImageView set;
    public View headerView;
    public LinearLayout setadd;



    public HeaderView(Context context) {
        super(context);
        initView();
    }


    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public HeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initView() {

        headerView = LayoutInflater.from(getContext()).inflate(R.layout.homeheader, this);
        add = (ImageView) headerView.findViewById(R.id.add);
        set = (ImageView) headerView.findViewById(R.id.set);
        middleRe = (RelativeLayout) headerView.findViewById(R.id.middleRe);
        setadd = (LinearLayout)headerView.findViewById(R.id.setadd);



    }


}
