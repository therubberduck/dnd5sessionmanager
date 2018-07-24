package com.dicemonger.campaignmanager.Frontend.Screens

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.dicemonger.campaignmanager.R

class DeletionDialog private constructor () {
    companion object {
        fun show(title: String, body: String, context: Context, callback: () -> Unit) : AlertDialog {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                    .setMessage(body)
                    .setPositiveButton(R.string.global_delete, object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, id: Int) {
                            callback()
                        }
                    })
                    .setNegativeButton(R.string.global_cancel, object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, id: Int) {
                            dialog?.dismiss()
                        }
                    })

            val dialog = builder.create()
            dialog.show()
            return dialog
        }
    }
}