package com.example.application1;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealApplication extends Application{
    ExecutorService srv = Executors.newCachedThreadPool();
}
