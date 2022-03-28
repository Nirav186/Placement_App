package com.example.placementapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.domain.usecases.UseCaseGetAllCompanies
import com.example.placementapp.domain.usecases.UseCaseGetAllStudentsWithCompany
import com.example.placementapp.domain.usecases.UseCaseGetSingleCompanyById

class CompanyViewModel : ViewModel() {

    fun getCompanyDataById(mDatabaseHelper: DatabaseHelper, mId: Int) =
        UseCaseGetSingleCompanyById(mDatabaseHelper, mId).invoke()

    fun getAllStudentsWithCompany(mDatabaseHelper: DatabaseHelper, mId: Int) =
        UseCaseGetAllStudentsWithCompany(mDatabaseHelper, mId).invoke()

}