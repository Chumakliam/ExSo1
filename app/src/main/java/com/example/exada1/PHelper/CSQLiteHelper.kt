package com.example.exada1.PHelper

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.*
import android.util.Log
import com.example.exada1.PDataPackage.CDataList

import com.example.exada1.PDataPackage.PCompanyPackage.CCompanyData
import com.example.exada1.PDataPackage.PDistrictPackage.CDistrictData
import com.example.exada1.PDataPackage.PProvincePackage.CProvinceData
import java.io.File
import java.io.FileOutputStream
import com.google.gson.Gson


class CSQLiteHelper(private var context : Context){
    companion object {
        private var tDB_NAME = "SyncData.db"
    }

    fun C_GEToOpenDatabase(): SQLiteDatabase {
        val oDbfile = context.getDatabasePath(tDB_NAME)
        try {
            var oCheckDB = context.openOrCreateDatabase(tDB_NAME, Context.MODE_PRIVATE, null)
            oCheckDB.close()
            C_SETxCoppyDatabase(oDbfile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return SQLiteDatabase.openDatabase(oDbfile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun C_GETbCheckDataBase(): Boolean {
        return context.getDatabasePath(tDB_NAME).exists()
    }

    private fun C_SETxCoppyDatabase(oDbFile: File) {
        var oOpenDb = context.assets.open(tDB_NAME)
        var oOutPutStream = FileOutputStream(oDbFile)
        var oBuffer = ByteArray(1024)
        while (oOpenDb.read(oBuffer) > 0) {
            oOutPutStream.write(oBuffer)
            Log.d("SQLHelper", "writing: ")
        }
        oOutPutStream.flush()
        oOutPutStream.close()
        oOpenDb.close()
        Log.d("SQLHelper", "completed: ")
    }

    private fun C_SETtCursorToJson(poCursor: Cursor): String {
        val oJson = StringBuilder("{")
        for (colIndex in 0 until poCursor.columnCount) {
            oJson.append("\"${poCursor.getColumnName(colIndex)}\": \"${poCursor.getString(colIndex)}\"")
            if (colIndex < poCursor.columnCount - 1) oJson.append(", ")
        }
        oJson.append("}")
        return oJson.toString()
    }

    @Throws(SQLException::class)
    fun C_GEToQuery(tquery: String): Cursor? {
        var oTempCursor: Cursor? = null
        val oDatabase = context.openOrCreateDatabase(tDB_NAME, Context.MODE_PRIVATE, null)
        try {
            oTempCursor = oDatabase.rawQuery(tquery, null)
            if (oTempCursor != null && oTempCursor.count > 0) {
                if (oTempCursor.moveToFirst()) {
                    return oTempCursor
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            oDatabase.close()
        }
        return oTempCursor
    }



     fun C_GEToData() : List<CDataList> {
        val aoValues = mutableListOf<CDataList>()
        val gson = Gson()
        var tSqlQuery = "SELECT FTSynName,FDSynLast " +
                    "FROM  TSysSyncData_L a " +
                    "INNER JOIN  TSysSyncData b " +
                    "ON a.FNSynSeqNo=b.FNSynSeqNo  WHERE a.FNLngID=1 "

        val oCursor = C_GEToQuery(tSqlQuery)

        if (oCursor != null) {
            while (oCursor.moveToNext()) {
                val oJson = C_SETtCursorToJson(oCursor)
                val oValue: CDataList = gson.fromJson(oJson, CDataList::class.java)
                aoValues.add(oValue)
            }
            oCursor.close()
        }
        return aoValues
    }

    fun C_SETxInsertDd(oItem: CCompanyData) {
        val oDatabase = context.openOrCreateDatabase(tDB_NAME, Context.MODE_PRIVATE, null)
        oDatabase.execSQL("DELETE FROM TCNMComp")
        oDatabase.execSQL("DELETE FROM TCNMComp_L")

        for (nI in oItem.roItem.raComp.indices) {
            if (oItem.roItem.raComp != null) {
                Log.d("C_API", "C_SET0insertDd: "+oItem.roItem.raComp.toString())
                oDatabase.execSQL(
                    "INSERT INTO TCNMComp VALUES('" + oItem.roItem.raComp.get(nI).rtCmpCode +
                            "','" + oItem.roItem.raComp.get(nI).rtCmpTel +
                            "','" + oItem.roItem.raComp.get(nI).rtCmpFax +
                            "','" + oItem.roItem.raComp.get(nI).rtBchcode +
                            "','" + oItem.roItem.raComp.get(nI).rtCmpWhsInOrEx +
                            "','" + oItem.roItem.raComp.get(nI).rtCmpRetInOrEx +
                            "','" + oItem.roItem.raComp.get(nI).rtCmpEmail +
                            "','" + oItem.roItem.raComp.get(nI).rtRteCode +
                            "','" + oItem.roItem.raComp.get(nI).rtVatCode +
                            "','" + oItem.roItem.raComp.get(nI).rdLastUpdOn +
                            "','" + oItem.roItem.raComp.get(nI).rtLastUpdBy +
                            "','" + oItem.roItem.raComp.get(nI).rdCreateOn +
                            "','" + oItem.roItem.raComp.get(nI).rtCreateBy+"');"
                )
            }
        }

        for (nI in oItem.roItem.raCompLng.indices) {
            if (oItem.roItem.raCompLng != null) {
                oDatabase.execSQL(
                    "INSERT INTO TCNMComp_L  VALUES('" + oItem.roItem.raCompLng.get(nI).rtCmpCode +
                            "','" + oItem.roItem.raCompLng.get(nI).rnLngID +
                            "','" + oItem.roItem.raCompLng.get(nI).rtCmpName +
                            "','" + oItem.roItem.raCompLng.get(nI).rtCmpShop +
                            "','" + oItem.roItem.raCompLng.get(nI).rtCmpDirector+"');"
                )
            }
        }

    }

    fun C_SETxInsertProvince(oItem: CProvinceData){
        val oDatabase = context.openOrCreateDatabase(tDB_NAME, Context.MODE_PRIVATE, null)
        oDatabase.execSQL("DELETE FROM TCNMProvince")
        oDatabase.execSQL("DELETE FROM TCNMProvince_L")
        for (nI in oItem.roItem.raPvn.indices) {
            if (oItem.roItem.raPvn != null) {
                Log.d("C_API", "C_SET0insertDd: "+oItem.roItem.raPvn.toString())
                oDatabase.execSQL(
                    "INSERT INTO TCNMProvince VALUES('" + oItem.roItem.raPvn.get(nI).rtPvnCode +
                            "','" + oItem.roItem.raPvn.get(nI).rtZneCode +
                            "','" + oItem.roItem.raPvn.get(nI).rtPvnLatitude +
                            "','" + oItem.roItem.raPvn.get(nI).rtPvnLongitude +
                            "','" + oItem.roItem.raPvn.get(nI).rdLastUpdOn +
                            "','" + oItem.roItem.raPvn.get(nI).rdCreateOn +
                            "','" + oItem.roItem.raPvn.get(nI).rtLastUpdBy +
                            "','" + oItem.roItem.raPvn.get(nI).rtCreateBy+"');"
                )
            }
        }
        for (nI in oItem.roItem.raPvnLng.indices) {
            if (oItem.roItem.raPvnLng != null) {
                Log.d("C_API", "C_SET0insertDd: "+oItem.roItem.raPvn.toString())
                oDatabase.execSQL(
                    "INSERT INTO TCNMProvince_L  VALUES('" + oItem.roItem.raPvnLng.get(nI).rtPvnCode +
                            "','" + oItem.roItem.raPvnLng.get(nI).rnLngID +
                            "','" + oItem.roItem.raPvnLng.get(nI).rtPvnName+"');"
                )
            }
        }

    }

    fun C_SETxinsertDistrict(oItem: CDistrictData){
        val oDatabase = context.openOrCreateDatabase(tDB_NAME, Context.MODE_PRIVATE, null)
        oDatabase.execSQL("DELETE FROM TCNMDistrict ")
        oDatabase.execSQL("DELETE FROM TCNMDistrict_L")
        for (i in oItem.roItem.raDistrinct.indices) {
            if (oItem.roItem.raDistrinct != null) {
                oDatabase.execSQL(
                    "INSERT INTO TCNMDistrict VALUES('" + oItem.roItem.raDistrinct.get(i).rtDstCode +
                            "','" + oItem.roItem.raDistrinct.get(i).rtDstPost +
                            "','" + oItem.roItem.raDistrinct.get(i).rtPvnCode +
                            "','" + oItem.roItem.raDistrinct.get(i).rtDstLatitude +
                            "','" + oItem.roItem.raDistrinct.get(i).rtDstLongitude +
                            "','" + oItem.roItem.raDistrinct.get(i).rdLastUpdOn +
                            "','" + oItem.roItem.raDistrinct.get(i).rdCreateOn +
                            "','" + oItem.roItem.raDistrinct.get(i).rtLastUpdBy +
                            "','" + oItem.roItem.raDistrinct.get(i).rtCreateBy+"');"
                )
            }
        }
        Log.d("C_API", "TCNMDistrict inserted ")
        for (nI in oItem.roItem.raDistrinctLng.indices) {
            if (oItem.roItem.raDistrinctLng != null) {
                oDatabase.execSQL(
                    "INSERT INTO TCNMProvince_L  VALUES('" + oItem.roItem.raDistrinctLng.get(nI).rtDstCode +
                            "','" + oItem.roItem.raDistrinctLng.get(nI).rnLngID +
                            "','" + oItem.roItem.raDistrinctLng.get(nI).rtDstName+"');"
                )
            }
        }
        Log.d("C_API", "TCNMDistrict_L inserted ")
    }
}

