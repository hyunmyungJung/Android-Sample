package com.hmjung.room;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.hmjung.room.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        MainViewModel viewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        binding.setViewModel(viewModel);
    }
}
