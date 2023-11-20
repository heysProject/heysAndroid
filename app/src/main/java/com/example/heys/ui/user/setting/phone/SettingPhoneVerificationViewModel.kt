package com.example.heys.ui.user.setting.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.heys.model.network.Phone
import com.example.heys.model.network.PhoneVerification
import com.example.heys.model.network.UserEdit
import com.example.heys.repository.MyPageRepository
import com.example.heys.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingPhoneVerificationViewModel @Inject constructor(
   private val signupRepository: SignupRepository,
   private val myPageRepository: MyPageRepository
) : ViewModel() {
   val phoneNumber = MutableLiveData<String>()
   val code = MutableStateFlow("")

   private val _isEnabled = MutableLiveData<Boolean>()
   val isEnabled: LiveData<Boolean> = _isEnabled

   init {
      viewModelScope.launch {
         code.collect {
            checkDigit()
         }
      }
   }

   private fun checkDigit() {
      _isEnabled.value = code.value?.length == 6
   }

   fun postPhoneVerification(phone: Phone) = signupRepository.postPhoneVerification(phone).asLiveData()
   fun deletePhoneVerification(phoneVerification: PhoneVerification) = signupRepository.deletePhoneVerification(phoneVerification).asLiveData()
   fun changePhoneNumber(token: String, user: UserEdit) = myPageRepository.changePhoneNumber(token, user).asLiveData()
}