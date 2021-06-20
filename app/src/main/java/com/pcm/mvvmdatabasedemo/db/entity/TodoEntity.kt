package com.pcm.mvvmdatabasedemo.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entity_todo")
data class TodoEntity(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "timestamp") var timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}