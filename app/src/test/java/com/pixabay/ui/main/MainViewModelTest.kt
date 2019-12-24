package com.pixabay.ui.main

import com.pixabay.BuildConfig
import com.pixabay.base.BaseRobolectricTest
import com.pixabay.data.remote.api.response.GetImagesResponse
import io.mockk.*
import org.junit.*
import retrofit2.Response

/**
 * Created by AlanChien on 24,December,2019.
 */
const val API_RESP_FILE = "api.json"

class MainViewModelTest : BaseRobolectricTest() {

    private val vm: MainViewModel = spyk(recordPrivateCalls = true)

    override fun setUp() {
        super.setUp()
        mockResp()
    }

    private fun mockResp() {
        val mockResp = mockk<Response<GetImagesResponse>>(relaxed = true)
        coEvery { apiClient.apiService.getImages(BuildConfig.PixabayKey, any()) } returns mockResp
        every { mockResp.isSuccessful } returns true
        val resp = getRespObject(API_RESP_FILE, GetImagesResponse::class.java) as GetImagesResponse
        every { mockResp.body() } returns resp
    }

    @Test
    fun `start to load images`() {
        vm.start()
    }
}