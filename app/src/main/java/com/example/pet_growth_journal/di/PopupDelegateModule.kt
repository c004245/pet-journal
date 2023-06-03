package com.example.pet_growth_journal.di

import com.example.pet_growth_journal.ui.add.AddPopupDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopupDelegateModule {

    @Provides
    @Singleton
    fun providesAddPopupController(): AddPopupDelegate =
        AddPopupDelegate()
}