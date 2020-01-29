package com.popwoot.ui


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.popwoot.R
import com.popwoot.utils.showSnackBar


abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun showSnackBarMessage(str:String) {
        showSnackBar(str, Color.WHITE)
    }

    fun showSnackBarErr(){
        showSnackBar(getString(R.string.no_internet), Color.WHITE)
    }

}
