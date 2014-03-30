package info.jackrex.androidcachefoundation.app;

import android.view.View;

/**
 * Created by Jackrex on 3/30/14.
 */
public class TabInfo {

    private final String id;
    private final String fragmentClazz;
    private final View indicatorView;

    public TabInfo(String id, String fragmentClazz, View indicatorView) {
        this.id = id;
        this.fragmentClazz = fragmentClazz;
        this.indicatorView = indicatorView;
    }

    public String getId() {
        return id;
    }

    public String getFragmentClazz() {
        return fragmentClazz;
    }

    public View getIndicatorView() {
        return indicatorView;
    }
}
