package info.jackrex.androidcachefoundation.framework;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Jackrex on 2/25/14.
 * 在使用Fragment 时候一定要记住 Fragment 的生命周期是委托在Activity 上
 * 使用getActivity() 要判断null
 *
 */
public abstract  class BaseFragment extends android.support.v4.app.Fragment {

    public static final String TAG = BaseFragment.class.getName();
    public ProgressDialog progressDialog;

    protected LayoutInflater inflater;
    protected View currentView;
    protected ViewGroup container;

    private static final String BUNDLE = "bundle";

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         this.inflater = inflater;
        this.container = container;
         currentView = createView();
        return currentView;
    }

    public abstract void init();
    public abstract View createView();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 显示一条toast
     * @param message toast内容
     */
    public void showToast(String message) {

        if(getActivity() != null){
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 居中显示toast
     * @param message toast内容
     */
    public void showCenterToast(String message) {


        if(getActivity() != null){
            Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
    }


    public void dismissProgress(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    //abstract
    /**
     * fragment被选中时调用的方法
     */
    public void onSelected(){
        Log.d(TAG, "selected");
    }

    //abstract
    /**
     * fragment由选中变成未选中调用的方法
     */
    public void onUnSelected(){
        Log.d(TAG, "unselected");
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onDestroy()
     */
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onPause()
     */
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();



    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onResume()
     */
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onStart()
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onStop()
     */
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }


    protected void openActivity( Class clazz){

        if (getActivity()==null)
            return;

        Intent intent = new Intent();
        intent.setClass(getActivity(),clazz);
        getActivity().startActivity(intent);

    }


    protected void openActivity( Class clazz,Bundle bundle){

        if (getActivity()==null)
            return;

        Intent intent = new Intent();
        intent.setClass(getActivity(),clazz);
        intent.putExtra(BUNDLE,bundle);
        getActivity().startActivity(intent);

    }

    protected void sendErrorMessage(final Handler handler,int what){
        Message msg = new Message();
        msg.what = what;
        handler.sendMessage(msg);
    }

}
