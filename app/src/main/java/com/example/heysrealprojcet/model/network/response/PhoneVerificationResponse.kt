package com.example.heysrealprojcet.model.network.response

import com.example.heysrealprojcet.model.network.PhoneVerificationData
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneVerificationResponse(
   @SerializedName("message") val message: String,
   @SerializedName("isSuccess") val isSuccess: Boolean,
   @SerializedName("data") val data: PhoneVerificationData,
) : java.io.Serializable