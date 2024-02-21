package com.example.application1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.example.application1.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            System.out.println(msg.obj.toString());
            binding.txtOutput.setText(msg.obj.toString());
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //plain say hello
        binding.allRecepies.setOnClickListener(v->{

            MealRepository repo = new MealRepository();
            ExecutorService srv = ((MealApplication)getApplication()).srv;
            repo.getListOfRecipes(srv);



        });
    }


}