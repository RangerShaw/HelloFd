package com.ranger.hellofd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<TodoItem> todoItems;
    private Context context;

    ItemDao itemDao;

    public ListViewAdapter(List<TodoItem> todoItems, ItemDao itemDao, Context context) {
        this.todoItems = todoItems;
        this.itemDao = itemDao;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return todoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //把布局转换成view对象 or 复用历史缓存对象
        View view = convertView == null ? View.inflate(context, R.layout.listview_item, null) : convertView;
        TextView eventText = view.findViewById(R.id.tv_check_text);
        CheckBox checkBox = view.findViewById(R.id.rb_check_button);
        Button clearBtn = view.findViewById(R.id.clear_btn);

        int itemId = todoItems.get(position).id;

        //显示事项文字
        eventText.setText(todoItems.get(position).content);
        checkBox.setChecked(todoItems.get(position).checked);

        //点击勾选框
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            itemDao.updateChecked(itemId, isChecked);
            if (isChecked) {
                eventText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                eventText.setTextColor(Color.GRAY);
            } else {
                eventText.setPaintFlags(eventText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                eventText.setTextColor(Color.BLACK);
            }
        });

        //点击删除按钮
        clearBtn.setOnClickListener(v -> {
            itemDao.delete(itemId);
            itemDao.loadAll(todoItems);
            notifyDataSetChanged();
        });

        return view;
    }

}
