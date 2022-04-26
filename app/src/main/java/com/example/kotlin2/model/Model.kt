package com.example.kotlin2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class Haber(
    @ColumnInfo(name ="name")
     val name: String?,

    @ColumnInfo(name ="description")
     val description: String?,

    @ColumnInfo(name ="source")
     val source: String?,

    @ColumnInfo(name ="image")
     val image: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class HaberResult(val result:ArrayList<Haber>)



