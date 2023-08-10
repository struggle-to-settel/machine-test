package com.test.machinetest.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.test.machinetest.model.Article

@Dao
interface NewsDao {

    @Insert
    fun insert(note: Article)

    @Delete
    fun delete(note: Article)

    @Query("select * from news")
    fun getAllNews(): List<Article>

    @Query("delete from news")
    fun deleteAllNews(): Unit
}