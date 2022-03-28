package com.example.placementapp.domain.usecases

import com.example.placementapp.data.model.StudentData
import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseAddNewStudent(
    private var databaseHelper: DatabaseHelper,
    private var studentData: StudentData
) {

    operator fun invoke() = databaseHelper.getStudentDatabaseDao().addNewStudent(studentData)

}