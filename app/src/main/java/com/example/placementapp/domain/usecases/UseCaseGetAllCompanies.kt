package com.example.placementapp.domain.usecases

import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseGetAllCompanies(private var databaseHelper: DatabaseHelper) {

    operator fun invoke() = databaseHelper.getCompanyDatabaseDao().getAllCompanies()

}