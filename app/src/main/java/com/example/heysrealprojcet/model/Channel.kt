package com.example.heysrealprojcet.model

import com.example.heysrealprojcet.enums.ChannelStatus
import com.example.heysrealprojcet.enums.ChannelType

data class Channel(
   val resId: Int,
   val title: String,
   val period: Int,
   val status: ChannelStatus,
   val capacity: Int,
   val view: Int,
   val type: ChannelType = ChannelType.Contest
)