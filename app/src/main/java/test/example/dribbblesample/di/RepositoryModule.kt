package test.example.dribbblesample.di

import dagger.Module
import dagger.Provides
import test.example.dribbblesample.api.DribbbleService
import test.example.dribbblesample.repository.ShotsRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun providesShotsRepo(dribbbleService: DribbbleService): ShotsRepository = ShotsRepositoryImpl(dribbbleService)
}