package com.udacity.projects.bakingapp;

import android.app.Application;
import android.content.Context;

import com.udacity.projects.bakingapp.di.BakingComponent;
import com.udacity.projects.bakingapp.di.DaggerBakingComponent;
import com.udacity.projects.bakingapp.di.modules.ContextModule;
import com.udacity.projects.bakingapp.di.modules.RoomModule;

public class BakingApplication extends Application {

    private BakingComponent bakingComponent;

    public static BakingComponent getComponent(Context context) {
        return ((BakingApplication) context.getApplicationContext()).bakingComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        bakingComponent = DaggerBakingComponent.builder()
                .contextModule(new ContextModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }
}
