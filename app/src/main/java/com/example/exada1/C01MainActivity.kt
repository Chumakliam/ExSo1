package com.example.exada1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exada1.PAdapter.C01MainAdapter
import com.example.exada1.PDataPackage.cmlData
import com.example.exada1.PViewmodel.C01MainViewModel
import com.example.exada1.PViewmodel.CAppViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class C01MainActivity : AppCompatActivity() {
    private lateinit var viewMode: C01MainViewModel
    private  var factory = CAppViewModelFactory()
    private lateinit var bCheckNewData: CheckBox
    private lateinit var ollLayoutSubData: LinearLayout
    private lateinit var tShowDate: EditText
    private lateinit var bCalenderButton: ImageButton

    private lateinit var orvDataView: RecyclerView
    private lateinit var oArrayList: ArrayList<cmlData>
    lateinit var tTitle:Array<String>
    lateinit var tDate:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewMode=ViewModelProvider(this,factory).get(C01MainViewModel::class.java)
        setContentView(R.layout.w01activity_main)
        bCheckNewData = findViewById(R.id.ocbCheckNewData)
        ollLayoutSubData = findViewById(R.id.ollLayoutSubData)
        tShowDate = findViewById(R.id.oetShowDate)
        bCalenderButton = findViewById(R.id.ocmCalenderButton)
        orvDataView=findViewById(R.id.orvDataView)
        orvDataView.layoutManager=LinearLayoutManager(this)
        orvDataView.setHasFixedSize(true)

        bCheckNewData.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ollLayoutSubData.visibility = View.GONE
            }
           else{

                ollLayoutSubData.visibility = View.VISIBLE
            }
        }

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

        tTitle = arrayOf(
            "บริษัท",
            "สาขา",
            "ผู้ใช้"
        )

        tDate = arrayOf(
            "2023-08-02 11:00:00",
            "2023-08-02 11:00:00",
            "2023-08-02 11:00:00"
        )


        oArrayList = arrayListOf<cmlData>()
        C_SETxData()
    }
    private fun C_SETxData(){
        for(i in tTitle.indices){
            val oData = cmlData(tTitle[i],tDate[i])
            oArrayList.add(oData)
        }
        orvDataView.adapter=C01MainAdapter(oArrayList)
    }
    private fun C_SETxLavle(ocdCalendar: Calendar){
        val myFormat="yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat)
        tShowDate.setText(sdf.format(ocdCalendar.time))
    }
}