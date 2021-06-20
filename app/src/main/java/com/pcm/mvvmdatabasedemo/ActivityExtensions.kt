package com.pcm.mvvmdatabasedemo

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setToolbarWithBack(toolbar: Toolbar, resId: Int) {
    setToolbarWithBack(toolbar, getString(resId))
}

fun AppCompatActivity.setToolbarWithBack(toolbar: Toolbar, title: String?) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = title
}

fun AppCompatActivity.setToolbar(toolbar: Toolbar, resId: Int) {
    setToolbar(toolbar, getString(resId))
}

fun AppCompatActivity.setToolbar(toolbar: Toolbar, title: String?) {
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
}

fun AppCompatActivity.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
