package com.example.placementapp.presentation.view.activity

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.placementapp.R
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.databinding.ActivityMainBinding
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.presentation.ActBase
import com.example.placementapp.presentation.adapter.CompanyAdapter
import com.example.placementapp.presentation.callbacks.OnCompanyClick
import com.example.placementapp.presentation.utils.hide
import com.example.placementapp.presentation.utils.show
import com.example.placementapp.presentation.viewmodel.MainViewModel

class MainActivity : ActBase<ActivityMainBinding>() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mDatabaseHelper: DatabaseHelper
    private lateinit var companyAdapter: CompanyAdapter

    private var companyList = arrayListOf<CompanyData>()

    override fun setViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun bindObjects() {
        mDatabaseHelper = DatabaseHelper(this)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun bindListeners() {
        binding.tvAddCompany.setOnClickListener {
            startActivity(Intent(this, AddCompanyActivity::class.java))
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }

        binding.nodDataAddCompany.setOnClickListener {
            startActivity(Intent(this, AddCompanyActivity::class.java))
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
        }
    }

    override fun bindMethods() {
        mViewModel.getAllCompanies(mDatabaseHelper).observe(this) {
            companyList.clear()
            companyList.addAll(it)
            if (companyList.size == 0) {
                binding.noDataLayout.show()
                binding.rcvCompanies.hide()
            } else {
                binding.noDataLayout.hide()
                binding.rcvCompanies.show()
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.rcvCompanies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        companyAdapter = CompanyAdapter(this,companyList, object : OnCompanyClick {
            override fun onClick(pos: Int) {
                val intent = Intent(this@MainActivity, CompanyActivity::class.java)
                intent.putExtra("companyId", companyList[pos].companyId)
                startActivity(intent)
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
            }
        })
        binding.rcvCompanies.adapter = companyAdapter
    }

}