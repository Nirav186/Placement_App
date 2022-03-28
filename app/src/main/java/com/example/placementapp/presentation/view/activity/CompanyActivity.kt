package com.example.placementapp.presentation.view.activity

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placementapp.R
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.data.model.StudentData
import com.example.placementapp.databinding.ActivityCompanyBinding
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.presentation.ActBase
import com.example.placementapp.presentation.adapter.CompanyAdapter
import com.example.placementapp.presentation.adapter.StudentAdapter
import com.example.placementapp.presentation.callbacks.OnCompanyClick
import com.example.placementapp.presentation.utils.hide
import com.example.placementapp.presentation.utils.show
import com.example.placementapp.presentation.viewmodel.CompanyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyActivity : ActBase<ActivityCompanyBinding>() {

    lateinit var mViewModel: CompanyViewModel
    lateinit var mDatabaseHelper: DatabaseHelper
    lateinit var mCompanyData: CompanyData
    lateinit var studentAdapter: StudentAdapter
    private var studentList = arrayListOf<StudentData>()

    override fun setViewBinding() = ActivityCompanyBinding.inflate(layoutInflater)

    override fun bindObjects() {
        mDatabaseHelper = DatabaseHelper(this)
        mViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val cId = intent.getIntExtra("companyId", -1)
            if (cId != -1) {
                mCompanyData =
                    mViewModel.getCompanyDataById(mDatabaseHelper, cId)
                runOnUiThread {
                    binding.tbCompanyName.text = mCompanyData.companyName
                    mViewModel.getAllStudentsWithCompany(mDatabaseHelper, mCompanyData.companyId!!)
                        .observe(this@CompanyActivity) {
                            studentList.clear()
                            studentList.addAll(it[0].students)
                            if (studentList.size == 0) {
                                binding.noDataLayout.show()
                                binding.rcvCompanies.hide()
                            } else {
                                binding.noDataLayout.hide()
                                binding.rcvCompanies.show()
                            }
                        }
                }
            }
        }
    }

    override fun bindListeners() {
        binding.nodDataAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            intent.putExtra("companyId", mCompanyData.companyId)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        binding.addStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            intent.putExtra("companyId", mCompanyData.companyId)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        binding.iconBack.setOnClickListener { onBackPressed() }
    }

    override fun bindMethods() {
        setAdapter()
    }

    private fun setAdapter() {
        binding.rcvCompanies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        studentAdapter = StudentAdapter(studentList)
        binding.rcvCompanies.adapter = studentAdapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

}