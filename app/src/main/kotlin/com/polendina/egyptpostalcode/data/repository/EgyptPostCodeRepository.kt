package com.polendina.egyptpostalcode.data.repository

import com.polendina.egyptpostalcode.domain.model.OfficeList
import com.polendina.egyptpostalcode.domain.model.OfficeResponse
import com.polendina.egyptpostalcode.domain.model.ShipmentResponse
import okhttp3.Headers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class EgyptPostCodeRepository {

    fun getOffices(
        city: String,
        responseBody: (OfficeList?) -> Unit,
        failureThrowable: (Throwable) -> Unit
    ) {
        println("Getting offices")
        retrofit.searchPostOffice(city = city).enqueue(object : retrofit2.Callback<OfficeList> {
            override fun onResponse(
                call: Call<OfficeList>,
                response: Response<OfficeList>
            ) {
//                if (response.isSuccessful) {
                println("Response")
                println(response.isSuccessful)
                println(response.body())
                responseBody(response.body())
//                }
            }
            override fun onFailure(call: Call<OfficeList>, t: Throwable) {
                println("Failure")
                failureThrowable(t)
            }
        })
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
    @GET(value = "api/autocomplete")
    fun searchPostOffice(@Query("query") city: String): Call<OfficeList>
    @GET(value = "api/autocomplete")
    fun getOffices(@Query("query") city: String): Call<OfficeResponse>
    @GET(value = "api/track")
    fun trackShipment(@Query("track_no") trackNumber: Int): Call<ShipmentResponse>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://egpostal.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().addInterceptor {
        it.proceed(it.request().newBuilder()
            .headers(Headers.of(generateHeaders()))
            .build()
        )
    }.build())
    .build()
    .create(RemoteAPI::class.java)