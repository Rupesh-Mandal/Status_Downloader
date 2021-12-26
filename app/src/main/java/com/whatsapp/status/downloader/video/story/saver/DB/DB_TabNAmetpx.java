package com.whatsapp.status.downloader.video.story.saver.DB;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class DB_TabNAmetpx extends SQLiteOpenHelper {
 String DBname;
 Context context;
 SharedPreferences AI;

    ContentValues values ;
    SQLiteDatabase db ;
    public DB_TabNAmetpx(Context context, String DB_name) {
        super(context,DB_name
      , null,1);


          DBname=DB_name.trim();

        this.context=context;

        AI=context.getSharedPreferences("AI",MODE_PRIVATE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tablename (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT)");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS Tablename" );
  onCreate(db);
    }



    public void dellDBaall(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Tablename");
        db.close();



    }




    //////////////////////////

    public ArrayList<String> getAllTAbles() {
        ArrayList<String> data = new ArrayList<>();
        data.clear();
        SQLiteDatabase database=this.getReadableDatabase();
        try {

            Cursor cursor=database.rawQuery("SELECT * FROM Tablename",null);

            while (cursor.moveToNext())
            {


                data.add(cursor.getString(  cursor.getColumnIndex("Name")).replaceAll("\\s+","").trim());

                Log.v("msgx",cursor. getString(  cursor.getColumnIndex("Name")).trim());
            }

            cursor.close();




        }catch (Exception e){
            Log.v("msgx","TAvNAME error: ----> "+e.toString());
        }



        database.close();
        return data;

    }



    public void addTablesName(String name) {

        db = this.getWritableDatabase();


        values = new ContentValues();
        values.put("Name",name);

        if (db.insert("Tablename" , null, values) != -1) {

            AI.edit().putBoolean(name+"TAB",true).apply();

            Log.v("msgx","saved  tab name  "+name );
        }else {


            Log.v("msgx","error not saved  tab name  "+name );

        }




        db.close();
    }


}

