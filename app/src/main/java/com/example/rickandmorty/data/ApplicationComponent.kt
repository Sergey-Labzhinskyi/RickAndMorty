package com.example.rickandmorty.data

import com.example.rickandmorty.view.CharacterListFragment
import com.example.rickandmorty.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: CharacterListFragment)
}