package fi.tuni.shoppingcart

import retrofit2.Call
import retrofit2.http.GET

interface CartAPI {
    @GET("shoppingcart")
    fun getList(): Call<List<items>>
}