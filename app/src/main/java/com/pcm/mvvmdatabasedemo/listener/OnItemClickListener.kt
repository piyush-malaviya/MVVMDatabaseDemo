package com.pcm.mvvmdatabasedemo.listener

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(view: View?, item: T?, position: Int)
}
