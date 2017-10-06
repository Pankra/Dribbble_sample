package test.example.dribbblesample.repository

import test.example.dribbblesample.ShotItem
import test.example.dribbblesample.api.DribbbleService
import test.example.dribbblesample.di.ShotsRepository

class ShotsRepositoryImpl(val dribbbleService: DribbbleService): ShotsRepository {
    override fun getShots(): List<ShotItem> = dribbbleService.getShots().execute().body()?: emptyList()
    override fun getShot(id: Int): ShotItem? = dribbbleService.getShot(id).execute().body()
}