package com.example.heysrealprojcet.ui.main.content.study.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heysrealprojcet.databinding.StudyFilterFragmentBinding

class StudyFilterFragment : Fragment() {
   private lateinit var binding: StudyFilterFragmentBinding
   private val viewModel: StudyFilterViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
      binding = StudyFilterFragmentBinding.inflate(inflater, container, false)
      binding.vm = viewModel
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.lifecycleOwner = this
      binding.btnApply.setOnClickListener { findNavController().navigateUp() }
   }
}