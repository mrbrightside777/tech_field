package com.example.mvvmgithub.Di.Components;


import com.example.mvvmgithub.Data.Database.Databases.MvvmDB;
import com.example.mvvmgithub.Data.Repository.Repository;
import com.example.mvvmgithub.Di.Modules.DbModule;
import com.example.mvvmgithub.Di.Scopes.DbScope;

import dagger.Component;

@DbScope
@Component(modules = {DbModule.class})
public interface DbComponent {
    MvvmDB getDb();
//    void injectInto(Repository repository);
}
