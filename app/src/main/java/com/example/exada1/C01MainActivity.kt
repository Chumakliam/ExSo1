package com.example.exada1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exada1.PAdapter.C01MainAdapter
import com.example.exada1.PApi.IApiService
import com.example.exada1.PApi.OApiUtilities
import com.example.exada1.PDataPackage.cmlDataList
import com.example.exada1.PHelper.CSQLiteHelper
import com.example.exada1.PRepository.CRepository
import com.example.exada1.PViewmodel.C01MainViewModel
import com.example.exada1.PViewmodel.CAppViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar

class C01MainActivity : AppCompatActivity() {
    private lateinit var oC_MainViewModel: C01MainViewModel
    private lateinit var oC_ShowDate: EditText
    private lateinit var oC_DataView: RecyclerView
    private var oC_Db:CSQLiteHelper?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        C_SETxInitView()
        oC_Db = CSQLiteHelper(this)
        if(!oC_Db?.C_GETbCheckDataBase()!!){
            oC_Db?.C_GEToOpenDatabase()
        }else{
            Log.d("MainActivity", "Database is: exists")
        }
        C_SETxData()
        val oApiService = OApiUtilities.oRetrofit.create(IApiService::class.java)
        val oRepository = CRepository(oApiService, oC_Db!!)
        oC_MainViewModel = ViewModelProvider(this,CAppViewModelFactory(oRepository)).get(C01MainViewModel::class.java)


    }

    private fun C_SETxData(){
         var aoListData = mutableListOf<cmlDataList>()
        aoListData = oC_Db?.C_GEToData() as MutableList<cmlDataList>
        oC_DataView.adapter=C01MainAdapter(aoListData)

    }
    private fun C_SETxLavle(poCalendar: Calendar){
        val tMyFormat="yyyy-MM-dd"
        val oSdf = SimpleDateFormat(tMyFormat)
        oC_ShowDate.setText(oSdf.format(poCalendar.time))
    }


    private fun C_SETxInitView(){
          var bCheckNewData: CheckBox
          var ollLayoutSubData: LinearLayout
          var bCalenderButton: ImageButton
          var bDownloadButton: Button
          var tSearch: EditText
          var bSearchButton: ImageButton
          var bCheckSelectAll : CheckBox
        setContentView(R.layout.w01activity_main)
        bCheckNewData = findViewById(R.id.ocbCheckNewData)
        ollLayoutSubData = findViewById(R.id.ollLayoutSubData)
        oC_ShowDate = findViewById(R.id.oetShowDate)
        bCalenderButton = findViewById(R.id.ocmCalenderButton)
        oC_DataView=findViewById(R.id.orvDataView)
        oC_DataView.layoutManager=LinearLayoutManager(this)
        oC_DataView.setHasFixedSize(true)
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
        val oCalendar = Calendar.getInstance()
        val oDatePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            oCalendar.set(Calendar.YEAR,year)
            oCalendar.set(Calendar.MONTH,month)
            oCalendar.set(Calendar.DAY_OF_MONTH,dayofMonth)
            C_SETxLavle(oCalendar)
        }
        bCalenderButton.setOnClickListener{
            DatePickerDialog(this,oDatePicker,oCalendar.get(Calendar.YEAR),oCalendar.get(Calendar.MONTH),
                oCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }
}