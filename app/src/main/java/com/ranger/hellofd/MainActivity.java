package com.ranger.hellofd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ItemDao itemDao;

    private ListView listView;
    private FloatingActionButton addBtn;

    private List<TodoItem> todoItems;

    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String content = data.getStringExtra("content");
            itemDao.add(content);
            itemDao.loadAll(todoItems);
            adapter.notifyDataSetChanged();
        }
    }

    void initView() {
        listView = findViewById(R.id.lv_text_view);
        todoItems = new ArrayList<>();
        addBtn = findViewById(R.id.add_btn);

        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    void initData() {
        itemDao = new ItemDao(this);
        itemDao.deleteAll();

        for (int i = 0; i < 5; i++) {
            itemDao.add("事件" + i, i % 2 == 0);
        }

        itemDao.loadAll(todoItems);

        adapter = new ListViewAdapter(todoItems, itemDao, this);
        listView.setAdapter(adapter);
    }

}

