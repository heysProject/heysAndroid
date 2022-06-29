package com.example.heysrealprojcet.ui.join.gender

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heysrealprojcet.enums.Gender
import com.example.heysrealprojcet.util.UserPreference

class JoinGenderViewModel : ViewModel() {
   private val _isMale = MutableLiveData<Boolean>()
   val isMale: LiveData<Boolean> = _isMale

   private val _isFemale = MutableLiveData<Boolean>()
   val isFemale: LiveData<Boolean> = _isFemale

   private val _isNonBinary = MutableLiveData<Boolean>()
   val isNonBinary: LiveData<Boolean> = _isNonBinary

   fun onClickGender(v: View) {
      if (v.tag == "male") {
         _isMale.value = true
         _isFemale.value = false
         _isNonBinary.value = false
         UserPreference.gender = Gender.Male.gender
      } else if (v.tag == "female") {
         _isMale.value = false
         _isFemale.value = true
         _isNonBinary.value = false
         UserPreference.gender = Gender.Female.gender
      } else {
         _isMale.value = false
         _isFemale.value = false
         _isNonBinary.value = true
         UserPreference.gender = Gender.NonBinary.gender
      }
   }
}