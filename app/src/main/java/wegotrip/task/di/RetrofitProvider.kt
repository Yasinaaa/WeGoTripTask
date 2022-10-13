package wegotrip.task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wegotrip.task.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Named

const val REQUEST_WEBHOOK = "REQUEST_WEBHOOK"
const val IMAGES_REQUEST = "IMAGES_REQUEST"
const val TIMEOUT_IN_SECONDS = 30

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {

    private val loggerInterceptor = HttpLoggingInterceptor().setLevel(getHttpLogLevel())

    private fun retrofitBuilder(url: String) = Retrofit.Builder()
        .baseUrl(url)
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())

    private fun getHttpLogLevel() =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

    private fun createClient() =  OkHttpClient.Builder()
        .addInterceptor(loggerInterceptor)
        .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
        .build()

    @Provides
    @Named(REQUEST_WEBHOOK)
    fun provideWebhookRetrofit(): Retrofit =
        retrofitBuilder("https://webhook.site/").build()

    @Provides
    @Named(IMAGES_REQUEST)
    fun provideImagesRetrofit(): Retrofit =
        retrofitBuilder("https://app.wegotrip.com/api/v2/").build()

}