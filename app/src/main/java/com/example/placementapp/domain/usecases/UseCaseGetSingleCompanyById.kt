package com.example.placementapp.domain.usecases

import com.example.placementapp.domain.database.DatabaseHelper

class UseCaseGetSingleCompanyById(private var databaseHelper: DatabaseHelper, var mId: Int) {

    operator fun invoke() = databaseHelper.getCompanyDatabaseDao().getSingleCompanyById(mId)

}