package com.julianmartinez.ccp.data

import com.julianmartinez.ccp.R
import java.util.Locale

data class CountryData(
    private var cCodes: String,
    val countryPhoneCode: String = "+90",
    val cNames:String = "tr",
    val flagResID: Int = R.drawable.co
) {
    val countryCode = cCodes.lowercase(Locale.getDefault())
}