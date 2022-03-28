package com.example.placementapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.domain.usecases.UseCaseAddNewCompany
import com.example.placementapp.domain.usecases.UseCaseGetAllCompanies
import com.example.placementapp.domain.usecases.UseCaseGetSingleCompany

class AddCompanyViewModel : ViewModel() {

    fun getCompanyData(mDatabaseHelper: DatabaseHelper, companyName: String) =
        UseCaseGetSingleCompany(mDatabaseHelper, companyName).invoke()

    fun addNewCompany(mDatabaseHelper: DatabaseHelper,companyData: CompanyData){
        UseCaseAddNewCompany(mDatabaseHelper,companyData).invoke()
    }

}