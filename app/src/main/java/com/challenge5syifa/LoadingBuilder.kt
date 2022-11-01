package com.challenge5syifa

import android.app.AlertDialog
import android.content.Context

object LoadingBuilder {
    fun loadingDialog(ctx:Context): AlertDialog{
        val builder = AlertDialog.Builder(ctx)
        builder.setCancelable(false)
        builder.setView(R.layout.loading_dialog)
        return builder.create()
    }
}