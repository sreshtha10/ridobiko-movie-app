package com.sreshtha.ridobiko_movie_app.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sreshtha.ridobiko_movie_app.api.OMDBapi
import com.sreshtha.ridobiko_movie_app.model.Show
import com.sreshtha.ridobiko_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val api:OMDBapi) :ViewModel(){


    val movieData:MutableLiveData<Resource<Show>> = MutableLiveData()
    val seriesData:MutableLiveData<Resource<Show>> = MutableLiveData()
    val showData:MutableLiveData<Resource<Show>> = MutableLiveData()


    fun getMovie(title:String){
        viewModelScope.launch {
            val result = api.getMovie(title)
            if(result.isSuccessful){
                movieData.value = Resource.Success<Show>(result.body())
            }
        }
    }

    fun getSeries(title:String){
        viewModelScope.launch {
            val result = api.getSeries(title)
            if(result.isSuccessful){
                seriesData.value = Resource.Success<Show>(result.body())
            }
        }
    }

    fun getShow(title:String){
        viewModelScope.launch {
            val result = api.getShow(title)
            if(result.isSuccessful){
                showData.value = Resource.Success<Show>(result.body())
            }
        }
    }


}