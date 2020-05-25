package com.mocktest.listpage

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.P
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.mocktest.listpage.injection.appModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 * The application level class
 */
class App : Application() {

    /**
     * On application created
     */
    override fun onCreate() {
        super.onCreate()
        setupCoil()
        setupKoin()
    }

    private fun setupCoil() {
        mCoilImageLoader = ImageLoader(this) {
            componentRegistry {
                if (SDK_INT >= P) {
                    add(SvgDecoder(this@App))
                } else {
                    add(GifDecoder())
                }

            }
            okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this@App))
                    .build()
            }
        }
    }

    /**
     * Setup the dependency injection in application level using koin lib
     */
    private fun setupKoin() {
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule())
        }
    }

    companion object {
        private lateinit var mCoilImageLoader: ImageLoader
        val coilImageLoader: ImageLoader get() = mCoilImageLoader
    }
}