package com.dicemonger.campaignmanager.Utility

import com.wealthfront.magellan.Screen

fun Screen<*>.getString(resId: Int, vararg formatArgs: Any) : String{
    return this.getActivity().getString(resId, formatArgs)
}