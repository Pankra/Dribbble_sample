package test.example.dribbblesample.di

import dagger.Component
import test.example.dribbblesample.shotlist.ShotsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        DataModule::class,
        RepositoryModule::class
))
interface ApplicationComponent {
    fun inject(into: ShotsFragment)
}