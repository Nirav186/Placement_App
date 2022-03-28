package com.example.placementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentData(

    @PrimaryKey(autoGenerate = true)
    val studentId: Int? = null,

    @ColumnInfo(name = "studentName")
    var studentName: String,

    @ColumnInfo(name = "companyId")
    var companyId: Int

)
