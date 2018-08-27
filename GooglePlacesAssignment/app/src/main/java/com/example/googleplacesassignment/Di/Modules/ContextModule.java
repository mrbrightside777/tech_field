package com.example.googleplacesassignment.Di.Modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;
    public ContextModule(Context context) {this.context = context;}

    @Provides
    Context getContext() {return context;}
}
