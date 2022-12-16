package com.example.heysrealprojcet.ui.sign_up.phone

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heysrealprojcet.databinding.SignUpPhoneFragmentBinding
import com.example.heysrealprojcet.model.Phone
import com.example.heysrealprojcet.model.network.NetworkResult
import com.example.heysrealprojcet.util.UserPreference
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpPhoneFragment : Fragment() {
   private lateinit var binding: SignUpPhoneFragmentBinding
   private val viewModel: SignUpPhoneViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
      binding = SignUpPhoneFragmentBinding.inflate(inflater, container, false)
      binding.vm = viewModel
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState) // livedata 사용할 때 객체 범위를 반드시 지정해줘야함!!
      binding.lifecycleOwner = this
      binding.okButton.setOnClickListener { requestCheckPhoneNumber() }
      binding.phoneInput.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))

      // 화면 들어오자마자 키보드 보이기
      val inputMethodManager =
         requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.showSoftInput(binding.phoneInput, 0)
   }

   private fun requestCheckPhoneNumber() {
      viewModel.checkPhoneNumber(Phone(UserPreference.phoneNumber))
      viewModel.response.observe(viewLifecycleOwner) { response ->
         val alert = AlertDialog.Builder(requireContext())
         when (response) {
            is NetworkResult.Success -> {
               if (response.data?.isUserExisted == true) {
                  val bottomSheet = ExistingUserBottomSheet()
                  bottomSheet.show(childFragmentManager, bottomSheet.tag)
               } else {
                  goToPhoneVerification()
               }
            }
            is NetworkResult.Error -> {
               alert.setTitle("전화번호 체크 실패").setMessage("전화번호 체크에 실패했습니다.").create().show()
            }

            is NetworkResult.Loading -> {
               alert.setTitle("로딩 중").setMessage("전화번호 체크가 지연되고 있습니다.").create().show()
            }
         }
      }
   }


   private fun goToPhoneVerification() {
      findNavController().navigate(
         com.example.heysrealprojcet.R.id.action_signUpPhoneFragment_to_signUpVerificationFragment,
         bundleOf("phoneNumber" to viewModel.phoneNumber.value))
   }
}