package com.example.heys.ui.main.content.study.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.heys.model.network.ChannelList
import com.example.heys.model.network.NetworkResult
import com.example.heys.model.network.response.ChannelListResponse
import com.example.heys.repository.StudyRepository
import com.example.heys.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class StudyListViewModel @Inject constructor(
   private val studyRepository: StudyRepository
) : BaseViewModel() {
   val isChecked = MutableStateFlow(false)

   private val _channelList = MutableLiveData<List<ChannelList>>()
   val channelList: LiveData<List<ChannelList>> = _channelList

   private val _response: MutableLiveData<NetworkResult<ChannelListResponse>> = MutableLiveData()
   val response: LiveData<NetworkResult<ChannelListResponse>> = _response

   fun setStudyList(list: List<ChannelList>?) {
      _channelList.value = list ?: listOf()
   }

   fun getStudyList(
      token: String,
      interest: ArrayList<String>?,
      lastRecruitDate: String?,
      purposes: ArrayList<String>?,
      online: String?,
      location: String?,
      includeClosed: Boolean? = true,
      page: Int? = 1,
      limit: Int? = 30) = studyRepository.getStudyList(token, interest, lastRecruitDate, purposes, online, location, includeClosed, page, limit).asLiveData()
}