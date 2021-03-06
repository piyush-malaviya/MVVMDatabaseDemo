package com.pcm.mvvmdatabasedemo.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pcm.mvvmdatabasedemo.R
import com.pcm.mvvmdatabasedemo.adapter.TodoAdapter
import com.pcm.mvvmdatabasedemo.databinding.ActivityMainBinding
import com.pcm.mvvmdatabasedemo.db.AppDatabase
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import com.pcm.mvvmdatabasedemo.extensions.setToolbar
import com.pcm.mvvmdatabasedemo.listener.OnItemClickListener
import com.pcm.mvvmdatabasedemo.model.TodoRepository
import com.pcm.mvvmdatabasedemo.viewmodel.TodoViewModel
import com.pcm.mvvmdatabasedemo.viewmodel.TodoViewModelFactory

class MainActivity : AppCompatActivity(), OnItemClickListener<TodoEntity> {

    private lateinit var binding: ActivityMainBinding
    var todoAdapter: TodoAdapter? = null

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(TodoRepository(AppDatabase.invoke(this@MainActivity).todoDao()))
    }
    //private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        initView()
    }

    private fun initView() {
        setToolbar(binding.toolbar, R.string.app_name)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreateTodoActivity::class.java))
        }

        todoAdapter = TodoAdapter(this)
        binding.apply {
            rvTodoList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodoList.adapter = todoAdapter
            rvTodoList.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        todoViewModel.allTodo.observe(this, {
            it?.let { todoAdapter?.setItems(ArrayList(it)) }
        })

    }

    override fun onItemClick(view: View?, item: TodoEntity?, position: Int) {
        if (view?.id == R.id.ivDelete) {
            item ?: return
            todoViewModel.delete(item)
        }
    }


}