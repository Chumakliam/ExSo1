package com.example.exada1.PAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exada1.PDataPackage.cmlDataList
import com.example.exada1.R

class C01MainAdapter(private val oList: MutableList<cmlDataList>):
    RecyclerView.Adapter<C01MainAdapter.CViewHolder>() {
      var bSelectedAll: Boolean = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): C01MainAdapter.CViewHolder {
        val oItemView = LayoutInflater.from(parent.context).inflate(R.layout.w01data_view,parent,false)
        return CViewHolder(oItemView)
    }

    override fun onBindViewHolder(holder: C01MainAdapter.CViewHolder, position: Int) {
        val aoCurrentItem = oList[position]
        holder.bCheckBox.setText(aoCurrentItem.tTitle)
        holder.tDate.setText(aoCurrentItem.tDate)
        if (!bSelectedAll){
            holder.bCheckBox.setChecked(false)
        }
        else  holder.bCheckBox.setChecked(true)
    }
//    fun selectAll() {
//        bSelectedAll = true
//        notifyDataSetChanged()
//    }
//
//    fun unselectall() {
//        bSelectedAll = false
//        notifyDataSgetItemCountetChanged()
//    }

    override fun getItemCount(): Int {
        return oList.size
    }

    class CViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val bCheckBox : CheckBox=itemView.findViewById(R.id.ocbChecBoxView)
        val tDate : TextView=itemView.findViewById(R.id.otvDateView)


    }

}