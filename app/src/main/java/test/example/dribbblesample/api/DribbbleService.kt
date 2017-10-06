package test.example.dribbblesample.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import test.example.dribbblesample.ShotItem

interface DribbbleService {
    @GET("shots")
    fun getShots() : Call<List<ShotItem>>

    @GET("shots/")
    fun getShot(@Query("id") id: Int) : Call<ShotItem>
}