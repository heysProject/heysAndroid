package com.example.heysrealprojcet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heysrealprojcet.model.network.FetchState
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
   protected val _fetchState = MutableLiveData<FetchState>()
   val fetchState: LiveData<FetchState> = _fetchState

   protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
      throwable.printStackTrace()

      when (throwable) {
         is SocketException -> _fetchState.postValue(FetchState.BAD_INTERNET)
         is HttpException -> _fetchState.postValue(FetchState.PARSE_ERROR)
         is UnknownHostException -> _fetchState.postValue(FetchState.WRONG_CONNECTION)
         else -> _fetchState.postValue(FetchState.FAIL)
      }
   }
}