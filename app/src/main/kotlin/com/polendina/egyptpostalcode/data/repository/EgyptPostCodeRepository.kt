package com.polendina.egyptpostalcode.data.repository

import com.polendina.egyptpostalcode.domain.model.OfficeList
import com.polendina.egyptpostalcode.domain.model.Office
import com.polendina.egyptpostalcode.domain.model.PostOfficeAdditional
import com.polendina.egyptpostalcode.domain.model.ShipmentResponse
import okhttp3.Headers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class EgyptPostCodeRepository {

    fun searchOffices(
        city: String,
        responseBody: (OfficeList?) -> Unit,
        failureThrowable: (Throwable) -> Unit
    ) {
        retrofit.searchPostOffice(city = city).enqueue(object : retrofit2.Callback<OfficeList> {
            override fun onResponse(
                call: Call<OfficeList>,
                response: Response<OfficeList>
            ) {
//                if (response.isSuccessful) {
                responseBody(response.body())
//                }
            }
            override fun onFailure(call: Call<OfficeList>, t: Throwable) {
                failureThrowable(t)
            }
        })
    }

    /**
     * Obtain additional information about each Post office.
     *
     * @param id The ID of each postal office. Usually obtained from the post office list returned from searching.
     */
    suspend fun getOffice(
        id: Int
    ): PostOfficeAdditional? {
        return retrofit.getOffice(id = id).awaitResponse().body()
    }

    fun trackShipment(
        trackNumber: Int,
        responseBody: (ShipmentResponse?) -> Unit,
        failureThrowable: (Throwable) -> Unit
    ) {
        retrofit.trackShipment(trackNumber = trackNumber).enqueue(object: retrofit2.Callback<ShipmentResponse> {
            override fun onResponse(
                call: Call<ShipmentResponse>,
                response: Response<ShipmentResponse>
            ) {
                responseBody(response.body())
            }
            override fun onFailure(call: Call<ShipmentResponse>, t: Throwable) {
                failureThrowable(t)
            }
        })
    }

}


interface RemoteAPI{
    @GET(value = "autocomplete")
    fun searchPostOffice(@Query("query") city: String): Call<OfficeList>
    @GET(value = "get_office")
    fun getOffice(@Query("id") id: Int): Call<PostOfficeAdditional>
    @GET(value = "track")
    fun trackShipment(@Query("track_no") trackNumber: Int): Call<ShipmentResponse>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://egpostal.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().addInterceptor {
        it.proceed(it.request().newBuilder()
            .headers(Headers.of(generateHeaders()))
            .build()
        )
    }.build())
    .build()
    .create(RemoteAPI::class.java)