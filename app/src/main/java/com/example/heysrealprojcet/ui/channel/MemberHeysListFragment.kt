package com.example.heysrealprojcet.ui.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heysrealprojcet.R
import com.example.heysrealprojcet.databinding.MemberHeysListFragmentBinding
import com.example.heysrealprojcet.ui.main.MainActivity

class MemberHeysListFragment : Fragment() {
   private lateinit var binding: MemberHeysListFragmentBinding
   private lateinit var memberHeysItemRecyclerViewAdapter: MemberHeysItemRecyclerViewAdapter
   private lateinit var userNameList: MutableList<String>

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      val mainActivity = activity as MainActivity
      mainActivity.hideBottomNavigation(true)
   }

   override fun onDestroy() {
      super.onDestroy()
      val mainActivity = activity as MainActivity
      mainActivity.hideBottomNavigation(false)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
      binding = MemberHeysListFragmentBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.lifecycleOwner = this
      makeList()
      memberHeysItemRecyclerViewAdapter = MemberHeysItemRecyclerViewAdapter(name = userNameList) { showProfileDialog() }
      binding.heysList.adapter = memberHeysItemRecyclerViewAdapter
      binding.heysList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
   }

   private fun makeList() {
      userNameList = mutableListOf("이름1", "이름2", "이름3", "이름4", "이름5")
   }

   private fun showProfileDialog() {
      ProfileDialog(requireContext()) { goToChat() }.show()
   }

   private fun goToChat() {
      findNavController().navigate(R.id.action_memberHeysListFragment_to_heysChannelChatFragment)
   }
}