package com.hmjung.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                .build();
    }

    public LiveData<List<Todo>> getAll(){
        return  db.todoDao().getAll();
    }

    public void insert(Todo todo){
        new InsertAsyncTask(db
                .todoDao())
                .execute(todo);
    }

    // db 사용은 백그라운드 스레드에서 이뤄져야 한다.
    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
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
