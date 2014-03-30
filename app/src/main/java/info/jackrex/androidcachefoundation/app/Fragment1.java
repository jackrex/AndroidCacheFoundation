package info.jackrex.androidcachefoundation.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jackrex on 3/30/14.
 */
public class Fragment1 extends Fragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e("","oncreate");
        return inflater.inflate(R.layout.fragment1, container,false);
    }



}
