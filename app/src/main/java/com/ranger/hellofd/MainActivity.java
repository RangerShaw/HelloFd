package com.ranger.hellofd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private List<String> listText;

    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        Log.d("MainActivity", "hello");
    }

    void initView() {
        listView = findViewById(R.id.lv_text_view);
        listText = new ArrayList<>();
    }

    void initData() {
        for (int i = 0; i < 20; i++) {
            listText.add("事件" + i);
        }
        adapter = new ListViewAdapter(listText, this);
        listView.setAdapter(adapter);
    }
}