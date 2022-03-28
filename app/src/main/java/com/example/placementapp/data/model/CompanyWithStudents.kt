package com.example.placementapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithStudents(
    @Embedded val companyData: CompanyData,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val students:List<StudentData>
)
