package com.popwoot.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.popwoot.R


//Custom Snackbar
fun AppCompatActivity.showSnackBar(message: String, color: Int): Snackbar {
    val sb =
        Snackbar.make(this.findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_SHORT)
    sb.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
    val textView = sb.view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(color)
    sb.show()
    return sb
}

fun Context.hideSoftKeyboard() {
    try {
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((this as Activity).currentFocus!!.windowToken, 0)
    } catch (e: Exception) {
    }
}
fun Context.verifyAvailableNetwork():Boolean{
    val connectivityManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo=connectivityManager.activeNetworkInfo
    return  networkInfo!=null && networkInfo.isConnected
}


fun ImageView.loadImage(url: String) {
    val circularProgressDrawable =
        CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .fitCenter()
        .into(this)
}