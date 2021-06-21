package com.pcm.mvvmdatabasedemo.views

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pcm.mvvmdatabasedemo.R
import com.pcm.mvvmdatabasedemo.databinding.ActivityCreateTodoBinding
import com.pcm.mvvmdatabasedemo.db.AppDatabase
import com.pcm.mvvmdatabasedemo.db.TodoViewModel
import com.pcm.mvvmdatabasedemo.db.TodoViewModelFactory
import com.pcm.mvvmdatabasedemo.db.entity.TodoEntity
import com.pcm.mvvmdatabasedemo.extensions.setToolbarWithBack
import com.pcm.mvvmdatabasedemo.extensions.showToast
import com.pcm.mvvmdatabasedemo.model.TodoRepository

class CreateTodoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateTodoBinding
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(TodoRepository(AppDatabase.invoke(this@CreateTodoActivity).todoDao()))
    }

    //private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        setToolbarWithBack(binding.toolbar, R.string.create_todo)

        binding.btnCreate.setOnClickListener {
            createTodo()
        }
    }

    private fun isValid(): Boolean {
        if (binding.edtTitle.text?.trim().isNullOrEmpty()) {
            showToast(getString(R.string.please_enter_title))
            return false
        }
        if (binding.edtDescription.text?.trim().isNullOrEmpty()) {
            showToast(getString(R.string.please_enter_description))
            return false
        }
        return true
    }

    private fun createTodo() {
        if (!isValid()) return

        val todo = TodoEntity(
            binding.edtTitle.text.toString(),
            binding.edtDescription.text.toString(),
            System.currentTimeMillis()
        )

        todoViewModel.insert(todo)
        showToast(getString(R.string.todo_created_successfully))
        finish()
    }

}