package com.example.exada1.PHelper

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import java.io.File
import java.io.FileOutputStream


class CSQLiteHelper(private var context : Context){
        companion object{
            private var tDB_NAME = "SyncData.db"
        }
    fun C_GEToOpenDatabase(): SQLiteDatabase {
        val oDbfile = context.getDatabasePath(tDB_NAME)

        try {
            var bCheckDB=context.openOrCreateDatabase(tDB_NAME,Context.MODE_PRIVATE,null)
            bCheckDB.close()
                C_SETxCoppyDatabase(oDbfile)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return SQLiteDatabase.openDatabase(oDbfile.path,null,SQLiteDatabase.OPEN_READWRITE)
    }
    private  fun C_GETbCheckDataBase(): Boolean {
        var bCheckDB: SQLiteDatabase? = null
        val oDbfile = context.getDatabasePath(tDB_NAME)
        try {
            bCheckDB = SQLiteDatabase.openDatabase(
                oDbfile.path, null,
                SQLiteDatabase.OPEN_READONLY
            )
            bCheckDB.close()
        } catch (e: SQLiteException) {
        }
        return bCheckDB != null
    }

    private fun C_SETxCoppyDatabase(oDbFile:File){
            var oOpenDb = context.assets.open(tDB_NAME)
            var oOutPutStream = FileOutputStream(oDbFile)
            var oBuffer = ByteArray(1024)
            while (oOpenDb.read(oBuffer)>0){
                    oOutPutStream.write(oBuffer)
                Log.d("SQLHelper", "writing: ")
            }
            oOutPutStream.flush()
            oOutPutStream.close()
            oOpenDb.close()
             Log.d("SQLHelper", "completed: ")
    }

    @Throws(SQLException::class)
    fun C_GEToQuery(tquery:String) : Cursor?{
        var oTempCursor : Cursor? = null
        val oDatabase = context.openOrCreateDatabase(tDB_NAME,Context.MODE_PRIVATE,null)
        try {
            oTempCursor=oDatabase.rawQuery(tquery,null)
            if (oTempCursor != null && oTempCursor.count > 0)
            {
                if (oTempCursor.moveToFirst())
                {
                    return oTempCursor
                }
            }

        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            oDatabase.close()
        }
        return oTempCursor
    }

}

