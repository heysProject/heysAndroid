package com.example.heysrealprojcet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.heysrealprojcet.databinding.ChannelPurposeFragmentDialogBinding

class ChannelPurposeDialogFragment: DialogFragment() {
   private lateinit var binding: ChannelPurposeFragmentDialogBinding
   private val viewModel: ChannelPurposeDialogViewModel by viewModels()

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding = ChannelPurposeFragmentDialogBinding.inflate(inflater, container, false)
      binding.vm = viewModel
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.lifecycleOwner = this

      binding.btnCapability.isSelected = true
      viewModel.choicePurpose = binding.btnCapability
   }
}