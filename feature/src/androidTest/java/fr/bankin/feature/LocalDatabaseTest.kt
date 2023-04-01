package fr.bankin.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.bankin.feature.data.local.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class LocalDatabaseTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var categoryDao: ParentCategoryDao
    private lateinit var subCategoryDao: SubCategoryDao
    private lateinit var subCategoryAndParentCategoryDao: SubCategoryAndParentCategoryDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        categoryDao = db.categoryDao()
        subCategoryDao = db.subCategoryDao()
        subCategoryAndParentCategoryDao = db.subCategoryAndParentCategoryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeCategoryAndReadInList() {
        val category: ParentCategory = ParentCategory("test", "test", "testName", 1)
        categoryDao.insert(category)
        val categories = categoryDao.getAll()
        MatcherAssert.assertThat(categories.first(), CoreMatchers.equalTo(category))
    }


    @Test
    @Throws(Exception::class)
    fun write3CategoriesAndCount() {
        for (i in 1..3) {
            val category = ParentCategory("test", "test", "testName",i)
            categoryDao.insert(category)
        }

        val categories = categoryDao.getAll()
        MatcherAssert.assertThat(categories.count(), CoreMatchers.equalTo(3))
    }


    @Test
    fun insertSubCategoryRetrieve() = runBlocking {
        categoryDao.insert(ParentCategory("","","Santé",163))
        subCategoryDao.insert(SubCategory("Médecine",163))
        val subCategories = subCategoryAndParentCategoryDao.getSubcategoriesForCategory(163)
        assert(subCategories.isNotEmpty())
        assert(subCategories.first().name=="Médecine")
    }

    @Test
    fun insertSubCategoryRetrieveFails() = runBlocking {
        categoryDao.insert(ParentCategory("","","Santé",163))
        subCategoryDao.insert(SubCategory("Médecine",163))
        val subCategories = subCategoryAndParentCategoryDao.getSubcategoriesForCategory(164)
        assert(subCategories.isEmpty())
    }
}