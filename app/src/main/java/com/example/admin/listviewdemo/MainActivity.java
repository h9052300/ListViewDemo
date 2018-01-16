package com.example.admin.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case SCROLL_STATE_FLING:
                Log.i("TAG", "使用者在手指離開螢幕之前滑了一下，View仍然依靠慣行滑動");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pic", R.drawable.ic_launcher_background);
                map.put("text", "新增的項目");
                dataList.add(map);
                mSimpleAdapter.notifyDataSetChanged(); //重新整理UI介面
                break;
            case SCROLL_STATE_IDLE:
                Log.i("TAG", "View已經停止滑動");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("TAG", "使用者手指沒有離開螢幕，View正在滑動");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String text = mListView.getItemAtPosition(position) + "";
        Toast.makeText(MainActivity.this, "position = " + position + "text = " + text, Toast.LENGTH_LONG).show();
    }

    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        String[] arr_data = {"肥老闆1", "肥老闆2", "肥老闆3", "肥老闆4"};

        dataList = new ArrayList<Map<String, Object>>();
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr_data);
        mSimpleAdapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.text});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.drawable.ic_launcher_foreground);
            map.put("text", "肥老闆" + i);
            dataList.add(map);
        }
        return dataList;
    }
}
