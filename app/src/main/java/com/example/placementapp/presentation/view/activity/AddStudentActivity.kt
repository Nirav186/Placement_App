package com.example.placementapp.presentation.view.activity

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.placementapp.R
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.data.model.StudentData
import com.example.placementapp.databinding.ActivityAddStudentBinding
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.presentation.ActBase
import com.example.placementapp.presentation.viewmodel.AddStudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentActivity : ActBase<ActivityAddStudentBinding>() {

    lateinit var mViewModel: AddStudentViewModel
    lateinit var mDatabaseHelper: DatabaseHelper
    var cId = -1

    override fun setViewBinding() = ActivityAddStudentBinding.inflate(layoutInflater)

    override fun bindObjects() {
        mDatabaseHelper = DatabaseHelper(this)
        mViewModel = ViewModelProvider(this).get(AddStudentViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            cId = intent.getIntExtra("companyId", -1)
        }
    }

    override fun bindListeners() {
        binding.tvSave.setOnClickListener {
           if (binding.etStudentName.text.isNotEmpty()){
               saveStudent(binding.etStudentName.text.toString())
           }else{
               Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show()
           }
        }
    }

    override fun bindMethods() {

    }

    private fun saveStudent(studentName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mViewModel.addNewStudent(
                mDatabaseHelper,
                StudentData(studentName = studentName, companyId = cId)
            )
            runOnUiThread {
                Toast.makeText(this@AddStudentActivity, "Saved", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

}