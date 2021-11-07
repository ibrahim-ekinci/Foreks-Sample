package com.gloorystudio.foreks_sample.data.local.db.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CurrencyDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CurrencyDatabase
    private lateinit var dao: CurrencyDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.currencyDao()
    }

    @After
    fun teardown() {

    }

    @Test
    fun insertCurrencyFavoriteTest() = runBlockingTest {
        val currencyFavoriteEntity = CurrencyFavoriteEntity("code-1")
        dao.insertCurrencyFavorite(currencyFavoriteEntity)

        val allCurrencyFavoriteEntity = dao.getAllCurrencyFavorite()
        assertThat(allCurrencyFavoriteEntity?.contains(currencyFavoriteEntity))
    }

    @Test
    fun deleteCurrencyFavoriteTest() = runBlockingTest {
        val currencyFavoriteEntity = CurrencyFavoriteEntity("code-1")
        dao.insertCurrencyFavorite(currencyFavoriteEntity)
        dao.deleteCurrencyFavorite(currencyFavoriteEntity.code)

        val allCurrencyFavoriteEntity = dao.getAllCurrencyFavorite()
        assertThat(allCurrencyFavoriteEntity).doesNotContain(currencyFavoriteEntity)
    }

    @Test
    fun getCurrencyFavoriteTest() = runBlockingTest {
        val currencyFavoriteEntity = CurrencyFavoriteEntity("code-1")
        dao.insertCurrencyFavorite(currencyFavoriteEntity)

        val allCurrencyFavoriteEntity = dao.getAllCurrencyFavorite()
        assertThat(allCurrencyFavoriteEntity?.size).isEqualTo(1)
        assertThat(allCurrencyFavoriteEntity?.get(0)).isEqualTo(currencyFavoriteEntity)
    }

    @Test
    fun getAllCurrencyFavoriteTest() = runBlockingTest {
        val currencyFavoriteEntity1 = CurrencyFavoriteEntity("code-1")
        val currencyFavoriteEntity2 = CurrencyFavoriteEntity("code-2")
        val currencyFavoriteEntity3 = CurrencyFavoriteEntity("code-3")
        val currencyFavoriteEntity4 = CurrencyFavoriteEntity("code-4")
        val currencyFavoriteEntity5 = CurrencyFavoriteEntity("code-5")
        dao.insertCurrencyFavorite(currencyFavoriteEntity1)
        dao.insertCurrencyFavorite(currencyFavoriteEntity2)
        dao.insertCurrencyFavorite(currencyFavoriteEntity3)
        dao.insertCurrencyFavorite(currencyFavoriteEntity4)
        dao.insertCurrencyFavorite(currencyFavoriteEntity5)

        val allCurrencyFavoriteEntity = dao.getAllCurrencyFavorite()
        assertThat(allCurrencyFavoriteEntity?.size).isEqualTo(5)
        assertThat(allCurrencyFavoriteEntity?.get(0)).isEqualTo(currencyFavoriteEntity1)
        assertThat(allCurrencyFavoriteEntity?.get(1)).isEqualTo(currencyFavoriteEntity2)
        assertThat(allCurrencyFavoriteEntity?.get(2)).isEqualTo(currencyFavoriteEntity3)
        assertThat(allCurrencyFavoriteEntity?.get(3)).isEqualTo(currencyFavoriteEntity4)
        assertThat(allCurrencyFavoriteEntity?.get(4)).isEqualTo(currencyFavoriteEntity5)
    }
}