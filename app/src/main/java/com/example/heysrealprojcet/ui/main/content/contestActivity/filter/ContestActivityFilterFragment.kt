package com.example.heysrealprojcet.ui.main.content.contestActivity.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.heysrealprojcet.InterestViewModel
import com.example.heysrealprojcet.databinding.ContestActivityFilterFragmentBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

class ContestActivityFilterFragment : Fragment() {
   private lateinit var binding: ContestActivityFilterFragmentBinding
   private val contestActivityViewModel: ContestActivityFilterViewModel by viewModels()
   private val interestViewModel: InterestViewModel by viewModels()

   private var currentTime = YearMonth.now()
   private val formatter = DateTimeFormatter.ofPattern("yyyy년 M월")

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
      binding = ContestActivityFilterFragmentBinding.inflate(inflater, container, false)
      binding.vm = contestActivityViewModel
      binding.vm2 = interestViewModel
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.lifecycleOwner = this

      binding.btnApply.setOnClickListener { findNavController().navigateUp() }

      contestActivityViewModel.selectedDate

      contestActivityViewModel.calendarPosition.observe(viewLifecycleOwner) {
         if(currentTime.year == YearMonth.now().year && it == YearMonth.now().month.value) {
            binding.calendarBack.visibility = View.INVISIBLE
         } else {
            binding.calendarBack.visibility = View.VISIBLE
         }
      }

      binding.cafeteriaCalendar.itemAnimator = null

      binding.cafeteriaCalendar.setup(
         YearMonth.now(),
         YearMonth.now(),
         WeekFields.of(Locale.getDefault()).firstDayOfWeek
      )
      binding.yearMonth.text = currentTime.format(formatter)

      binding.calendarBack.setOnClickListener {
         if (currentTime.year > YearMonth.now().year) {
            if (contestActivityViewModel.calendarPosition.value == 1) {
               contestActivityViewModel.setPosition(12)
               currentTime = currentTime.minusYears(1)
               currentTime = currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!)
               binding.cafeteriaCalendar.setup(
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  WeekFields.of(Locale.getDefault()).firstDayOfWeek
               )
            } else {
               contestActivityViewModel.minusPosition()
               binding.cafeteriaCalendar.setup(
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  WeekFields.of(Locale.getDefault()).firstDayOfWeek
               )
               currentTime = currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!)
            }
         } else {
            if (currentTime.year == YearMonth.now().year &&
               contestActivityViewModel.calendarPosition.value!! > YearMonth.now().month.value) {
               contestActivityViewModel.minusPosition()
               binding.cafeteriaCalendar.setup(
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
                  WeekFields.of(Locale.getDefault()).firstDayOfWeek
               )
               currentTime = currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!)
            }
         }
         binding.yearMonth.text = currentTime.format(formatter)
      }

      binding.calendarForward.setOnClickListener {
         if (contestActivityViewModel.calendarPosition.value in 1..11) {
            currentTime = currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!)
            contestActivityViewModel.plusPosition()
            binding.cafeteriaCalendar.setup(
               currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
               currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
               WeekFields.of(Locale.getDefault()).firstDayOfWeek
            )
            currentTime = currentTime.plusMonths(1)
         } else {
            contestActivityViewModel.setPosition(1)
            currentTime = currentTime.plusYears(1)
            currentTime = currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!)
            binding.cafeteriaCalendar.setup(
               currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
               currentTime.withMonth(contestActivityViewModel.calendarPosition.value!!),
               WeekFields.of(Locale.getDefault()).firstDayOfWeek
            )
         }
         binding.yearMonth.text = currentTime.format(formatter)
      }

      binding.cafeteriaCalendar.dayBinder = object : DayBinder<ContestActivityFilterCafeteriaContainer> {
         override fun create(view: View): ContestActivityFilterCafeteriaContainer =
            ContestActivityFilterCafeteriaContainer(view, binding.cafeteriaCalendar, contestActivityViewModel)

         override fun bind(container: ContestActivityFilterCafeteriaContainer, day: CalendarDay) = container.bind(day)
      }
   }
}