package com.example.placementapp.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.databinding.ItemCompanyBinding
import com.example.placementapp.domain.database.DatabaseHelper
import com.example.placementapp.domain.usecases.UseCaseGetAllStudentsWithCompany
import com.example.placementapp.presentation.callbacks.OnCompanyClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyAdapter(
    var mActivity: Activity,
    var companyList: ArrayList<CompanyData>,
    var onCompanyClick: OnCompanyClick
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mDatabaseHelper = DatabaseHelper(mActivity)

    class ViewHolder(var binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(h: RecyclerView.ViewHolder, position: Int) {
        val holder = h as ViewHolder
        holder.binding.companyName.text = companyList[holder.adapterPosition].companyName
        holder.binding.companyItem.setOnClickListener { onCompanyClick.onClick(holder.adapterPosition) }
        UseCaseGetAllStudentsWithCompany(
            mDatabaseHelper,
            companyList[holder.adapterPosition].companyId!!
        ).invoke().observe(mActivity as LifecycleOwner) {
            holder.binding.studentCount.text = it[0].students.size.toString()
        }
    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}