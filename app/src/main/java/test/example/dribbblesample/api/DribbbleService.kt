package test.example.dribbblesample.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import test.example.dribbblesample.ShotItem

interface DribbbleService {
    @GET("shots")
    fun getShots(@Query("page") page: Int, @Query("per_page") perPage: Int? = 50) : Call<List<ShotItem>>

    @GET("shots/{id}")
    fun getShot(@Path("id") id: Int) : Call<ShotItem>
}