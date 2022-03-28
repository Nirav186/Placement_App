package com.example.placementapp.domain.usecases

import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseAddNewCompany(
    private var databaseHelper: DatabaseHelper,
    private var companyData: CompanyData
) {

    operator fun invoke() = databaseHelper.getCompanyDatabaseDao().addNewCompany(companyData)

}