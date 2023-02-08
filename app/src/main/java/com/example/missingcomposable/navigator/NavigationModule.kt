package com.example.missingcomposable.navigator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {
    @Binds
    fun bindLinkNavigator(bind: LinkNavigatorImpl): LinkNavigator
}
