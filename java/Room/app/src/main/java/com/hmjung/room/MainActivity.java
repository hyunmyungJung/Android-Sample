package com.hmjung.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mInsert_et;
    private TextView mResult_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInsert_et = findViewById(R.id.insult_et);
        mResult_tv = findViewById(R.id.result_tv);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db") // todo-db라는 데이터베이스 파일이 실제 작성됨.
//                .allowMainThreadQueries()   // db는 백그라운드 스레드에서 동작하도록 작성해야함, 예제는 실습을 위해 메인 스레드에서 사용할 수 있도록 함.  // 비동기 처리로 삭제됨.
                .build();

        // UI 갱신
        db.todoDao().getAll().observe(this, todos -> {
            mResult_tv.setText(todos.toString());
        });

        // 버튼 클릭시 DB에 insert
        findViewById(R.id.save_btn).setOnClickListener(v -> {
//            db.todoDao().insert(new Todo(mInsert_et.getText().toString()));   // 비동기 처리로 삭제됨.
            new InsertAsyncTask(db
                    .todoDao())
                    .execute(new Todo(mInsert_et.getText().toString()));
        });
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void>{
        private TodoDao mTodoDao;

        // Todo를 받기 위한 생성자 생성
        public InsertAsyncTask(TodoDao mTodoDao) {
            this.mTodoDao = mTodoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }

}
