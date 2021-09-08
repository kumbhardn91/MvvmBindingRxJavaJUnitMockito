package com.example.exercisetwo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.exercisetwo.model.CountryModel
import com.example.exercisetwo.model.DataRows
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var countryViewModel: CountryViewModel? = null
    private var countryLiveDataTest: MutableLiveData<CountryModel> = MutableLiveData()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        countryViewModel = CountryViewModel()
    }

    @Test
    fun getCountryLiveData() {
        val countryModel = listOf(
            DataRows(
                description = "Beavers are second only to humans in their ability to manipulate and change their environment",
                imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",
                title = "Beavers"
            ),
            DataRows(
                description = "Flag information",
                imageHref = "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
                title = "Flag"
            )
        )
        val countryModelJson = CountryModel(rows = countryModel, title = "About Canada")
        countryViewModel?.countryLiveData?.value = countryModelJson
        val countryViewModel = countryViewModel?.countryLiveData?.value
        assertEquals(countryViewModel?.rows?.get(0)?.title, "Beavers")
    }

    @Test
    fun setCountryLiveData() {
        val countryModel = listOf(
            DataRows(
                description = "Beavers are second only to humans in their ability to manipulate and change their environment",
                imageHref = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",
                title = "Beavers"
            ),
            DataRows(
                description = "Flag information",
                imageHref = "http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
                title = "Flag"
            )
        )
        val countryModelJson = CountryModel(rows = countryModel, title = "About Canada")
        countryViewModel?.countryLiveData?.value = countryModelJson
        countryLiveDataTest.value = countryModelJson
    }

    @Test
    fun getCountryData() {
    }
}