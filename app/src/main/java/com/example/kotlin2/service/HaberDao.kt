package com.example.kotlin2.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin2.Haber
import com.example.kotlin2.HaberResult

@Dao
interface HaberDao {

    @Query("SELECT * FROM haber")
    suspend fun getAllHaber(): List<Haber>

    @Query("SELECT * FROM haber WHERE id= :id")
    suspend fun getHaber(id: Int): Haber

    @Insert
    suspend fun insertAll(vararg habers: HaberResult): List<Long>

    @Query("DELETE FROM haber")
    suspend fun deleteHabers()


}