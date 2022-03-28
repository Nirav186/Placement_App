package com.example.placementapp.domain.usecases

import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseGetAllStudentsWithCompany(private var databaseHelper: DatabaseHelper,var companyId: Int) {

    operator fun invoke() = databaseHelper.getCompanyDatabaseDao().getStudentsWithCompany(companyId)

}