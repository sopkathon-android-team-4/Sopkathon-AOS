import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.sopkathon_aos.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitFactory {
    private const val BASE_URL = BuildConfig.LUCKY_MESSAGE_URL
    private const val CONTENT_TYPE = "application/json"
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    private fun getLogOkHttpClient(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}