@file:JvmName("ExtensionsUtils")

package com.org.demo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint

import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged


fun Activity.toast(resourceId: Int) = toast(getString(resourceId))

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    toast?.cancel()
    Toast.makeText(this, message, duration).show()

}

fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit))
{
    if (value1 != null && value2 != null)
        bothNotNull(value1, value2)

}


inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}
inline fun <reified T: Activity> Activity.openActivityForResult(block:Intent.() -> Unit = {}, requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java).apply(block), requestCode)
}


var toast :Toast? = null

fun EditText.onChange(cb: (String) -> Unit){
    this.doAfterTextChanged {

    }
}

fun View.makeGone(){
    this.visibility = View.GONE
}

fun View.makeVisible(){
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.clickable(){
   this.isClickable = true
}

fun View.notClickable(){
    this.isClickable = false
}

fun View.enabled(){
    this.isEnabled = true
}

fun View.disabled(){
    this.isEnabled = false
}

fun View.preventTwoClick(){
    this.disabled()
    this.postDelayed({this.enabled()}, 600)
}


fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun String.getZeroDefault(): String {
    return if(this.equals("null"))
        "0" else
        this
}


fun View.disableWithAlpha() {
    isEnabled = false
    isClickable = false
    alpha = 0.5F
}

fun View.enableWithAlph() {
    isEnabled = true
    isClickable = true
    alpha = 1F
}

