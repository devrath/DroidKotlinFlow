package com.demo.flow.di


import com.demo.flow.network.api.PlaylistAPI
import com.demo.flow.network.repository.PlaylistRepository
import com.demo.flow.network.services.PlayListService
import com.demo.flow.utils.Constants.APP_URL
import com.demo.flow.viewmodels.ParallelNetworkCallViewModel
import com.demo.flow.viewmodels.SingleNetworkCallViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        SingleNetworkCallViewModel(get())
        ParallelNetworkCallViewModel(get())
    }
}

val repositoryModule = module {
    single {
        PlaylistRepository(get())
    }
}

val serviceModule = module {
    single {
        PlayListService(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): PlaylistAPI {
        return retrofit.create(PlaylistAPI::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APP_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}