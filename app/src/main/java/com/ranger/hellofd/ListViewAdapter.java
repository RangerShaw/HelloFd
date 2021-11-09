package com.ranger.hellofd;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<String> listText;
    private Context context;
    private Map<Integer, Boolean> map = new HashMap<>();

    public ListViewAdapter(List<String> listText, Context context) {
        this.listText = listText;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listText.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //把布局转换成view对象 or 复用历史缓存对象
        View view = convertView == null ? View.inflate(context, R.layout.listview_item, null) : convertView;

        //单选按钮的文字
        TextView eventText = view.findViewById(R.id.tv_check_text);
        eventText.setText(listText.get(position));

        //单选按钮
        final CheckBox checkBox = view.findViewById(R.id.rb_check_button);
        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                map.put(position, true);
                eventText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                eventText.setTextColor(Color.GRAY);
            } else {
                map.put(position, false);
                eventText.setPaintFlags(eventText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                eventText.setTextColor(Color.BLACK);
            }
        });
        checkBox.setChecked(map.containsKey(position) && map.get(position) != null);
        return view;
    }

}
