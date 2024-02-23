package com.example.secretdrivers.di

import com.example.secretdrivers.data.db.sddriver.DriverDao
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModuleUnitTest {

    @Mock
    lateinit var driverDao: DriverDao

    private lateinit var appModule: AppModule

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        appModule = AppModule()
    }

    @Test
    fun testProvideSdRepository() {
        val sdRepository = appModule.provideSdRepository(driverDao)
        assertNotNull(sdRepository)
    }

    @Test
    fun testProvideGson() {
        val gson = appModule.providesGson()
        assertNotNull(gson)
    }

    @Test
    fun testProvideOkHttpClient() {
        val okHttpClient = appModule.provideOkHttpClient()
        assertNotNull(okHttpClient)
    }

    @Test
    fun testProvideRetrofit() {
        val retrofit = appModule.provideRetrofit(appModule.provideOkHttpClient())
        assertNotNull(retrofit)
    }

    @Test
    fun testProvideApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = appModule.provideApiService(retrofit)
        assertNotNull(apiService)
    }
}
