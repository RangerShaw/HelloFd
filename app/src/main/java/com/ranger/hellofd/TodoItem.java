package com.ranger.hellofd;

import java.util.Date;

public class TodoItem {
    int id;
    Date date;
    String content;
    boolean checked;

    public TodoItem(int id, Date date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.checked = false;
    }

    public TodoItem(int id, Date date, String content, boolean checked) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.checked = checked;
    }
}