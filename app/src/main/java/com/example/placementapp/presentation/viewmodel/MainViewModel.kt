package com.example.placementapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.domain.usecases.UseCaseGetAllCompanies

class MainViewModel : ViewModel() {

    fun getAllCompanies(mDatabaseHelper: DatabaseHelper) =
        UseCaseGetAllCompanies(mDatabaseHelper).invoke()

}