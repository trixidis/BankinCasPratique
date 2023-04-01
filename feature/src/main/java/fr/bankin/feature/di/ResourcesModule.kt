package fr.bankin.feature.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.bankin.feature.R
import fr.bankin.feature.data.local.AppDatabase
import fr.bankin.feature.data.local.ParentCategoryDao
import fr.bankin.feature.data.local.SubCategoryAndParentCategoryDao
import fr.bankin.feature.data.local.SubCategoryDao
import fr.bankin.feature.data.network.ServiceApi
import fr.bankin.feature.data.repository.CategoryRepository
import fr.bankin.feature.data.repository.IRepository
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCase
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCaseImpl
import fr.bankin.feature.domain.getSubCategoriesUseCase.GetSubCategoriesUseCase
import fr.bankin.feature.domain.getSubCategoriesUseCase.GetSubCategoriesUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourcesModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "categories_db"
        ).build()
    }

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): ParentCategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    fun provideSubCategoryDao(appDatabase: AppDatabase): SubCategoryDao {
        return appDatabase.subCategoryDao()
    }

    @Provides
    fun provideSubCategoryCategoryDao(appDatabase: AppDatabase): SubCategoryAndParentCategoryDao {
        return appDatabase.subCategoryAndParentCategoryDao()
    }

    @Singleton
    @Provides
    fun provideNetworkService(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ServiceApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(context.getString(R.string.server_url))
            .client(okHttpClient)
            .build()
        return retrofit.create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRepository(
        apiService: ServiceApi,
        dao: ParentCategoryDao,
        subCategorydao: SubCategoryDao,
        subCategoryCategoryDao: SubCategoryAndParentCategoryDao
    ): IRepository =
        CategoryRepository(apiService, dao, subCategorydao, subCategoryCategoryDao)

    @Provides
    fun providesGetCategoriesUseCase(repo:IRepository):GetCategoriesUseCase{
        return GetCategoriesUseCaseImpl(repo)
    }

    @Provides
    fun providesGetSuCategoriesUseCase(repo:IRepository):GetSubCategoriesUseCase{
        return GetSubCategoriesUseCaseImpl(repo)
    }
}
