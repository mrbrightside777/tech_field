package com.example.mvvmgithub.Di.Components;


import com.example.mvvmgithub.Di.Modules.RepoModule;
import com.example.mvvmgithub.UserViewModel;

import dagger.Component;

@Component(modules = RepoModule.class)
public interface UserViewModelComponent {
    void inject(UserViewModel userViewModel);
}
