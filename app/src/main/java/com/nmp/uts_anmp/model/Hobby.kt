package com.nmp.uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hobbies")
data class Hobby(
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "photoUrl")
    var photoUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}