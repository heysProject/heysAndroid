package com.example.heys.ui.user.setting.password

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heys.R
import com.example.heys.databinding.SettingPasswordChangeFragmentBinding
import com.example.heys.util.UserPreference

class SettingPasswordChangeFragment : Fragment() {
   private lateinit var binding: SettingPasswordChangeFragmentBinding
   private val viewModel: SettingPasswordChangeViewModel by viewModels()

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding = SettingPasswordChangeFragmentBinding.inflate(inflater, container, false)
      binding.vm = viewModel
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.lifecycleOwner = this

      binding.okButton.setOnClickListener {
         if (UserPreference.isAutoLogin) {
            goToSetting()
         } else {
            goToLogin()
         }
      }

      val inputMethodManager =
         requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.showSoftInput(binding.password, 0)
   }

   private fun goToLogin() {
      findNavController().navigate(R.id.action_settingPasswordChangeFragment2_to_signInPhoneFragment)
   }

   private fun goToSetting() {
      findNavController().navigate(R.id.action_settingPasswordChangeFragment_to_settingFragment)
   }
}