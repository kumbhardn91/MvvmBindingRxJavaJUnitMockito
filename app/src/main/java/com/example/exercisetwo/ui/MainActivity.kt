package com.example.exercisetwo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.adapter.CountryModelAdapter
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.utils.CustomeProgressDialog
import com.example.exercisetwo.utils.checkForInternet
import com.example.exercisetwo.utils.showToast
import com.example.exercisetwo.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var customeProgressDialog: CustomeProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialization()
        observeCountryLiveData()
        updateCountryData()
        observeProgressDialog()

    }

    private fun initialization() {

        customeProgressDialog = CustomeProgressDialog(this)
        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)
    }

    private fun observeCountryLiveData() {

        if (checkForInternet(this)) {

            countryViewModel.countryLiveData.observe(this, {
                if (it != null) {
                    val dataList = it.rows
                    dataList.let {
                        binding.recyclerView.apply {
                            adapter = CountryModelAdapter(dataList)
                            layoutManager = manager
                        }
                    }
                } else {
                    showToast(this, getString(R.string.no_data))
                }
            })

        } else {
            showToast(this, getString(R.string.no_internet))
        }

    }

    private fun updateCountryData() {
        countryViewModel.getCountryData()
    }

    private fun observeProgressDialog() {
        countryViewModel.progressDialog?.observe(this, {
            if (it!!) customeProgressDialog.show() else customeProgressDialog.dismiss()
        })

    }

}