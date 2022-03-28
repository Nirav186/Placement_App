package com.example.placementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.placementapp.data.model.CompanyData
import com.example.placementapp.data.model.CompanyWithStudents
import com.example.placementapp.data.model.StudentData

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewCompany(companyData: CompanyData): Long

    @Query("select * from CompanyData")
    fun getAllCompanies(): LiveData<MutableList<CompanyData>>

    @Query("select * from CompanyData WHERE companyName is :companyName")
    fun getSingleCompany(companyName: String): CompanyData

    @Query("select * from CompanyData WHERE companyId is :mId")
    fun getSingleCompanyById(mId: Int): CompanyData

    @Query("delete from CompanyData where companyId in (:mId)")
    fun deleteCompanies(mId: MutableList<Int>): Int

    @Update
    fun update(companyData: CompanyData)

    @Query("select companyId from CompanyData where companyId = (select MAX(companyId) from CompanyData)")
    fun getLastIndex(): Int

    @Query("select * from CompanyData where companyId=:mId")
    fun getStudentsWithCompany(mId: Int): LiveData<List<CompanyWithStudents>>
}