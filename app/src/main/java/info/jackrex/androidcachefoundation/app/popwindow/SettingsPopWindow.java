package info.jackrex.androidcachefoundation.app.popwindow;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Jackrex on 4/1/14.
 */
public class SettingsPopWindow extends PopupWindow {


    private Activity activity;
    private View.OnClickListener listener;

    public SettingsPopWindow(Context context, Activity activity, View.OnClickListener listener) {
        super(context);
        this.activity = activity;
        this.listener = listener;
    }
}
