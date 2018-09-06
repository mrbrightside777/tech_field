package com.example.isspasses.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    fun get_ctx():Context = context
}