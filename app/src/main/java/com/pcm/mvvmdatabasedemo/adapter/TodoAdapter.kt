package com.pcm.mvvmdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pcm.mvvmdatabasedemo.databinding.TodoItemLayoutBinding
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import com.pcm.mvvmdatabasedemo.listener.OnItemClickListener
import java.util.*

class TodoAdapter(listener: OnItemClickListener<TodoEntity>? = null) :
    BaseFilterAdapter<TodoEntity, TodoAdapter.TodoViewHolder>() {

    init {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val itemBinding =
            TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(getListItem(position))
    }

    override fun filterObject(
        filteredList: ArrayList<TodoEntity>,
        item: TodoEntity,
        searchText: String
    ) {
    }

    inner class TodoViewHolder(var itemBinding: TodoItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        fun onBind(todo: TodoEntity?) {
            itemBinding.tvTitle.text = todo?.title
            itemBinding.tvDescription.text = todo?.description
            itemBinding.ivDelete.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition >= 0) {
                onItemClickListener?.onItemClick(v, getListItem(adapterPosition), adapterPosition)
            }
        }

    }
}