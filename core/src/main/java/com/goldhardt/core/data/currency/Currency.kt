package com.goldhardt.core.data.currency

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(@SerializedName("simbolo") val symbol: String,
                    @SerializedName("nomeFormatado") val name: String,
                    @SerializedName("tipoMoeda") val type: String) : Parcelable