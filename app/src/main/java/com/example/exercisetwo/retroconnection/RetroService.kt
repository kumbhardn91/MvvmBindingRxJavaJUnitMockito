package com.example.exercisetwo.retroconnection

import com.example.exercisetwo.model.CountryModel
import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {

    @GET("facts.json")
    fun getCountryInfoApi(): Observable<CountryModel>
}