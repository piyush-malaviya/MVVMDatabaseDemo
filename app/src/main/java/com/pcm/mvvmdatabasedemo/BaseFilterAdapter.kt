package com.pcm.mvvmdatabasedemo

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.pcm.mvvmdatabasedemo.listener.OnItemClickListener
import java.util.*

abstract class BaseFilterAdapter<T, S : RecyclerView.ViewHolder> : RecyclerView.Adapter<S>(),
    Filterable {

    @JvmField
    var onItemClickListener: OnItemClickListener<T>? = null

    val originalArrayList = ArrayList<T>()
    var mArrayList = ArrayList<T>()
    private var recyclerView: RecyclerView? = null

    fun setDataArrayList(mArrayList: ArrayList<T>) {
        this.mArrayList = mArrayList
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun addItem(item: T?) {
        if (item == null) return
        mArrayList.add(item)
        originalArrayList.add(item)
        notifyDataSetChanged()
    }

    fun setItems(arrayList: ArrayList<T>?) {
        val dataList = arrayList ?: ArrayList()
        //if (arrayList.isNullOrEmpty()) return
        this.originalArrayList.clear()
        this.originalArrayList.addAll(dataList)
        this.mArrayList.clear()
        this.mArrayList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addItems(arrayList: ArrayList<T>?) {
        if (arrayList.isNullOrEmpty()) return
        this.originalArrayList.addAll(arrayList)
        this.mArrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    fun remove(item: T?) {
        if (item != null && mArrayList.remove(item)) {
            originalArrayList.remove(item)
            notifyDataSetChanged()
        }
    }

    fun getList(): ArrayList<T> {
        return mArrayList
    }

    fun getListItem(position: Int): T? {
        return if (position > mArrayList.size) {
            null
        } else mArrayList[position]
    }

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                mArrayList.clear()

                if (TextUtils.isEmpty(charSequence)) {
                    mArrayList.addAll(originalArrayList)
                } else {
                    val filteredList = ArrayList<T>()
                    for (item in originalArrayList) {
                        filterObject(filteredList, item, charSequence.toString())
                    }
                    mArrayList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mArrayList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {

                Handler(Looper.getMainLooper()).postDelayed({
                    if (recyclerView != null) {
                        recyclerView?.recycledViewPool?.clear()
                    }
                    notifyDataSetChanged()
                }, 100)
            }
        }
    }

    protected abstract fun filterObject(filteredList: ArrayList<T>, item: T, searchText: String)

}