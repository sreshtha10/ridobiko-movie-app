package com.sreshtha.ridobiko_movie_app.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.sreshtha.ridobiko_movie_app.databinding.ActivityMainBinding
import com.sreshtha.ridobiko_movie_app.model.Show
import com.sreshtha.ridobiko_movie_app.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val _viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setUpObservers()
        initListeners()
    }


    private fun initListeners(){
        binding.apply {

           tv_btn_search.setOnClickListener {
               if(searchView.query.toString().isEmpty()){
                   //todo display snackbar
                   return@setOnClickListener
               }
               val title = searchView.query.toString()
               when {
                   spType.selectedItem.toString().lowercase() == "movies" -> {
                       _viewModel.getMovie(title)
                   }
                   spType.selectedItem.toString().lowercase() == "series" -> {
                       _viewModel.getSeries(title)
                   }
                   else -> {
                       _viewModel.getShow(title)
                   }
               }
           }

        }
    }


    private fun setUpObservers(){
        _viewModel.movieData.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Log.d("SEARCH_RESULT",it.message.toString())
                    tv_label_nothing_to_show.visibility = View.VISIBLE
                    iv_poster.visibility = View.GONE
                    sl_show.visibility = View.GONE
                }
                is Resource.Success -> {
                    Log.d("SEARCH_RESULT",it.data.toString())
                    tv_label_nothing_to_show.visibility = View.GONE
                    iv_poster.visibility = View.VISIBLE
                    sl_show.visibility = View.VISIBLE
                    updateUI(it.data!!)
                }

            }
        }

        _viewModel.seriesData.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Log.d("SEARCH_RESULT",it.message.toString())
                    tv_label_nothing_to_show.visibility = View.VISIBLE
                    iv_poster.visibility = View.GONE
                    sl_show.visibility = View.GONE
                }
                is Resource.Success -> {
                    Log.d("SEARCH_RESULT",it.data.toString())
                    tv_label_nothing_to_show.visibility = View.GONE
                    iv_poster.visibility = View.VISIBLE
                    sl_show.visibility = View.VISIBLE
                    updateUI(it.data!!)
                }

            }
        }

        _viewModel.showData.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Log.d("SEARCH_RESULT",it.message.toString())
                    tv_label_nothing_to_show.visibility = View.VISIBLE
                    iv_poster.visibility = View.GONE
                    sl_show.visibility = View.GONE
                }
                is Resource.Success -> {
                    Log.d("SEARCH_RESULT",it.data.toString())
                    tv_label_nothing_to_show.visibility = View.GONE
                    iv_poster.visibility = View.VISIBLE
                    sl_show.visibility = View.VISIBLE
                    updateUI(it.data!!)
                }

            }
        }
    }


    private fun updateUI(show:Show){
        binding.apply {
            Glide.with(root)
                .load(show.Poster)
                .into(ivPoster)

            tvTitle.text = show.Title
            tvGenre.text = show.Genre
            tvDirector.text = "Directed by ${show.Director}"
            tvTime.text = show.Runtime
            tvPlot.text = show.Plot
            tvCast.text = show.Actors
            tvYear.text = show.Year
            tvImdbRating.text = show.imdbRating

            show.Ratings.forEach {
                if(it.Source.lowercase()=="rotten tomatoes"){
                    tvRtRating.text = it.Value
                }
            }
        }

    }
}