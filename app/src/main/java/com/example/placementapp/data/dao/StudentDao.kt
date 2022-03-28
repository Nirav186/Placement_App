package com.example.placementapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.placementapp.data.model.StudentData

@Dao
interface StudentDao {

    @Insert
    fun addNewStudent(studentData: StudentData): Long

    @Query("select * from StudentData")
    fun getAllStudents(): MutableList<StudentData>

    @Query("select * from StudentData WHERE studentId is :id")
    fun getSingleStudent(id: Int): StudentData

    @Query("delete from StudentData where studentId in (:mId)")
    fun deleteStudents(mId: MutableList<Int>): Int

    @Update
    fun update(studentData: StudentData)

    @Query("select studentId from StudentData where studentId = (select MAX(studentId) from StudentData)")
    fun getLastIndex(): Int

}