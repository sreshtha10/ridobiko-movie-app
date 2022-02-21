package com.sreshtha.ridobiko_movie_app.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sreshtha.ridobiko_movie_app.MyApp
import com.sreshtha.ridobiko_movie_app.api.OMDBapi
import com.sreshtha.ridobiko_movie_app.model.Show
import com.sreshtha.ridobiko_movie_app.utils.Resource
import dagger.hilt.android.internal.Contexts.getApplication
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
            if(result.isSuccessful && result.body()?.Response?.lowercase() == "true"){
                movieData.value = Resource.Success<Show>(result.body())
            }
            else if(result.isSuccessful){
                movieData.value = Resource.Error<Show>(message = "Not Found!")
            }
            else{
                movieData.value = Resource.Error<Show>(message = "Error")
            }
        }
    }

    fun getSeries(title:String){
        viewModelScope.launch {
            val result = api.getSeries(title)
            if(result.isSuccessful && result.body()?.Response?.lowercase() == "true"){
                seriesData.value = Resource.Success<Show>(result.body())
            }
            else if(result.isSuccessful){
                seriesData.value = Resource.Error<Show>(message = "Not Found!")
            }
            else{
                seriesData.value = Resource.Error<Show>(message = "Error")
            }
        }
    }

    fun getShow(title:String){
        viewModelScope.launch {
            val result = api.getShow(title)
            if(result.isSuccessful && result.body()?.Response?.lowercase() == "true"){
               showData.value = Resource.Success<Show>(result.body())
            }
            else if(result.isSuccessful){
                showData.value = Resource.Error<Show>(message = "Not Found!")
            }
            else{
                showData.value = Resource.Error<Show>(message = "Error")
            }
        }
    }

    fun hasInternetConnection(context: Context):Boolean{
        val connectivityManager = getApplication(context).getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork?:return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?:return false
        return when{
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> return false
        }

    }


}