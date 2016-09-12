package com.thinkfreely.changemode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import thinkfreely.changemodelibrary.ChangeModeController;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //第一步 当前要立即变色的页面
        ChangeModeController.getInstance().init(this,R.attr.class).setTheme(this, R.style.DayTheme, R.style.NightTheme);
        //其他页面
        //ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyBaseAdapter());
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)this.findViewById(R.id.recyclerView);
        //设置固定大小
        recyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        //创建适配器，并且设置
        mAdapter = new TestRecyclerAdapter(this);
        recyclerView.setAdapter(mAdapter);

        //添加额外属性
//        ChangeModeController.getInstance().addBackgroundColor(toolbar, R.attr.colorPrimary);
        // ChangeModeController.getInstance().addBackgroundDrawable(view,R.attr.colorAccent);
        //ChangeModeController.getInstance().addTextColor(view,R.attr.colorAccent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TypedValue attrTypedValue;
        switch (item.getItemId()) {
            case R.id.item_menu_day:
                //第二步 设置切换
                ChangeModeController.changeDay(this, R.style.DayTheme);
                 attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zztextColor);
                toolbar.setTitleTextColor(getResources().getColor(attrTypedValue.resourceId));
                break;
            case R.id.item_menu_night:
                ChangeModeController.changeNight(this, R.style.NightTheme);
                 attrTypedValue = ChangeModeController.getAttrTypedValue(this, R.attr.zztextColor);
                toolbar.setTitleTextColor(getResources().getColor(attrTypedValue.resourceId));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(MainActivity.this, R.layout.item_lv, null);
            }
            return convertView;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //第三步   在onDestroy调用
        ChangeModeController.onDestory();
    }
}
