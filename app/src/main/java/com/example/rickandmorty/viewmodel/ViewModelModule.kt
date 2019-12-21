package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface  ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
     fun characterListViewModel(viewModel: CharacterListViewModel): ViewModel


    @Binds
     fun bindHomeViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}