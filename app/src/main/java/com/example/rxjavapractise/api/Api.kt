package com.example.rxjavapractise.api


import com.example.rxjavapractise.datamodel.albumResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api {
    @GET("/albums")
    fun getalbum(): Observable<List<albumResponse>>
}