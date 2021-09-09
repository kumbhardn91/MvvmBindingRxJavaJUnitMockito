package com.example.exercisetwo.repository

import com.example.exercisetwo.model.CountryModel
import com.example.exercisetwo.retroconnection.RetroInstance
import com.example.exercisetwo.retroconnection.RetroService
import io.reactivex.Observable

class DataRepository {

    fun getCountryInfo(): Observable<CountryModel> {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        return retroInstance.getCountryInfoApi()
    }

}