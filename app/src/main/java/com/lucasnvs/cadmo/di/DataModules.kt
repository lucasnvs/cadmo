package com.lucasnvs.cadmo.di

import android.content.Context
import androidx.room.Room
import com.lucasnvs.cadmo.data.DefaultFavoriteRepository
import com.lucasnvs.cadmo.data.DefaultProductRepository
import com.lucasnvs.cadmo.data.FavoriteRepository
import com.lucasnvs.cadmo.data.ProductRepository
import com.lucasnvs.cadmo.data.source.local.CadmoDatabase
import com.lucasnvs.cadmo.data.source.local.ProductDAO
import com.lucasnvs.cadmo.data.source.network.KabumProductNetworkDataSource
import com.lucasnvs.cadmo.data.source.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProductRepository(repository: DefaultProductRepository): ProductRepository

    @Singleton
    @Binds
    abstract fun bindFavoriteRepository(repository: DefaultFavoriteRepository): FavoriteRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: KabumProductNetworkDataSource): NetworkDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): CadmoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CadmoDatabase::class.java,
            "Products.db"
        ).build()
    }

    @Provides
    fun provideProductDao(database: CadmoDatabase): ProductDAO = database.productDAO()
}