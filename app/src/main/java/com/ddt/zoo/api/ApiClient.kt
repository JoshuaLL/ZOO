package com.ddt.zoo.api

import com.ddt.zoo.ZOO_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ddt.zoo.repository.ApiRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModules = module {
    single { provideGson() }
    single { provideAuthInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideApiService(get()) }
    single { provideApiRepository(get()) }
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideAuthInterceptor(): AuthInterceptor {
    return AuthInterceptor()
}

fun provideHttpLoggingInterceptor() =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }


fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLoggingInterceptor)
    return builder.build()
}

fun provideApiService(client: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .baseUrl(ZOO_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
        .create(ApiService::class.java)
}

fun provideApiRepository(service: ApiService): ApiRepository {
    return ApiRepository(service)
}