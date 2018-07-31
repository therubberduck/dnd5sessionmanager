package com.dicemonger.campaignmanager.Utility

fun Int.moveWithInBounds(start: Int, end: Int) : Int {
    var num = this
    if(num >= end) {
        num = end -1
    }
    if(num < 0) {
        num = 0
    }
    return num
}