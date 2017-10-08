package test.example.dribbblesample.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import test.example.dribbblesample.DribbbleApp
import javax.inject.Singleton

@Module
class AppModule(val app: DribbbleApp) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app
}