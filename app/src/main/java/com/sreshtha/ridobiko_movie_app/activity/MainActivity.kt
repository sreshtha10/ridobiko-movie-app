package com.sreshtha.ridobiko_movie_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.sreshtha.ridobiko_movie_app.databinding.ActivityMainBinding
import com.sreshtha.ridobiko_movie_app.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val _viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObservers()
        initListeners()
    }


    private fun initListeners(){

    }


    private fun setUpObservers(){
        _viewModel.movieData.observe(this) {
            when (it) {
                is Resource.Error -> {

                }
                is Resource.Success -> {

                }

            }
        }

        _viewModel.seriesData.observe(this) {
            when (it) {
                is Resource.Error -> {

                }
                is Resource.Success -> {

                }

            }
        }

        _viewModel.showData.observe(this) {
            when (it) {
                is Resource.Error -> {

                }
                is Resource.Success -> {

                }

            }
        }





    }
}