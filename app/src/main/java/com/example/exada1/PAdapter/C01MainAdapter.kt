package com.example.exada1.PAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exada1.PDataPackage.cmlData
import com.example.exada1.R

class C01MainAdapter(private val oList: ArrayList<cmlData>):
    RecyclerView.Adapter<C01MainAdapter.CViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): C01MainAdapter.CViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.w01data_view,parent,false)
        return CViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: C01MainAdapter.CViewHolder, position: Int) {
        val currentItem = oList[position]
        holder.bCheckBox.setText(currentItem.tTitle)
        holder.tDate.setText(currentItem.tDate)
    }

    override fun getItemCount(): Int {
        return oList.size
    }

    class CViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val bCheckBox : CheckBox=itemView.findViewById(R.id.ocbChecBoxView)
        val tDate : TextView=itemView.findViewById(R.id.otvDateView)


    }

}