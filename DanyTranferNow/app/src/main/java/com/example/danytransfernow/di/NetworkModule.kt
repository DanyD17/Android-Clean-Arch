package com.example.danytransfernow.di




import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.danytransfernow.BuildConfig
import com.example.danytransfernow.MainApplication
import com.example.danytransfernow.core.api.RetrofitService
import com.example.danytransfernow.feature.login.data.repository.AuthRepositoryImp
import com.example.danytransfernow.feature.login.data.repository.TransactionsRepositoryImp
import com.example.danytransfernow.feature.login.data.source.remote.AuthDataSource
import com.example.danytransfernow.feature.login.data.source.remote.AuthDataSourceImp
import com.example.danytransfernow.feature.login.data.source.remote.TransactionDataSource
import com.example.danytransfernow.feature.login.data.source.remote.TransactionSourceImp
import com.example.danytransfernow.feature.login.domain.repository.AuthRepository
import com.example.danytransfernow.feature.login.domain.repository.TransactionsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request: Request = chain.request()
                var token = ""
                if (MainApplication.user != null && !MainApplication.user!!.token.isNullOrEmpty()) {
                    token = MainApplication.user!!.token.toString()
                }
                val builder: Request.Builder = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Connection", "close")
                    .addHeader("Authorization", token)

                request = builder.build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authDataSource: AuthDataSource
    ):AuthRepository {
        return AuthRepositoryImp(authDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthDataSource(
        retrofitService: RetrofitService
    ): AuthDataSource {
        return AuthDataSourceImp(retrofitService)
    }

    @Singleton
    @Provides
    fun provideTransactionRepository(
        transactionDataSource: TransactionDataSource
    ): TransactionsRepository {
        return TransactionsRepositoryImp(transactionDataSource)
    }

    @Singleton
    @Provides
    fun provideTransactionDataSource(
        retrofitService: RetrofitService
    ): TransactionDataSource {
        return TransactionSourceImp(retrofitService)
    }
}