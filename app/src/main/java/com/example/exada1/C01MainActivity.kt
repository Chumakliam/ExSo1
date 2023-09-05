package com.example.exada1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exada1.PAdapter.C01MainAdapter
import com.example.exada1.PDataPackage.CData
import com.example.exada1.PHelper.CSQLiteHelper
import java.text.SimpleDateFormat
import java.util.Calendar

class C01MainActivity : AppCompatActivity() {
    private lateinit var bCheckNewData: CheckBox
    private lateinit var ollLayoutSubData: LinearLayout
    private lateinit var tShowDate: EditText
    private lateinit var bCalenderButton: ImageButton
    private lateinit var orvDataView: RecyclerView
    private lateinit var aoArrayList: ArrayList<CData>
    private  val atTitle = mutableListOf<String>()
    private val atDate = mutableListOf<String>()
    private lateinit var bDownloadButton: Button
    private lateinit var tSearch: EditText
    private lateinit var bSearchButton: ImageButton
    private var oDb:CSQLiteHelper?=null
    private lateinit var bCheckSelectAll : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        C_SETxInitView()
        val ocdCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            ocdCalendar.set(Calendar.YEAR,year)
            ocdCalendar.set(Calendar.MONTH,month)
            ocdCalendar.set(Calendar.DAY_OF_MONTH,dayofMonth)
            C_SETxLavle(ocdCalendar)
        }
        bCalenderButton.setOnClickListener{
            DatePickerDialog(this,datePicker,ocdCalendar.get(Calendar.YEAR),ocdCalendar.get(Calendar.MONTH),
                ocdCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        oDb = CSQLiteHelper(this)
//        db?.C_GEToOpenDatabase()
        C_GEToData()
        aoArrayList = arrayListOf<CData>()
        C_SETxData()
    }

    private fun C_SETxData(){
        for(i in atTitle.indices){
            val oData = CData(atTitle[i],atDate[i])
            aoArrayList.add(oData)
        }
        orvDataView.adapter=C01MainAdapter(aoArrayList)
    }
    private fun C_SETxLavle(ocdCalendar: Calendar){
        val tMyFormat="yyyy-MM-dd"
        val oSdf = SimpleDateFormat(tMyFormat)
        tShowDate.setText(oSdf.format(ocdCalendar.time))
    }

    @SuppressLint("Range")
    private fun C_GEToData(){
        try {
            var sqlQuery = "SELECT FTSynName,FDSynLast " +
                    "FROM  TSysSyncData_L a " +
                    "INNER JOIN  TSysSyncData b " +
                    "ON a.FNSynSeqNo=b.FNSynSeqNo  WHERE a.FNLngID=1 "

            oDb?.C_GEToQuery(sqlQuery)?.use {
                if(it.count>0){
                    do {
                        atTitle.add(it.getString(it.getColumnIndex("FTSynName")))
                        atDate.add(it.getString(it.getColumnIndex("FDSynLast")))
                    }while (it.moveToNext())
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    private fun C_SETxInitView(){
        setContentView(R.layout.w01activity_main)
        bCheckNewData = findViewById(R.id.ocbCheckNewData)
        ollLayoutSubData = findViewById(R.id.ollLayoutSubData)
        tShowDate = findViewById(R.id.oetShowDate)
        bCalenderButton = findViewById(R.id.ocmCalenderButton)
        orvDataView=findViewById(R.id.orvDataView)
        orvDataView.layoutManager=LinearLayoutManager(this)
        orvDataView.setHasFixedSize(true)
        bDownloadButton = findViewById(R.id.ocmButtonDowload)
        tSearch=findViewById(R.id.oetSearch)
        bSearchButton = findViewById(R.id.ocmSearchButton)
        bCheckSelectAll =findViewById(R.id.ocbCheckSelectAll)
        bCheckNewData.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ollLayoutSubData.visibility = View.GONE
            }
            else{

                ollLayoutSubData.visibility = View.VISIBLE
            }
        }

    }
}