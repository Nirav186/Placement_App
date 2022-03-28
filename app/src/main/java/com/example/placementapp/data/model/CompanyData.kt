package com.example.placementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyData(

    @PrimaryKey(autoGenerate = true)
    val companyId: Int? = null,

    @ColumnInfo(name = "companyName")
    var companyName: String

)
