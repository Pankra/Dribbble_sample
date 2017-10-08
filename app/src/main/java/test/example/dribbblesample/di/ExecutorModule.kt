package test.example.dribbblesample.di

import dagger.Module
import dagger.Provides
import test.example.dribbblesample.interactor.Executor
import test.example.dribbblesample.interactor.ThreadExecutor
import javax.inject.Singleton

@Module
class ExecutorModule {

    @Provides
    @Singleton
    fun providesThreadExecutor(): Executor = ThreadExecutor()
}