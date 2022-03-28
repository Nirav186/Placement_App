package com.example.placementapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.placementapp.data.model.StudentData
import com.example.placementapp.databinding.ItemStudentBinding

class StudentAdapter(private var studentList: ArrayList<StudentData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(var binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(h: RecyclerView.ViewHolder, position: Int) {
        val holder = h as ViewHolder
        holder.binding.studentName.text = studentList[holder.adapterPosition].studentName
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}