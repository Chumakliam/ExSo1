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
import com.example.exada1.PDataPackage.CDataList
import com.example.exada1.PDataPackage.PCompanyPackage.CCompanyData
import com.example.exada1.PDataPackage.PDistrictPackage.CDistrictData
import com.example.exada1.PDataPackage.PProvincePackage.CProvinceData
import com.example.exada1.PHelper.CSQLiteHelper
import com.example.exada1.PRepository.CRepository
import com.example.exada1.PViewmodel.C01MainViewModel
import com.example.exada1.PViewmodel.CAppViewModelFactory
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar

class C01MainActivity : AppCompatActivity() {
    private lateinit var oMainViewModel: C01MainViewModel
    private lateinit var bCheckNewData: CheckBox
    private lateinit var ollLayoutSubData: LinearLayout
    private lateinit var tShowDate: EditText
    private lateinit var bCalenderButton: ImageButton
    private lateinit var orvDataView: RecyclerView

    private var listData = mutableListOf<CDataList>()
    private lateinit var bDownloadButton: Button
    private lateinit var tSearch: EditText
    private lateinit var bSearchButton: ImageButton
    private var oDb:CSQLiteHelper?=null
    private lateinit var bCheckSelectAll : CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        C_SETxInitView()
        oDb = CSQLiteHelper(this)
        if(!oDb?.C_GETbCheckDataBase()!!){
            oDb?.C_GEToOpenDatabase()
        }else{
            Log.d("MainActivity", "Database is: exists")
        }
        C_SETxData()
        val oApiService = OApiUtilities.oRetrofit.create(IApiService::class.java)
        val oRepository = CRepository(oApiService, oDb!!)
        oMainViewModel = ViewModelProvider(this,CAppViewModelFactory(oRepository)).get(C01MainViewModel::class.java)


    }

    private fun C_SETxData(){
        listData = oDb?.C_GEToData() as MutableList<CDataList>
        orvDataView.adapter=C01MainAdapter(listData)

    }
    private fun C_SETxLavle(poCalendar: Calendar){
        val tMyFormat="yyyy-MM-dd"
        val oSdf = SimpleDateFormat(tMyFormat)
        tShowDate.setText(oSdf.format(poCalendar.time))
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