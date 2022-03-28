package com.example.placementapp.presentation.view.activity

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.placementapp.R
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.databinding.ActivityAddCompanyBinding
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.presentation.ActBase
import com.example.placementapp.presentation.viewmodel.AddCompanyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCompanyActivity : ActBase<ActivityAddCompanyBinding>() {

    lateinit var mViewModel: AddCompanyViewModel
    lateinit var mDatabaseHelper: DatabaseHelper

    override fun setViewBinding() = ActivityAddCompanyBinding.inflate(layoutInflater)

    override fun bindObjects() {
        mDatabaseHelper = DatabaseHelper(this)
        mViewModel = ViewModelProvider(this).get(AddCompanyViewModel::class.java)
    }

    override fun bindListeners() {
        binding.tvSave.setOnClickListener {
            if (binding.etCompanyName.text.isNotEmpty()){
                saveCompany(binding.etCompanyName.text.toString())
            }else{
                Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show()
            }
        }

        binding.iconBack.setOnClickListener { onBackPressed() }
    }

    override fun bindMethods() {

    }

    private fun saveCompany(companyName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val cName = mViewModel.getCompanyData(mDatabaseHelper, companyName)
            if (cName == null) {
                mViewModel.addNewCompany(mDatabaseHelper, CompanyData(companyName = companyName))
                runOnUiThread {
                    Toast.makeText(this@AddCompanyActivity, "Saved", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(
                        this@AddCompanyActivity,
                        "Company already added in database",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

}