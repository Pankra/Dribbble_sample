package test.example.dribbblesample.di

import test.example.dribbblesample.ShotItem

interface ShotsRepository {
    fun getShots(): List<ShotItem>
    fun getShot(id: Int): ShotItem?
}