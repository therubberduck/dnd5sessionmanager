package com.dicemonger.campaignmanager.Frontend.Dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.dicemonger.campaignmanager.R

class TextDialog private constructor () {
    companion object {
        fun show(title: String?, body: String, context: Context, callback: () -> Unit) : AlertDialog {
            val builder = AlertDialog.Builder(context)

            if(title != null) {
                builder.setTitle(title)
            }

            builder.setMessage(body)
            
            builder.setPositiveButton(R.string.global_ok, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        callback()
                    }
                })

            val dialog = builder.create()
            dialog.show()
            return dialog
        }
    }
}