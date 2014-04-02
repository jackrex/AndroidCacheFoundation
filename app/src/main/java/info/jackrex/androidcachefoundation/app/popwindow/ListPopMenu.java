package info.jackrex.androidcachefoundation.app.popwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import info.jackrex.androidcachefoundation.app.R;

/**
 * Created by Jackrex on 4/2/14.
 */
public class ListPopMenu  {


    private Context context;
    private PopupWindow popupWindow;
    private ListView listView;

    public ListPopMenu(Context context, PopupWindow popupWindow) {
        this.context = context;
        this.popupWindow = popupWindow;
    }


    public ListPopMenu(Context context,BaseAdapter listAdapter,AdapterView.OnItemClickListener listener,PopupWindow.OnDismissListener dismissListener) {
        // TODO Auto-generated constructor stub
        this(context,R.layout.popdialog,listAdapter,listener,dismissListener,-1);
    }

    public ListPopMenu(Context context,int layout,BaseAdapter listAdapter,AdapterView.OnItemClickListener listener,PopupWindow.OnDismissListener dismissListener,int width) {
        // TODO Auto-generated constructor stub
        this.context = context;
        View view = LayoutInflater.from(context).inflate(layout, null);
        listView = (ListView)view.findViewById(R.id.lvlistview);
        listView.setAdapter(listAdapter);
        listView.setFocusableInTouchMode(true);
        listView.setFocusable(true);
        listView.setOnItemClickListener(listener);
        if (width == 0){
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }else if (width == -1){
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            popupWindow = new PopupWindow(view, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(dismissListener);

    }



    public void showAsDropDown(View parent) {
        popupWindow.showAsDropDown(parent);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }

    public void dismiss() {
        popupWindow.dismiss();
    }



}
