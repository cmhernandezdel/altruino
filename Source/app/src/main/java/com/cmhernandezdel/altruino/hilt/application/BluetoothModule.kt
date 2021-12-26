package com.cmhernandezdel.altruino.hilt.application

import com.cmhernandezdel.altruino.providers.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class BluetoothModule {
    @Singleton
    @Binds
    abstract fun bindBluetoothConnectionProvider(impl: BluetoothProvider): IBluetoothConnectionProvider

    @Singleton
    @Binds
    abstract fun bindBluetoothStatusProvider(impl: BluetoothProvider): IBluetoothStatusProvider
}