package com.ranger.hellofd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddItemActivity extends AppCompatActivity {

    private Button confirmBtn;

    private EditText editText;

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editText = findViewById(R.id.et_new_item);
        confirmBtn = findViewById(R.id.bt_confirm);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text = editText.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        confirmBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("content", text);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}