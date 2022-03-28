package com.example.placementapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.data.model.StudentData
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.domain.usecases.UseCaseAddNewCompany
import com.example.placementapp.domain.usecases.UseCaseAddNewStudent
import com.example.placementapp.domain.usecases.UseCaseGetAllCompanies
import com.example.placementapp.domain.usecases.UseCaseGetSingleCompany

class AddStudentViewModel : ViewModel() {

    fun addNewStudent(mDatabaseHelper: DatabaseHelper, studentData: StudentData) {
        UseCaseAddNewStudent(mDatabaseHelper, studentData).invoke()
    }

}