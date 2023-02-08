package com.example.missingcomposable

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Link : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader = ImageLoader(this)
}
