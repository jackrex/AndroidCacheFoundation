package info.jackrex.androidcachefoundation.app.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import info.jackrex.androidcachefoundation.app.R;


/**
 * Created by Jackrex on 4/1/14.
 */
public class SettingsPopWindow extends PopupWindow {


    private Activity activity;
    private View.OnClickListener listener;
    private Context context;

    public SettingsPopWindow(Context context, Activity activity, View.OnClickListener listener) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.listener = listener;
        initView();
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.settingdialog, null);

        int h = activity.getWindowManager().getDefaultDisplay().getHeight();
        int w = activity.getWindowManager().getDefaultDisplay().getWidth();
       Button btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


            }
        });
       LinearLayout my_photo = (LinearLayout) mMenuView.findViewById(R.id.my_photo);
        my_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("SelectPicPopupWindow", "我的相册");
                dismiss();
            }
        });
       LinearLayout my_bank = (LinearLayout) mMenuView.findViewById(R.id.my_bank);
        //设置按钮监听
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w/2+50);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);


    }


}
