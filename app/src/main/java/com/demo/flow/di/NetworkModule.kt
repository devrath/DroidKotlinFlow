package com.demo.flow.di

/*
import com.demo.flow.network.api.PlaylistAPI
import com.demo.flow.utils.Constants.APP_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class NetworkModule {
    @Provides
    fun playlistApi(retrofit : Retrofit) : PlaylistAPI {
        return retrofit.create(PlaylistAPI::class.java)
    }

    @Provides
    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(APP_URL) // Sometimes it fails and we need to change this
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}*/
