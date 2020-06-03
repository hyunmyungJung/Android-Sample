package com.hmjung.room;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private EditText mInsert_et;
    private TextView mResult_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInsert_et = findViewById(R.id.insult_et);
        mResult_tv = findViewById(R.id.result_tv);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // UI 갱신
        viewModel.getAll().observe(this, todos -> {
            mResult_tv.setText(todos.toString());
        });

        // 버튼 클릭시 DB에 insert
        findViewById(R.id.save_btn).setOnClickListener(v -> {
            viewModel.insert(new Todo(mInsert_et.getText().toString()));
        });
    }
}
