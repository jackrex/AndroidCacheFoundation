package info.jackrex.androidcachefoundation.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private TabHost tabHost;
    private MyViewPager viewPager;
    private ArrayList<TabInfo> tabInfos;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(getApplicationContext());
        showTabs();
    }


    private void showTabs() {
        initTabInfo();
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setOnTabChangedListener(this);
        tabHost.setup();

        viewPager = (MyViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(new TabAdapter(this, viewPager, tabInfos));
        viewPager.setSwipeEnabled(false);

        for (TabInfo tabInfo : tabInfos) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabInfo.getId());
            tabSpec.setIndicator(tabInfo.getIndicatorView());
            tabSpec.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String arg0) {
                    View v = new View(MainActivity.this);
                    v.setMinimumWidth(0);
                    v.setMinimumHeight(0);
                    return v;
                }
            });
            tabHost.addTab(tabSpec);
        }

    }

    private void initTabInfo() {
        tabInfos = new ArrayList<TabInfo>();
        View indexIndicator = inflater.inflate(R.layout.tab1, null);
        View indexIndicator2 = inflater.inflate(R.layout.tab1, null);
        View indexIndicator3 = inflater.inflate(R.layout.tab1, null);
        View indexIndicator4 = inflater.inflate(R.layout.tab1, null);
        View indexIndicator5 = inflater.inflate(R.layout.tab1, null);
        tabInfos.add(new TabInfo("index1", Fragment1.class.getName(), indexIndicator));
        tabInfos.add(new TabInfo("index2", Fragment1.class.getName(), indexIndicator2));
        tabInfos.add(new TabInfo("index3", Fragment1.class.getName(), indexIndicator3));
        tabInfos.add(new TabInfo("index4", Fragment1.class.getName(), indexIndicator4));
        tabInfos.add(new TabInfo("index5", Fragment1.class.getName(), indexIndicator5));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private boolean isExit;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }

    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(!isExit){
                isExit=true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
            else{
                finish();
                // System.exit(0);
            }
        }
        return false;
    }

    @Override
    public void onTabChanged(String tabId) {

        Log.e(tabId,tabId);


    }
}
