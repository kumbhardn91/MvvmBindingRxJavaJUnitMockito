package com.example.exercisetwo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercisetwo.model.CountryModel
import com.example.exercisetwo.model.DataRows
import com.example.exercisetwo.repository.DataRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var countryViewModel: CountryViewModel

    @Mock
    private var dataRepository: DataRepository = DataRepository()

    private val countryModel = listOf(
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
    private val countryModelObject = CountryModel(rows = countryModel, title = "About Canada")

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        countryViewModel = CountryViewModel()
    }

    @Test
    fun getCountryInfo_onSuccess() {

        Mockito.`when`(dataRepository.getCountryInfo())
            .thenReturn(Observable.just(countryModelObject))

        countryViewModel.getCountryData()

        val countryViewModel = countryViewModel.countryLiveData.value
        assertEquals(countryViewModel?.rows?.get(0)?.title, "Beavers")

    }

    @Test
    fun getCountryInfo_onError() {

        Mockito.`when`(dataRepository.getCountryInfo())
            .thenReturn(Observable.error(Throwable("Error")))

        countryViewModel.getCountryData()

        val countryViewModel = countryViewModel.countryLiveData.value
        assertEquals(countryViewModel?.rows?.get(0)?.title, "Beavers")
    }

    @Test
    fun getCountryData() {
    }
}