package com.pixabay

import android.app.Application
import android.graphics.Bitmap
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.common.util.ByteConstants
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImagePipelineFactory
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
import com.pixabay.di.apiModule
import com.pixabay.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by AlanChien on 23,December,2019.
 */
private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()
private val MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4
private const val MAX_DISK_CACHE_SIZE = 40L * ByteConstants.MB

class PixabayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initFresco()
        initKoin()
        initTinber()
    }

    private fun initFresco() {
        try {
            Fresco.initialize(this, getImagePipelineConfig())
        } catch (e: Exception) {
            Timber.e(e, "init fresco error")
        }
    }

    private fun getMainDiskCacheConfig() = DiskCacheConfig.newBuilder(this)
        .setBaseDirectoryPath(cacheDir)
        .setBaseDirectoryName(packageName)
        .setMaxCacheSize(MAX_DISK_CACHE_SIZE) // max cache size
        .build()

    private fun getImagePipelineConfig() = ImagePipelineConfig.newBuilder(this)
        .setBitmapMemoryCacheParamsSupplier {
            MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE,
                Int.MAX_VALUE,
                MAX_MEMORY_CACHE_SIZE,
                Int.MAX_VALUE,
                Int.MAX_VALUE
            )
        }
        .setDownsampleEnabled(true)
        .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
        .setResizeAndRotateEnabledForNetwork(true)
        .setBitmapsConfig(Bitmap.Config.RGB_565)
        .setMainDiskCacheConfig(getMainDiskCacheConfig())
        .build()

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@PixabayApplication)
            modules(listOf(apiModule, viewModel))
        }
    }

    private fun initTinber() {
        takeIf { BuildConfig.DEBUG }?.let {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ImagePipelineFactory.getInstance().imagePipeline.clearMemoryCaches()
    }
}