package com.dicemonger.campaignmanager.Utility

import android.content.res.Resources

fun Int.dp(): Int = (this.toFloat() * Resources.getSystem().displayMetrics.density).toInt()
fun Int.dpToPx(): Int = (this.toFloat() / Resources.getSystem().displayMetrics.density).toInt()