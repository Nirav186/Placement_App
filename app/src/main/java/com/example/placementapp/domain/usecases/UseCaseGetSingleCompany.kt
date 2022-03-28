package com.example.placementapp.domain.usecases

import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseGetSingleCompany(private var databaseHelper: DatabaseHelper, var companyName: String) {

    operator fun invoke() = databaseHelper.getCompanyDatabaseDao().getSingleCompany(companyName)

}