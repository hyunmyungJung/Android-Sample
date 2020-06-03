package com.hmjung.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mInsert_et;
    private TextView mResult_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInsert_et = findViewById(R.id.insult_et);
        mResult_tv = findViewById(R.id.result_tv);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db") // todo-db라는 데이터베이스 파일이 실제 작성됨
                .allowMainThreadQueries()   // db는 백그라운드 스레드에서 동작하도록 작성해야함, 예제는 실습을 위해 메인 스레드에서 사용할 수 있도록 함
                .build();

        mResult_tv.setText(db.todoDao().getAll().toString());

        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().insert(new Todo(mInsert_et.getText().toString()));
                mResult_tv.setText(db.todoDao().getAll().toString());
            }
        });
    }

}
