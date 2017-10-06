package test.example.dribbblesample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.example.dribbblesample.BuildConfig
import test.example.dribbblesample.R
import test.example.dribbblesample.api.DribbbleInterceptor
import test.example.dribbblesample.api.DribbbleService
import test.example.dribbblesample.di.qualifier.AccessToken
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton @AccessToken
    fun provideAccessToken(context: Context): String = context.getString(R.string.client_access_token)

    @Provides @Singleton
    fun provideRequestInterceptor(@AccessToken accessToken: String) = DribbbleInterceptor(accessToken)

    @Provides @Singleton
    fun provideOkHttpClient(interceptor: DribbbleInterceptor): OkHttpClient =
            OkHttpClient().newBuilder()
                    .addInterceptor(interceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
                    })
                    .build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder().baseUrl("https://api.dribbble.com/v1/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides @Singleton
    fun provideDribbbleService(retrofit: Retrofit): DribbbleService = retrofit.create(DribbbleService::class.java)
}
