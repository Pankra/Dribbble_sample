package test.example.dribbblesample

import android.app.Application
import test.example.dribbblesample.di.ApplicationComponent
import test.example.dribbblesample.di.DaggerApplicationComponent

class DribbbleApp: Application() {
    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().build()
    }
}