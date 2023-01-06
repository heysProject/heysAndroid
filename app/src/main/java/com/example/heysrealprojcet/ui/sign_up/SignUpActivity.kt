package com.example.heysrealprojcet.ui.sign_up

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.heysrealprojcet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.sign_up_activity)
      window.statusBarColor = Color.parseColor("#F8F9FC")
   }
}