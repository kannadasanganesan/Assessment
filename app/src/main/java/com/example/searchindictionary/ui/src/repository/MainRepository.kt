package com.example.searchindictionary.ui.src.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.searchindictionary.ui.src.data.models.Searchresultmodel
import com.example.searchindictionary.ui.src.network.UserService
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainRepository {

    private val apiService = UserService.apiService

    val searchSuccessLiveData = MutableLiveData<MutableList<Searchresultmodel>>()
    val searchFailureLiveData = MutableLiveData<Boolean>()

    suspend fun getSearchResult(query: String) {

        try {

            val response = apiService.getSearchResult(query)

            Log.d(TAG, "${response}")

            if (response.isSuccessful) {
                Log.d(TAG, "SUCCESS")
                Log.d(TAG, response.body().toString())
                searchSuccessLiveData.postValue(response.body())

            } else {
                Log.d(TAG, "FAILURE")
                Log.d(TAG, "${response.body()}")
                searchFailureLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            Log.e(TAG, e.message!!)
            //this exception occurs when there is no internet connection or host is not available
            //so inform user that something went wrong
            searchFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            Log.e(TAG, e.message!!)
            //this exception occurs when time out will happen
            //so inform user that something went wrong
            searchFailureLiveData.postValue(true)
        } catch (e: Exception) {
            Log.e(TAG, e.message!!)
            //this is generic exception handling
            //so inform user that something went wrong
            searchFailureLiveData.postValue(true)
        }

    }

    companion object {
        val TAG = MainRepository::class.java.simpleName
    }
}