package com.example.rickandmorty.view

import android.content.Context
import android.widget.Toast

class ToastUtil {


    companion object{
        fun showToast(message: String, length: Int, context: Context){
            Toast.makeText(context, message, length).show()
        }

       fun showLong(resourceId: Int, context: Context){
           showToast(context.getString(resourceId), Toast.LENGTH_LONG, context)
       }

        fun showLong(message: String, context: Context){
            showToast(message, Toast.LENGTH_LONG, context)
        }

        fun showShort(resourceId: Int, context: Context){
            showToast(context.getString(resourceId), Toast.LENGTH_SHORT, context)
        }

        fun showShort(message: String, context: Context){
            showToast(message, Toast.LENGTH_SHORT, context)
        }
    }
}